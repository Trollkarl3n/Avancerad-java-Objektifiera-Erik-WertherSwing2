import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                FileExplorer fileExplorer = new FileExplorer();      // Skapa en instans av filutforskarklassen
                Processor processor = new Processor();               // Skapa en instans av CSV/JSON-processorklassen
                GUI gui = new GUI(fileExplorer, processor);          // Skapa huvudramen och skicka med instanserna
                gui.setVisible(true);
            });
        }
    }