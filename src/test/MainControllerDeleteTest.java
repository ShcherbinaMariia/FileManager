package test;

import main.MainController;
import main.Model;
import main.UtilsOS;
import org.junit.jupiter.api.Assertions;

import java.io.File;

public class MainControllerDeleteTest {
    private static String path = "/Users/mariyashcherbina/Documents/TestDir/";
    private static MainController mainController = new MainController();
    private static Model model = new Model(path);
    private static String emptyDirName = "TestEmptyDir";
    private static String nonEmptyDirName = "TestNonEmptyDir";
    private static String testFileName = "TestFile.txt";


    @org.junit.jupiter.api.BeforeAll
    static void createFilesAndDirectories(){
        UtilsOS.createDirectory(path + nonEmptyDirName);
        UtilsOS.createFile(path + nonEmptyDirName + "/" + testFileName);
    }

    @org.junit.jupiter.api.Test
    void deleteNonEmptyDirectory() {
        //check that exit code when trying to create existing file is 1
        Assertions.assertEquals(2, mainController.delete(model, nonEmptyDirName, true));
    }

    @org.junit.jupiter.api.Test
    void deleteFile() {
        UtilsOS.createFile(path + testFileName);
        File testFile  = new File(path + testFileName);
        mainController.delete(model, testFileName, true);
        //check that file doesn't exists after using delete method
        Assertions.assertTrue(!testFile.exists());
    }

    @org.junit.jupiter.api.Test
    void deleteEmptyDirectory() {
        UtilsOS.createDirectory(path + emptyDirName);
        File testDir  = new File(path + emptyDirName);
        //check that exit code when trying to create existing file is 1
        mainController.delete(model, emptyDirName, true);
        Assertions.assertTrue(!testDir.exists());
    }

    @org.junit.jupiter.api.AfterAll
    static void clear(){
        UtilsOS.deleteFile(path + nonEmptyDirName + "/" + testFileName);
        UtilsOS.deleteFile(path + nonEmptyDirName);
        UtilsOS.deleteFile(path + emptyDirName);
        UtilsOS.deleteFile(path + testFileName);
    }
}
