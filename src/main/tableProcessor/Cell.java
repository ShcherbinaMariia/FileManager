package main.tableProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell{

    Double value = null;
    String expression = null;
    Set<CellCoordinates> linkedCells = new HashSet<>();
    Set<CellCoordinates> cellsDependOn = new HashSet<>();

    Cell(Double value, String expression, Set<CellCoordinates> linkedCells, Set<CellCoordinates> cellsDependOn){
        this.value = value;
        this.expression = expression;
        this.linkedCells = linkedCells;
        this.cellsDependOn = cellsDependOn;
    }

    Cell(){}
}
