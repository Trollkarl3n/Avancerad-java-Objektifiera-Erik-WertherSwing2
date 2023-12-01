import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel för filutforskning
public class FileExplorer extends JPanel {
    private JButton openButton;
    private JFileChooser fileChooser;

    // Konstruktor för att skapa filutforskaren
    public FileExplorer() {
        openButton = new JButton("Öppna fil"); // Skapa en knapp för att öppna filer
        fileChooser = new JFileChooser();        // Skapa en filutforskarinstans

        // Lägg till en lyssnare för knappen
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(FileExplorer.this); // Visa filutforskardialogen och hämta resultatet
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile(); // Hämta den valda filen
                    // Implementera logik för att hantera den valda filen här
                }
            }
        });

        add(openButton); // Lägg till öppna-knappen i panelen
    }
}