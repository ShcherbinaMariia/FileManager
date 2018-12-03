grammar Grammar;

compileUnit
    :    expression EOF
    ;

expression :
	LEFT_PARENTHESIS expression RIGHT_PARENTHESIS  #ParenthesizedExpression
	| operatorToken=(MIN | MAX) LEFT_PARENTHESIS expression COMMA expression RIGHT_PARENTHESIS #MinMaxExpression
	| expression operatorToken=(MULTIPLY | DIVIDE) expression #MultiplicativeExpression
	| expression operatorToken=(ADD | SUBTRACT) expression #AdditiveExpression
	| DOUBLE_NUMBER #NumberExpression
	| CELL_NAME #CellIdExpression
	| EOF #EofThis
	;

WS
    :   [ \t\r\n]+ -> skip
    ;

DOUBLE_NUMBER
    :   INT_NUMBER+ ('.' INT_NUMBER+)?
    ;

INT_NUMBER
    :   [0-9]+
    ;

LETTERS
    :   [A-Z]+
    ;

CELL_NAME
    :   LETTERS+ INT_NUMBER+
    ;

LEFT_PARENTHESIS
    :   '('
    ;

RIGHT_PARENTHESIS
    :   ')'
    ;

ADD
    :   '+'
    ;

SUBTRACT
    :   '-'
    ;

MULTIPLY
    :   '*'
    ;

DIVIDE
    :   '/'
    ;
MIN
    :   'min'
    ;
MAX
    :   'max'
    ;
COMMA
    :   ','
    ;