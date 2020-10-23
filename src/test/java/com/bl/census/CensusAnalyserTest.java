package com.bl.census;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String WRONG_FILE_TYPE_PATH = "./src/test/resources/IndianCensus.csv";
    private static final String WRONG_FILE_HEADER_PATH = "./src/test/resources/IndianNewCensus.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_STATE = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_TYPE_STATE = "./src/test/resources/IndiaStateCode.txt";
    private static final String WRONG_CSV_FILE_DELIMITER_STATE = "./src/test/resources/IndianStateCodes.csv";
    private static final String WRONG_CSV_FILE_HEADER_STATE = "./src/test/resources/NewIndianStateCodes.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusDataWithWrongFileShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusDataWithWrongTypeShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusDataWithWrongDelimiterShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusDataWithWrongHeaderShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test

    public void givenStatesReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void givenWrongStateCodeFileReturnsException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_STATE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test

    public void givenWrongStateCodeFileTypeReturnsException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_TYPE_STATE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test

    public void givenWrongStateCodeFileWithWrongDelimiterReturnsException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_DELIMITER_STATE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test

    public void givenStateCodeFileWithWrongHeaderReturnsException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_HEADER_STATE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test

    public void givenCensusFileWithCommonsCSVReturnsCorrectNoOfEntries() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void givenStateCodeFileWithCommonsCSVReturnsCorrectNoOfEntries() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeFileWithWrongHeaderReturnsExceptionCommonsCSV() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(WRONG_CSV_FILE_HEADER_STATE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusDataWithWrongDelimiterShouldThrowExceptionCommonsCSV() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void giveIndianCensusData_WhenSortOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void giveIndianCensusData_WhenSortOnState_ShouldReturnSortedResult2() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal", indiaCensusCSV[indiaCensusCSV.length-1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void giveIndianStateData_WhenSortOnStateCode_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaStateCSV[] indiaStateCSV = new Gson().fromJson(sortCensusData, IndiaStateCSV[].class);
           Assert.assertEquals("AD", indiaStateCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void giveIndianStateData_WhenSortOnStateCode_ShouldReturnSortedResult2() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaStateCSV[] indiaStateCSV = new Gson().fromJson(sortCensusData, IndiaStateCSV[].class);
            Assert.assertEquals("WB", indiaStateCSV[indiaStateCSV.length-1].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }
    @Test
    public void giveIndianStateData_WhenSortOnPopulation_ShouldReturnSortedResult2() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
           IndiaCensusCSV[] indiaCensusCSV= new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
           Assert.assertEquals(199812341, indiaCensusCSV[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void giveIndianStateData_WhenSortOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV= new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(607688, indiaCensusCSV[indiaCensusCSV.length-1].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }



}

