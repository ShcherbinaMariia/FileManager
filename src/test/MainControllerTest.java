package test;

import main.MainController;
import main.Model;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    private MainController mainController = new MainController();
    Model model = new Model("/Users/mariyashcherbina/Documents/TestDir");
    String testFileName = "NewTestFile.txt";
    String testDirName = "NewTestDir";


    //testing creating files and directories

    @org.junit.jupiter.api.Test
    void createFile() {
        //check that after createFile function call such file exists
        File testFile  = new File(model.getPath() + "/" + testFileName);
        mainController.createFile(model, testFileName);
        assertTrue(testFile.exists());
    }

    @org.junit.jupiter.api.Test
    void createExistingFile() {
        //check that exit code when trying to create existing file is 1
        Assertions.assertEquals(1, mainController.createFile(model, testFileName));
    }

    @org.junit.jupiter.api.Test
    void createDirectory() {
        File testDir  = new File(model.getPath() + "/" + testDirName);
        mainController.createDirectory(model, testDirName);
        assertTrue(testDir.exists() && testDir.isDirectory());
    }

    @org.junit.jupiter.api.Test
    void createExistingDirectory() {
        //check that exit code when trying to create existing directory is 1
        Assertions.assertEquals(1, mainController.createDirectory(model, testDirName));
    }

}