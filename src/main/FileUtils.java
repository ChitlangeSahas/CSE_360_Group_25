package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

    ArrayList<String> file_to_tokens(File file) throws IOException {
        ArrayList<Grade> data = new ArrayList<>();
        String csvFile = file.getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "";
        String extension = "";

        int i = csvFile.lastIndexOf('.');
        if (i > 0) extension = csvFile.substring(i + 1);

        if (extension.equals("csv")) {
            System.out.println("CSV File Detected.");
            cvsSplitBy = ",";
        } else if (extension.equals("txt")) {
            System.out.println("TXT File Detected.");
            cvsSplitBy = "\n";
        }
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                if (Settings.GRADE_LEFT_BOUNDARY <= Float.parseFloat(country[0]) && Settings.GRADE_RIGHT_BOUNDARY >= Float.parseFloat(country[0]))
                {
                    data.add(new Grade(Double.parseDouble(country[0])));
                }
                else System.out.println("ERROR LOG TODO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
