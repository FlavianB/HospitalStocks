package com.example.hospitalstocks.Services;

import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {
    public String generateCSV(List<String> dataRows, String ... headers) {
        String csvFilePath = "output.csv";
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            StringBuilder header = new StringBuilder();
            for (String h: headers)
            {
                header.append(h).append(",");
            }
            String hString = header.toString();
            writer.append(hString.substring(0, hString.length() - 1)).append("\n");
            for (String row : dataRows) {
                StringBuilder rowString = new StringBuilder();
                List<String> rowList = Arrays.asList(row.split(","));
                for (int i = 0; i < rowList.size(); i++) {
                    String value = rowList.get(i).split(": ")[1];
                    rowString.append(value).append(",");
                }
                String rString = rowString.toString();
                writer.append(rString.substring(0, rString.length() - 1)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFilePath;
    }
}

