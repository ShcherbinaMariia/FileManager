package main;

import main.EditorController;
import main.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TextView extends JFrame {

    private String currentFilePath = null;
    private String path = null;

    private EditorController editorController = new EditorController();
    private JPanel MainView;
    private JToolBar toolBar;
    private JTextArea textEditor;
    private JButton newFileButton;
    private JButton openFileButton;
    private JButton findButton;
    private JButton saveFileButton;

    TextView(String path) {

        setProperties();
        this.path = path;

        open();

        newFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        openFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                open();
            }
        });

        findButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        saveFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (path == null){
                    ViewUtils.getNameWindow("Enter name of file");
                }
                editorController.save(path, textEditor.getText());
            }
        });
    }

    private void setProperties(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.5;
        screenSize.width *= 0.5;

        setSize(screenSize);

        textEditor.setEditable(true);
        setContentPane(MainView);
        setVisible(true);
    }

    private void open(){
        try {
            textEditor.setText(editorController.open(path));
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.processExitCode(-1);
        }
    }
}