package com.bl.census;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {

    <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
    <E> List<E> getCSVFList(Reader reader, Class csvClass) throws CSVBuilderException;



}
