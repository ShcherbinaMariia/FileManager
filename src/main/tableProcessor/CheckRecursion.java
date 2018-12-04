package main.tableProcessor;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class CheckRecursion {

    private HashSet<CellCoordinates> allInvolvedCells;
    private TableModel model;

    public CheckRecursion(TableModel model){
        this.model = model;
    }

    private void checkRecursionOnCell(CellCoordinates cellCoordinates) {

        Vector rowVector = (Vector) model.getDataVector().elementAt(cellCoordinates.row);
        Cell cell = (Cell) rowVector.get(cellCoordinates.column);

        cell.cellsDependOn.forEach(dependedCellCoordinates -> {
            if (!allInvolvedCells.contains(dependedCellCoordinates)){
                allInvolvedCells.add(dependedCellCoordinates);
                checkRecursionOnCell(dependedCellCoordinates);
            }
        });
    }

    public boolean checkIfValid(CellCoordinates cellCoordinates, Set<CellCoordinates> cellsDependOn) {

        allInvolvedCells = new HashSet<>(cellsDependOn);

        cellsDependOn.forEach(this::checkRecursionOnCell);

        return !allInvolvedCells.contains(cellCoordinates);
    }
}
