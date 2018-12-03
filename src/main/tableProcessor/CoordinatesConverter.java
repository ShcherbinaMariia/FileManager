package main.tableProcessor;

public class CoordinatesConverter {

    public static String alphabet = "ABCDEFGHIKLMNOPQRSTVXYZ";
    public static int numberOfSymbols = alphabet.length();
    public static int codeA = 65;

    public static int getIntColumn(String columnName) {
        int intColumn = 0;
        for (int i = 0; i < columnName.length(); i++) {
            intColumn *= numberOfSymbols;
            intColumn += (int) columnName.charAt(i) - codeA;
        }
        return intColumn;
    }

    public static String getColumnNameByIndex(int i) {
        String columnName = "";
        do {
            columnName = alphabet.charAt(i % numberOfSymbols) + columnName;
            i /= numberOfSymbols;
        } while (i != 0);
        return columnName;
    }

    public static CellCoordinates getCoordinatesByString(String id){
        int i = 0;
        while(id.charAt(i)>='A'){
            i++;
        }
        String txtColumn = id.substring(0, i);
        int row = Integer.parseInt(id.substring(i));
        int column = getIntColumn(txtColumn);
        return new CellCoordinates(row, column);
    }
}
