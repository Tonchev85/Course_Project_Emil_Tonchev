package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvHelper {
    public static Object[][] readCsvFile(String fileName) throws IOException, CsvException {
        CSVReader cvsReader = new CSVReader(new FileReader(fileName));
        List<String[]> cvsData = cvsReader.readAll();
        Object[][] result = new Object[cvsData.size()][2];
        for (int i = 0; i < cvsData.size(); i++) {
            result[i] = cvsData.get(i);
        }
        return result;
    }
}
