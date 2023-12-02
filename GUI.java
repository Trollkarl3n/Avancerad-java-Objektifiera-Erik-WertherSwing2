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
        FilHanterare filHanterare = new FilHanterare(GUI.URL);
        rows = filHanterare.getRows();
        String[] header = filHanterare.getHeader();

        model = new DefaultTableModel(rows.toArray(new String[0][0]), header);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        JButton save = new JButton("Save");
        JPanel bottom = new JPanel();
        save.setFocusable(false);
        save.addActionListener(this::save);
        frame.add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new FlowLayout());
        bottom.add(save);
    }

    private void initializeFilePicker() {
        JFileChooser fileChooser = new JFileChooser("src");
        int result = fileChooser.showOpenDialog(null);
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
        URL = s;
    }
}