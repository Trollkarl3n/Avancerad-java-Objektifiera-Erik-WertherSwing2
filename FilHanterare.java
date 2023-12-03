import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilHanterare {

    // Statiska fält används för att dela data mellan olika instanser av FilHanterare-klassen.
    private static List<String[]> fileData;
    private static String[] header;

    // Konstruktor för att skapa en ny instans av FilHanterare med en given URL till filen.
    FilHanterare(String URL) {
        fileData = new ArrayList<>();
        try {
            File file = new File(URL);
            String[] extension = URL.split("\\.");

            // Beroende på filtyp, anropa läs-metoden för att bearbeta filen.
            if (Objects.equals(extension[1], "csv")) {
                readCSV(file);
            } else if (Objects.equals(extension[1], "json")) {
                readJSON(file);
            } else {
                showUnsupportedFormatError(); // Visa felmeddelande om filtypen inte stöds.
            }
        } catch (IOException e) {
            System.out.println("Fel: " + e);
        }

        // Sätt header till den första raden av fildata.
        header = fileData.get(0);
    }

    // Läs en JSON-fil och lägg till varje rad i fileData.
    private void readJSON(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                fileData.add(array);
            }
        }
    }

    // Läs en CSV-fil och lägg till varje rad i fileData.
    private void readCSV(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                fileData.add(array);
            }
        }
    }

    // Visa felmeddelande om filtypen inte stöds.
    private void showUnsupportedFormatError() {
        JOptionPane.showMessageDialog(null, "Filen stöds ej", "Fel!", JOptionPane.INFORMATION_MESSAGE, null);
        System.exit(0);
    }

    // Returnera header, som är den första raden av fildata.
    static String[] getHeader() {
        return header;
    }

    // Returnera alla rader av fildata.
    static List<String[]> getRows() {
        return fileData;
    }
}