import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilHanterare {

    private static List<String[]> fileData;
    private static String[] header;

    FilHanterare(String URL) {
        fileData = new ArrayList<>();
        try {
            File file = new File(URL);
            String[] extension = URL.split("\\.");

            if (Objects.equals(extension[1], "csv")) {
                readCSV(file);
            } else if (Objects.equals(extension[1], "json")) {
                readJSON(file);
            } else {
                showUnsupportedFormatError();
            }
        } catch (IOException e) {
            System.out.println("Fel: " + e);
        }

        header = fileData.get(0);
    }

    private void readJSON(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                fileData.add(array);
            }
        }
    }

    private void readCSV(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                fileData.add(array);
            }
        }
    }

    private void showUnsupportedFormatError() {
        JOptionPane.showMessageDialog(null, "Filen stöds ej", "Fel!", JOptionPane.INFORMATION_MESSAGE, null);
        System.exit(0);
    }

    static String[] getHeader() {
        return header;
    }

    static List<String[]> getRows() {
        return fileData;
    }
}