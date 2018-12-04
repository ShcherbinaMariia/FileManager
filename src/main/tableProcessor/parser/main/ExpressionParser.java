package main.tableProcessor.parser.main;

import main.tableProcessor.CellCoordinates;
import main.tableProcessor.CheckRecursion;
import main.tableProcessor.TableModel;
import org.antlr.v4.runtime.*;

import java.util.Set;

public class ExpressionParser
    {
        public static Double evaluate(TableModel model, String expression, CellCoordinates cellCoordinates, boolean findLinked)
        {
            if (expression == null)
                return null;

            GrammarLexer lexer = new GrammarLexer(new ANTLRInputStream(expression));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GrammarParser parser = new GrammarParser(tokens);

            GrammarParser.CompileUnitContext compileUnitContext = parser.compileUnit();
            Visitor visitor = new Visitor(model);
            Double value = visitor.visit(compileUnitContext);

            if (findLinked) {

                Set<CellCoordinates> linkedCells = visitor.getLinkedCells();

                if (new CheckRecursion(model).checkIfValid(cellCoordinates, linkedCells)) {
                    model.setCellsDependOn(cellCoordinates, linkedCells);
                    linkedCells.forEach(linkedCellCoordinates -> model.setLinkedCell(cellCoordinates, linkedCellCoordinates));
                }
                else{
                    return null;
                }
            }

            return value;
        }
    }
