package main.tableProcessor.parser.main;

import main.tableProcessor.CellCoordinates;
import main.tableProcessor.CoordinatesConverter;
import main.tableProcessor.TableModel;

import java.util.HashSet;
import java.util.Set;

public class Visitor extends GrammarBaseVisitor<Double>{

    TableModel model;
    Set<CellCoordinates> linkedCells = new HashSet<>();

    Visitor(TableModel model){
        this.model = model;
    }

    @Override
    public Double defaultResult(){
        return  0.0;
    }

    @Override
    public Double aggregateResult(Double aggregate, Double nextResult) {
        return nextResult + aggregate;
    }

    @Override
    public Double visitNumberExpression(GrammarParser.NumberExpressionContext ctx){
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitCellIdExpression(GrammarParser.CellIdExpressionContext ctx){
        String id = ctx.getChild(0).getText();
        CellCoordinates cellCoordinates = CoordinatesConverter.getCoordinatesByString(id);
        linkedCells.add(cellCoordinates);
        Double value = model.getValueAt(cellCoordinates.row, cellCoordinates.column);
        if (value == null){
            return Double.NaN;
        }
        return value;
    }
    @Override
    public Double visitEofThis(GrammarParser.EofThisContext ctx){
        System.out.print("EOF!");
        return 0.0;
    }
    @Override
    public Double visitAdditiveExpression(GrammarParser.AdditiveExpressionContext ctx) {
        Double a = visit(ctx.getChild(0));
        Double b = visit(ctx.getChild(2));
        if (ctx.getChild(1).getText().equals("+")){
            return a + b;
        }
        else{
            return a - b;
        }
    }
    @Override
    public Double visitMultiplicativeExpression(GrammarParser.MultiplicativeExpressionContext ctx) {
        Double a = visit(ctx.getChild(0));
        Double b = visit(ctx.getChild(2));
        if (ctx.getChild(1).getText().equals("*")){
            return a * b;
        }
        else{
            return a / b;
        }
    }
    @Override
    public Double visitMinMaxExpression(GrammarParser.MinMaxExpressionContext ctx) {
        Double a = visit(ctx.getChild(2));
        Double b = visit(ctx.getChild(4));
        if (ctx.getChild(0).getText().equals("min")){
            return Math.min(a, b);
        }
        else{
            return Math.max(a ,b);
        }
    }
    public Set<CellCoordinates> getLinkedCells(){
        return linkedCells;
    }
}
