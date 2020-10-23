package com.bl.census;

//import com.bl.jar.CSVBuilderException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public void welcomeMessage() {
        System.out.println("Welcome to census analyser");
    }

    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCSV> stateCSVList=null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            censusCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();

        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            stateCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IndiaStateCSV.class);
            return stateCSVList.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);

        }

    }

    public int loadIndiaStateOrCensusDataUsingCommonsCSV(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .parse(reader)
                    .iterator();
            return this.getCount(records);
        } catch (IOException | RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    public <E> int getCount(Iterator<E> csvIterator) {
        Iterable<E> csvIterable = () -> csvIterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }

//MethodToReturnJSONFileOfStatesInDictionaryOrder
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        try (Writer writer = new FileWriter("./src/test/resources/IndiaStateCensusDataJson.json")) {
            if (censusCSVList == null || censusCSVList.size() == 0) {
                throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
            }
            Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
            this.sort(censusComparator);
            String json = new Gson().toJson(censusCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        try (Writer writer = new FileWriter("./src/test/resources/IndiaStateCodeDataJson.json")) {
            if (stateCSVList== null || stateCSVList.size() == 0) {
                throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
            }
            Comparator<IndiaStateCSV> censusComparator = Comparator.comparing(state -> state.stateCode);
            this.sortState(censusComparator);
            String json = new Gson().toJson(stateCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(stateCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        try (Writer writer = new FileWriter("./src/test/resources/IndiaStatePopulationDataJson.json")) {
            if (censusCSVList == null || censusCSVList.size() == 0) {
                throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
            }
            Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
            this.descendingSort(censusComparator);
            String json = new Gson().toJson(censusCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }



    private void sort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < censusCSVList.size() - 1; i++) {
            for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
                IndiaCensusCSV census1 = censusCSVList.get(j);
                IndiaCensusCSV census2 = censusCSVList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusCSVList.set(j, census2);
                    censusCSVList.set(j + 1, census1);
                }
            }
        }
    }
    private void descendingSort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < censusCSVList.size() - 1; i++) {
            for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
                IndiaCensusCSV census1 = censusCSVList.get(j);
                IndiaCensusCSV census2 = censusCSVList.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    censusCSVList.set(j, census2);
                    censusCSVList.set(j + 1, census1);
                }
            }
        }
    }
    private void sortState(Comparator<IndiaStateCSV> censusComparator) {
        for (int i = 0; i < stateCSVList.size() - 1; i++) {
            for (int j = 0; j < stateCSVList.size() - i - 1; j++) {
               IndiaStateCSV census1 = stateCSVList.get(j);
                IndiaStateCSV census2 = stateCSVList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    stateCSVList.set(j, census2);
                    stateCSVList.set(j + 1, census1);
                }
            }
        }
    }

    public static void checkFile(String path) throws CensusAnalyserException {
        String pattern = "^[A-za-z]*.(csv)$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(path);
        if (!m.matches())
            throw new CensusAnalyserException();

    }

}
