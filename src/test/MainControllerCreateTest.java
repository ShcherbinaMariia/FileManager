package test;

import main.MainController;
import main.Model;
import main.UtilsOS;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static junit.framework.TestCase.assertTrue;

class MainControllerCreateTest {

    //testing creating files and directories

    private MainController mainController = new MainController();
    private Model model = new Model("/Users/mariyashcherbina/Documents/TestDir");

    @org.junit.jupiter.api.Test
    void createDirectory() {
        String testDirName = "NewTestDir";
        File testDir = new File(model.getPath() + "/" + testDirName);
        mainController.createDirectory(model, testDirName);
        //checks that after createDirectory method such directory exists
        assertTrue(testDir.exists() && testDir.isDirectory());
        //checks that trying to create second directory with same name will end in exit code 1
        Assertions.assertEquals(1, mainController.createDirectory(model, testDirName));
        UtilsOS.deleteFile(model.getPath() + testDirName);

    }

    @org.junit.jupiter.api.Test
    void createFile() {
        String testFileName = "NewTestFile.txt";
        File testFile = new File(model.getPath() + "/" + testFileName);
        mainController.createFile(model, testFileName);
        //check that after createFile function call such file exists
        assertTrue(testFile.exists());
        //checks that trying to create second directory with same name will end in exit code 1
        Assertions.assertEquals(1, mainController.createFile(model, testFileName));
        UtilsOS.deleteFile(model.getPath() + testFileName);
    }
}