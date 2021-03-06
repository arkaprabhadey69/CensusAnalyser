package com.bl.census;

public class CensusAnalyserException extends Exception {

    public CensusAnalyserException() {

    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,FILE_OR_HEADER_PROBLEM,NO_DATA;
    }
    ExceptionType type;
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
