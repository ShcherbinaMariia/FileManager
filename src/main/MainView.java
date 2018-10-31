package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MainView extends JFrame {

    //Java class for working with main window of file manager

    private MainController mainController = new MainController();

    private JPanel mainPanel;

    private JComboBox<String> leftDriveChooser;
    private JComboBox<String> rightDriveChooser;

    private JButton leftRefreshButton;
    private JButton leftBackButton;
    private JButton leftCreateButton;
    private JButton leftDeleteButton;
    private JButton leftCreateDirectoryButton;
    private JButton leftCopyButton;

    private JButton rightRefreshButton;
    private JButton rightBackButton;
    private JButton rightCreateButton;
    private JButton rightDeleteButton;
    private JButton rightCreateDirectoryButton;
    private JButton rightCopyButton;

    private JList<String> leftList;
    private JList<String> rightList;
    private JButton leftGoButton;
    private JButton rightGoButton;
    private JButton leftChangeTextFileButton;
    private JButton rightChangeTextButton;
    private JButton leftFindButton;
    private JButton rightFindButton;
    private JButton leftRenameButton;
    private JButton rightRenameButton;
    private JButton leftMoveButton;
    private JButton rightMoveButton;
    private JButton leftMakeFrequencyDictionaryButton;
    private JButton rightMakeFrequencyDictionaryButton;
    private JButton leftSelectLinkedFilesButton;
    private JButton rightSelectLinkedFilesButton;
    private JButton rightOpenEditorButton;
    private JButton leftOpenEditorButton;

    MainView() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height *= 0.8;
        screenSize.width *= 0.8;

        setSize(screenSize);

        setDriveChoosers(UtilsOS.getLogicalDrives());

        leftList.setModel(mainController.leftModel.model);
        rightList.setModel(mainController.rightModel.model);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("File Manager");
        setContentPane(mainPanel);
        setVisible(true);

        mainController.refresh(mainController.leftModel);
        mainController.refresh(mainController.rightModel);

        leftRefreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.refresh(mainController.leftModel);
            }
        });

        rightRefreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.refresh(mainController.rightModel);
            }
        });

        leftCreateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = ViewUtils.getNameWindow("Enter name of file you want to create");
                int exitCode = mainController.createFile(mainController.leftModel, name);
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightCreateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = ViewUtils.getNameWindow("Enter name of file you want to create");
                int exitCode = mainController.createFile(mainController.rightModel, name);
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftCreateDirectoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String dirName = ViewUtils.getNameWindow("Enter name of directory you want to create");
                int exitCode = mainController.createDirectory(mainController.leftModel, dirName);
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightCreateDirectoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String dirName = ViewUtils.getNameWindow("Enter name of directory you want to create");
                int exitCode = mainController.createDirectory(mainController.rightModel, dirName);
                ViewUtils.processExitCode(exitCode);;
            }
        });

        leftList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                JList list = (JList)evt.getSource();

                if (evt.getClickCount() == 2) {

                    int index = list.locationToIndex(evt.getPoint());
                    mainController.goTo(mainController.leftModel, index);
                }
            }
        });

        rightList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                JList list = (JList)evt.getSource();

                if (evt.getClickCount() == 2) {

                    int index = list.locationToIndex(evt.getPoint());
                    mainController.goTo(mainController.rightModel, index);
                }
            }
        });

        leftGoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.setDrive(mainController.leftModel, String.valueOf(leftDriveChooser.getSelectedItem()));
            }
        });

        rightGoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.setDrive(mainController.rightModel, String.valueOf(rightDriveChooser.getSelectedItem()));
            }
        });

        leftFindButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String patternStr = ViewUtils.getNameWindow("Enter name of files you want to select");
                select(leftList, mainController.find(mainController.leftModel, patternStr));
            }
        });
        rightFindButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String patternStr = ViewUtils.getNameWindow("Enter name of files you want to select");
                select(rightList, mainController.find(mainController.rightModel, patternStr));
            }
        });

        leftBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.back(mainController.leftModel);
            }
        });

        rightBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.back(mainController.rightModel);
            }
        });

        leftChangeTextFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String fileName = leftList.getSelectedValue();
                mainController.changeText(mainController.leftModel, fileName);
            }
        });
        rightChangeTextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String fileName = rightList.getSelectedValue();
                mainController.changeText( mainController.rightModel, fileName);
            }
        });

        leftRenameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String newName = ViewUtils.getNameWindow("Enter new name");
                int exitCode = mainController.rename(mainController.leftModel,leftList.getSelectedValue(), newName);
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightRenameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String newName = ViewUtils.getNameWindow("Enter new name");
                int exitCode = mainController.rename(mainController.rightModel,rightList.getSelectedValue(), newName);
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftDeleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.delete(mainController.leftModel, leftList.getSelectedValue(), ViewUtils.confirm());
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightDeleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.delete(mainController.leftModel, leftList.getSelectedValue(), ViewUtils.confirm());
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftCopyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.copy(mainController.leftModel, mainController.rightModel, leftList.getSelectedIndices());
                ViewUtils.processExitCode(exitCode);
            }
        });
        rightCopyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.copy(mainController.rightModel, mainController.leftModel, rightList.getSelectedIndices());
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftMoveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.move(mainController.leftModel, mainController.rightModel, leftList.getSelectedIndices());
                ViewUtils.processExitCode(exitCode);
            }
        });
        rightMoveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.move(mainController.rightModel, mainController.leftModel, rightList.getSelectedIndices());
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftMakeFrequencyDictionaryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.makeFrequencyDictionary(mainController.leftModel, leftList.getSelectedIndex());
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightMakeFrequencyDictionaryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = mainController.makeFrequencyDictionary(mainController.rightModel, rightList.getSelectedIndex());
                ViewUtils.processExitCode(exitCode);
            }
        });
        leftSelectLinkedFilesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = 0;
                try {
                    select(leftList, mainController.getLinkedFiles(mainController.leftModel, leftList.getSelectedIndex()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                    exitCode = 1;
                }
                ViewUtils.processExitCode(exitCode);
            }
        });

        rightSelectLinkedFilesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int exitCode = 0;
                try {
                    select(rightList, mainController.getLinkedFiles(mainController.rightModel, rightList.getSelectedIndex()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                    exitCode = 1;
                }
                ViewUtils.processExitCode(exitCode);
            }
        });

        leftOpenEditorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.openEditor(mainController.leftModel);
            }
        });

        rightOpenEditorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainController.openEditor(mainController.rightModel);
            }
        });
    }

    private void select(JList list, List<Integer> indexes){
        ListSelectionModel sm = list.getSelectionModel();
        sm.clearSelection();
        indexes.forEach(index -> sm.addSelectionInterval(index, index));
    }

    private void setDriveChoosers(Vector<String> Drives) {
        Drives.forEach(drive -> leftDriveChooser.addItem(drive));
        Drives.forEach(drive -> rightDriveChooser.addItem(drive));
    }
}
