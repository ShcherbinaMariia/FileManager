package main;

import main.tableProcessor.TableProcessorView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainController {

    Model leftModel;
    Model rightModel;

    private TextView textView;
    private TableProcessorView tableView;

    public MainController() {

        leftModel = new Model("/");
        rightModel = new Model("/");
    }

    public void refresh(Model model){
        model.refresh();
    }

    public int createFile(Model model, String fileName) {
        if (!fileName.contains(".")) fileName += ".txt";
        return UtilsOS.createFile(model.getPath() + "/" + fileName);
    }

    public void goTo(Model model, int index){
        String fileName = model.model.elementAt(index);
        String fullFilePath = model.getPath() + fileName;
        if (UtilsOS.isDirectory(fullFilePath)){
            model.setPath(fullFilePath);
        } else {
            textView = new TextView(fullFilePath);
        }
    }

    public void setDrive(Model model, String driveName){
        model.setPath(driveName);
    }

    public int createDirectory(Model model, String dirName) {
        return UtilsOS.createDirectory(model.getPath() + "/" + dirName);
    }

    List<Integer> find(Model model, String patternStr){
        return FindFileGroups.getFileGroup(model.model, patternStr);
    }

    public void back(Model model){
        String currentPath = model.getPath();
        if (currentPath.length() == 1) return;
        String newPath = currentPath.substring(0, currentPath.substring(0, currentPath.length() -1 ).lastIndexOf('/')) + "/";
        model.setPath(newPath);
    }

    public void changeText(Model model, String fileName){
        if (fileName == null) return;
        FileProccesing.replaceLineRepeats(model.getPath() + fileName);
    }

    public int rename(Model model, String oldName, String newName){
        if (!newName.contains(".")) newName += ".txt";
        return UtilsOS.renameFile(model.getPath(), oldName, newName);
    }

    public int delete(Model model, String fileName, boolean confirmation){
        if (!confirmation) return 3;
        return UtilsOS.deleteFile(model.getPath() + fileName);
    }

    public int copy(Model modelFrom, Model modelTo, int[] selectedIx){

        for (int i = 0; i < selectedIx.length; i++) {
            String selectedName = modelFrom.model.elementAt(selectedIx[i]);
            int exitCode = UtilsOS.copyFile(modelFrom.getPath() + selectedName, modelTo.getPath() + selectedName);
            if (exitCode != 0) return exitCode;
        }
        return 0;
    }

    public int move(Model modelFrom, Model modelTo, int[] selectedIx){
        int exitCode = copy(modelFrom, modelTo, selectedIx);
        if (exitCode != 0) return exitCode;
        for (int i = 0; i < selectedIx.length; i++) {
            exitCode = delete(leftModel, leftModel.model.elementAt(selectedIx[i]), true);
            if (exitCode != 0) return exitCode;
        }
        return exitCode;
    }

    public int makeFrequencyDictionary(Model model, int index){
        String fileName = model.model.elementAt(index);
        Map<String, Integer> dict = FileProccesing.findWordsOccurrences(model.getPath() + fileName);
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, Integer> entry : dict.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(";\n");
        }
        return UtilsOS.writeToFile(model.getPath() + "FD for " + fileName, result.toString());
    }

    public List<Integer> getLinkedFiles(Model model, int index) throws IOException {
         List<String> files = FileProccesing.findLinkedFiles(model.getPath(), model.model.elementAt(index));
         List<Integer> result = new LinkedList<>();
        for (String file : files) {
            result.add(model.model.indexOf(file));
        }
        return result;
    }

    public void openTableProcessor(Model model, String fileName){
        tableView = new TableProcessorView(model.getPath(), fileName);
    }
}
