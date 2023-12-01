import javax.swing.*;

public class GUI extends JFrame {
    private FileExplorer fileExplorer;
    private Processor processor;

    public GUI(FileExplorer fileExplorer, Processor processor) {
        this.fileExplorer = fileExplorer;
        this.processor = processor;

        // Konfigurera huvudramen här

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        add(fileExplorer); // Lägg till filutforskaren
        add(processor);    // Lägg till CSV/JSON-processor (om det är en panel eller liknande)
    }
}