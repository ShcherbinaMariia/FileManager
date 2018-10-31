package main;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Vector;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class UtilsOS {

    //Java class for working with operation system API

    public static boolean isDirectory(String filePath){
        File file = new File(filePath);
        return file.isDirectory();
    }


    public static Vector<String> getListElems(File files){

        Vector<String> result = new Vector<>();
        for (final File fileEntry : Objects.requireNonNull(files.listFiles())) {
            if (fileEntry.isDirectory()) {
                result.add(fileEntry.getName() + "/");
            } else {
                result.add(fileEntry.getName());
            }
        }
        return result;
    }

    public static int createDirectory(String filePath){

        File newFolder = new File(filePath);
        if (newFolder.exists()){
            return 1;
        }
        if (newFolder.mkdir()) return 0;
        return 2;
    }

    public static int createFile(String filePath) {
        File file = new File(filePath);
        int result = 1;
        try {
            if (file.createNewFile()) {
                System.out.println(filePath + " File Created");
                result = 0;
            } else
                System.out.println("File " + filePath + " already exists");
        } catch (Exception e) {
            result = -1;
            e.printStackTrace();
        }
        return result;
    }

    public static int renameFile(String path, String oldName, String newName) {

        File file = new File(path + oldName);

        File file2 = new File(path + newName);

        if (file2.exists())
            return 1;

        boolean success = file.renameTo(file2);

        if (!success) {
            return -1;
        }
        return 0;
    }

    public static int deleteFile(String path)
    {
        File file = new File(path);
        if (!file.exists())
            return 0;
        if (file.isDirectory()){
            if (Objects.requireNonNull(file.listFiles()).length != 0) {
                return 2;
            }
        }
        if(file.delete())
        {
           return 0;
        }
        return -1;
    }

    public static int copyFile(String pathFrom, String pathTo) {
        File fileFrom = new File(pathFrom);
        File fileTo = new File(pathTo);
        if (fileTo.exists())
            return 1;
        try {
            Files.copy(fileFrom.toPath(), fileTo.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public static Vector<String> getLogicalDrives(){
        Vector<String> result = new Vector<>();
        File[] roots = File.listRoots();
        for (File root: roots) {
            result.add(root.toString());
        }
        return result;
    }

    public static String getFileText(String path) throws IOException {

        BufferedReader reader;
        File readFile = new File(path);
        reader = new BufferedReader(new FileReader(readFile));
        StringBuilder result = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            result.append(line).append("\n");
            line = reader.readLine();
        }
        return result.toString();
    }

    public static int writeToFile(String path, String content){
        BufferedWriter writer;
        File writeFile = new File(path);
        try {
            writer = new BufferedWriter(new FileWriter(writeFile));
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
