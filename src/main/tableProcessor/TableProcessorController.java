package main.tableProcessor;

import com.google.gson.Gson;
import main.UtilsOS;

import java.io.IOException;

public class TableProcessorController {

    TableModel model;
    String currentPath;
    String currentFileName = null;

    void addRow(int index){
        model.insertRow(index);
    }

    void addColumn(int index){
        model.addColumn(index);
    }

    void deleteRow(int index, boolean confirmation) {
        if (!confirmation) return;
        model.removeRow(index);
    }

    void deleteColumn(String index, boolean confirmation){
        if (!confirmation) return;
        model.removeColumn(index);
    }

    void save(String name){
        UtilsOS.writeToFile(currentPath + "/" + name, model.getJSONTable());
    }
    boolean set(int row, int column, String expression){
        model.setValueAt(expression, row, column);
        model.fireTableStructureChanged();
        String cellExpression = model.getCell(row,column).expression;
        return (cellExpression != null && cellExpression.equals(expression));
    }

    void newTable(String parameters){
        String[] splitted = parameters.replaceAll("\\s", "").split(",");
        model = new TableModel(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
    }

    void open(String fileName) throws IOException {
        currentFileName = fileName;
        String jsonTable = UtilsOS.readFile(currentPath + "/" + fileName);
        model = new TableModel(jsonTable);
    }
}
