import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GUI {
    private DefaultTableModel model;
    private JFrame frame;
    private static String URL;
    private List<String[]> rows;

    public GUI() {
        // Skapa en JFrame med eb fulväljare, göra en boarderlayout & panels
        initializeFilePicker();

        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable();
        createBottomPanel();
        frame.setVisible(true);
    }

    private void createTable() {
        // Skapa en instans av FilHanterare och hämta fildata och header.
        FilHanterare filHanterare = new FilHanterare(GUI.URL);
        rows = filHanterare.getRows();
        String[] header = filHanterare.getHeader();
        // Skapa ett DefaultTableModel och en JTable med fildata och header.
        model = new DefaultTableModel(rows.toArray(new String[0][0]), header);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        // Lägg till en JScrollPane runt JTable för scrollning.
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        // Skapa en "Save" -knapp och en JPanel för nedre delen av gränssnittet.
        JButton save = new JButton("Save");
        JPanel bottom = new JPanel();
        // Gör knappen ej fokuserbar och lägg till en ActionListener för "Save".
        save.setFocusable(false);
        save.addActionListener(this::save);
        // Lägg till "Save" -knappen till JPanel.
        frame.add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new FlowLayout());
        bottom.add(save);
    }

    private void initializeFilePicker() {
        // Skapa en JFileChooser för att låta användaren välja en fil från "src"-mappen.
        JFileChooser fileChooser = new JFileChooser("src");
        int result = fileChooser.showOpenDialog(null);
        // Om användaren väljer en fil, sätt URL till den valda filens sökväg. Annars avsluta programmet.
        if (result == JFileChooser.APPROVE_OPTION) {
            setURL(fileChooser.getSelectedFile().getPath());
        } else {
            System.exit(0);
        }
    }

    private void save(ActionEvent e) {
        Object[][] tableData = new Object[model.getRowCount()][model.getColumnCount()];
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                tableData[i][j] = model.getValueAt(i, j);
            }
        }
    }
    private void setURL(String s) {
        // Sätt URL till den valda filens sökväg.
        URL = s;
    }
}