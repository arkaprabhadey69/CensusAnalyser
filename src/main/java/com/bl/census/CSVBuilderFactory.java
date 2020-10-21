package com.bl.census;
import com.bl.jar.ICSVBuilder;
import com.bl.jar.OpenCsvBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder();
    }
}
