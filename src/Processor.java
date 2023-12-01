import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.io.*;
import java.util.List;

// Enkel implementering av en CSV/JSON-processorpanel
public class Processor extends JPanel {

    public void writeToCSV(List<String[]> data, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data); // Använd OpenCSV för att skriva alla rader till filen
            writer.flush();        // Töm buffern och se till att allt skrivs till filen
        } catch (IOException e) {
            e.printStackTrace();
            // Hantera eventuella IO-fel här
        }
    }

    public List<String[]> readFromCSV(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            return csvReader.readAll(); // Använd OpenCSV för att läsa alla rader från filen
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            // Hantera eventuella IO-fel här
            return null;
        }
    }

    // Skriv data till en JSON-fil
    public void writeToJson(Object object, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Json json = new JsonBuilder().setPrettyPrinting().create();
            json.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
            // Hantera eventuella IO-fel här
        }
    }

    public JsonObject readFromJson(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parse(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            // Hantera eventuella IO-fel här
            return null;
        }
    }
}
