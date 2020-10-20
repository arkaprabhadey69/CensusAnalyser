package com.bl.census;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder(){
        return new OpenCsvBuilder();
    }
}
