package main.tableProcessor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import main.tableProcessor.parser.main.ExpressionParser;

import javax.swing.table.DefaultTableModel;
import java.util.*;

import com.alibaba.fastjson.JSON;

public class TableModel extends DefaultTableModel {

    public CheckRecursion checker = new CheckRecursion(this);

    private Set<CellCoordinates> deserializeCells(JSONArray JsonCells) {
        Set<CellCoordinates> cells = new HashSet<>();
        for (int k = 0; k < JsonCells.size(); k++) {
            JSONObject JsonCellCoordinates = JsonCells.getJSONObject(k);
            int row = JsonCellCoordinates.getInteger("row");
            int column = JsonCellCoordinates.getInteger("column");
            cells.add(new CellCoordinates(row, column));
        }
        return cells;
    }

    private Cell deserializeCell(JSONObject cellObject) {
        Double value = null;
        if (cellObject.containsKey("value"))
            value = cellObject.getDouble("value");
        String expression = null;
        if (cellObject.containsKey("expression"))
            expression = cellObject.getString("expression");
        JSONArray JsonLinkedCells = cellObject.getJSONArray("linkedCells");
        JSONArray JsonCellsDependOn = cellObject.getJSONArray("cellsDependOn");
        return new Cell(value, expression, deserializeCells(JsonLinkedCells), deserializeCells(JsonCellsDependOn));
    }

    TableModel(String JSONstring) {

        JSONObject object = JSON.parseObject(JSONstring, JSONObject.class);
        JSONArray JsonDataArray = object.getJSONArray("dataVector");
        Vector<Vector<Cell>> dataVector = new Vector<>();
        for (int i = 0; i < JsonDataArray.size(); i++) {
            JSONArray JsonRowArray = JsonDataArray.getJSONArray(i);
            Vector<Cell> rowVector = new Vector<>();
            for (int j = 0; j < JsonRowArray.size(); j++) {
                JSONObject cellObject = JsonRowArray.getJSONObject(j);
                rowVector.add(deserializeCell(cellObject));
            }
            dataVector.add(rowVector);
        }
        JSONArray JsonColumnIdentifiers = object.getJSONArray("columnIdentifiers");
        Vector<String> columnIdentifiers = new Vector<>();
        for (int i = 0; i < JsonColumnIdentifiers.size(); i++) {
            columnIdentifiers.add(JsonColumnIdentifiers.getString(i));
        }
        setDataVector(dataVector, columnIdentifiers);
    }

    TableModel(int numberOfRows, int numberOfColumns) {

        String[] columnNames = new String[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            columnNames[i] = CoordinatesConverter.getColumnNameByIndex(i);
        }
        Cell[][] cells = new Cell[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                cells[i][j] = new Cell();
            }
        }
        setDataVector(cells, columnNames);
    }

    public Cell getCell(int row, int column) {
        Vector rowVector = (Vector) dataVector.elementAt(row);
        return (Cell) rowVector.elementAt(column);
    }

    public String getExpressionAt(int row, int column) {
        Cell cell = getCell(row, column);
        return cell.expression;
    }

    @Override
    public Double getValueAt(int row, int column) {
        if (row >= getRowCount() || column >= getColumnCount())
            return Double.NaN;
        Cell cell = getCell(row, column);
        return cell.value;
    }

    public void setCellsDependOn(CellCoordinates source, Set<CellCoordinates> cellsDependsOn) {
        Cell cell = getCell(source.row, source.column);
        cell.cellsDependOn.forEach(cellCoordinates -> forget(cellCoordinates, source));
        cell.cellsDependOn = cellsDependsOn;
    }

    private void forget(CellCoordinates source, CellCoordinates linked) {
        Cell cell = getCell(source.row, source.column);
        cell.linkedCells.remove(linked);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {

        Vector rowVector = (Vector) dataVector.elementAt(row);
        Cell cell = (Cell) rowVector.get(column);
        CellCoordinates currentCellCoordinates = new CellCoordinates(row, column);

        String newExpression = (String) aValue;
        Double newValue = ExpressionParser.evaluate(this, newExpression, currentCellCoordinates, true);

        if (newValue != null){
            cell.expression = (String) aValue;
            cell.value = newValue;
            rowVector.set(column, cell);
            cell.linkedCells.forEach(cellCoordinates -> refreshValueAt(cellCoordinates.row, cellCoordinates.column));
        }
    }

    public void refreshValueAt(int row, int column) {
        Vector rowVector = (Vector) dataVector.elementAt(row);
        Cell cell = (Cell) rowVector.get(column);
        cell.value = ExpressionParser.evaluate(this, cell.expression, new CellCoordinates(row, column), false);
        rowVector.set(column, cell);
        cell.linkedCells.forEach(cellCoordinates -> refreshValueAt(cellCoordinates.row, cellCoordinates.column));
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Cell.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void insertRow(int index) {
        int numberOfColumns = getColumnCount();
        Cell[] row = new Cell[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            row[i] = new Cell();
        }
        insertRow(index, row);
    }

    public void setLinkedCell(CellCoordinates linked, CellCoordinates source) {
        Cell cell = getCell(source.row, source.column);
        if (!cell.linkedCells.contains(linked))
            cell.linkedCells.add(linked);
    }

    public void addColumn(int index) {
        int numberOfRows = getRowCount();
        Cell[] column = new Cell[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            column[i] = new Cell();
        }
        addColumn(CoordinatesConverter.getColumnNameByIndex(getColumnCount()), column);
        for (int i = 0; i < numberOfRows; i++) {
            Vector<Cell> rowVector = (Vector) dataVector.elementAt(i);
            for (int j = rowVector.size() - 1; j > index; j--) {
                Cell tmp = rowVector.elementAt(j);
                rowVector.setElementAt(rowVector.elementAt(j - 1), j);
                rowVector.setElementAt(tmp, j - 1);
            }
        }
    }

    public void removeColumn(int index) {
        int numberOfRows = getRowCount();
        columnIdentifiers.remove(columnIdentifiers.size() - 1);
        for (int row = 0; row < numberOfRows; row++) {
            Vector rowVector = (Vector) dataVector.elementAt(row);
            rowVector.remove(index);
            dataVector.setElementAt(rowVector, row);
        }
        fireTableStructureChanged();
    }
}
