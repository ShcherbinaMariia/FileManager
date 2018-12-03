package main.tableProcessor;

public class CellCoordinates {
    public int row;
    public int column;

    CellCoordinates(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != CellCoordinates.class)
            return false;
        CellCoordinates cellCoordinates = (CellCoordinates) obj;
        if (cellCoordinates.column == this.column && cellCoordinates.row == this.row)
            return true;
        return false;
    }
}
