package main.tableProcessor;
import main.ViewUtils;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TableProcessorView extends JFrame {

    TableProcessorController controller = new TableProcessorController();

    private JTable mainTable;
    private JButton addRowButton;
    private JButton addColumnButton;
    private JButton saveButton;
    private JTextField expressionTextField;
    private JButton setButton;
    private JButton deleteRowButton;
    private JButton deleteColumnButton;
    private JPanel mainPanel;
    private JButton newFileButton;
    private JButton helpButton;

    public TableProcessorView(String path, String fileName) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.8;
        screenSize.width *= 0.8;

        mainTable.setGridColor(Color.BLACK);

        setSize(screenSize);

        setContentPane(mainPanel);
        setVisible(true);

        controller.currentPath = path;

        if (fileName != null){
            try {
                controller.open(fileName);
                mainTable.setModel(controller.model);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        addRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.addRow(getIndex("Enter index of row to add"));
            }
        });
        addColumnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.addColumn(getIndex("Enter index of column to add"));
            }
        });
        deleteRowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.deleteRow(getIndex("Enter index of row to delete"), ViewUtils.confirm());
            }
        });
        deleteColumnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.deleteColumn(getStringIndex("Enter index of column to delete"), ViewUtils.confirm());
            }
        });
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = ViewUtils.getNameWindow("Enter name of file");
                controller.save(name + ".tpsf");
            }
        });
        setButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = mainTable.getSelectedRow();
                int column = mainTable.getSelectedColumn();
                String expression = expressionTextField.getText();
                boolean success = controller.set(row, column, expression);
                if (!success)
                    ViewUtils.processExitCode(4);
            }
        });

        newFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String parameters = JOptionPane.showInputDialog(new JFrame(), "Enter number of rows and columns, separated by comma");
                controller.newTable(parameters);
                mainTable.setModel(controller.model);
            }
        });

        mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = mainTable.rowAtPoint(evt.getPoint());
                int col = mainTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    expressionTextField.setText(controller.model.getExpressionAt(row, col));
                }
            }
        });
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showHelpMessage();
            }
        });
    }
    private void showHelpMessage(){
        String msg = "Allowed commands are +, -, *, /, min(), max()\n";
        msg += "To use cell value type cell coordinates in format A0\n(Upper case letter - column identifier, int number - row identifier)\n";
        msg += "Lines are numerated from 0\n";
        msg += "EXAMPLE: min(2, A0) + B1 * 2 - C1 / A1 + max(2, 4)";
        JOptionPane.showMessageDialog(null, msg, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private int getIndex(String message){
        String index=JOptionPane.showInputDialog(new JFrame(), message);
        return Integer.parseInt(index);
    }

    private String getStringIndex(String message){
        String index=JOptionPane.showInputDialog(new JFrame(), message);
        return index;
    }
}
