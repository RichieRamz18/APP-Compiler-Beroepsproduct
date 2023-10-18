grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COMMA: ',';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
DIV: '/';
ASSIGNMENT_OPERATOR: ':=';


//--- PARSER: ---
//stylesheet: styleRule+ EOF | (variableAssignment | styleRule)+ EOF;
//styleRule: selector body;
//
//selector: tagSelector | idSelector | classSelector;
//body: OPEN_BRACE (declarations | variableAssignment | ifClause)+ CLOSE_BRACE;
//
//tagSelector: LOWER_IDENT;
//idSelector: ID_IDENT;
//classSelector: CLASS_IDENT;
//
//declarations: declaration+ | ifClause+;
//declaration: propertyName COLON expression SEMICOLON;
//propertyName: LOWER_IDENT;
//
//expression: literal | expression (MUL) expression | expression (PLUS | MIN) expression;
//
//colorLiteral: COLOR;
//boolLiteral: TRUE | FALSE;
//percentageLiteral: PERCENTAGE;
//pixelLiteral: PIXELSIZE;
//scalarLiteral: SCALAR;
//literal: colorLiteral | boolLiteral | percentageLiteral | pixelLiteral | scalarLiteral | variableReference;
//
//variableAssignment: variableReference ASSIGNMENT_OPERATOR expression SEMICOLON;
//variableReference: CAPITAL_IDENT;
//
//ifClause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE body (ELSE body)?;
//elseClause: ELSE body;


//--------------------------------------------------------

//Stylesheet
//stylesheet: variableAssignment* styleRule* EOF;
//
//styleRule: selector OPEN_BRACE ruleBody CLOSE_BRACE;
//declaration: propertyName COLON expression SEMICOLON;
//propertyName: LOWER_IDENT;
//
//variableAssignment: variableReference ASSIGNMENT_OPERATOR expression+ SEMICOLON;
//variableReference: CAPITAL_IDENT;
//
//ifClause: IF BOX_BRACKET_OPEN (variableReference | boolLiteral ) BOX_BRACKET_CLOSE OPEN_BRACE ruleBody CLOSE_BRACE elseClause?;
//elseClause: ELSE OPEN_BRACE ruleBody CLOSE_BRACE;
//
//expression: expression (MUL) expression #multiplyOperation |
//            expression (DIV) expression #divideOperation |
//            expression (PLUS) expression #addOperation |
//            expression (MIN) expression #subtractOperation |
//            literal #lit;
//
//boolLiteral: TRUE | FALSE;
//colorLiteral: COLOR;
//percentageLiteral: PERCENTAGE;
//pixelLiteral: PIXELSIZE;
//scalarLiteral: SCALAR;
//literal: boolLiteral | colorLiteral | percentageLiteral | pixelLiteral | scalarLiteral | variableReference;
//
//classSelector: CLASS_IDENT;
//tagSelector: LOWER_IDENT;
//idSelector: ID_IDENT | COLOR;
//selector: (tagSelector | idSelector | classSelector) (COMMA selector)*;
//
//ruleBody: (declaration | ifClause | variableAssignment)*;

//-------------------------------------------------------


//--- PARSER: ---
stylesheet: (variableAssignment | stylerule)+;

stylerule: selector properties;
selector: (CLASS_IDENT | ID_IDENT | LOWER_IDENT);

// Calculations
operationValue: scalarValue | pixelValue | variableReference;
operation: add | subtract | multiply;
multiply: operationValue MUL (operationValue | operation);
add: operationValue PLUS (operationValue | operation);
subtract: operationValue MIN (operationValue | operation);

// Values
scalarValue: SCALAR;
colorValue: COLOR;
pixelValue: PIXELSIZE;
percentageValue: PERCENTAGE;
boolValue: (TRUE | FALSE);

// Variables
variableReference: CAPITAL_IDENT;
variableValue: (colorValue | pixelValue | boolValue | percentageValue);
variableAssignment: variableReference ASSIGNMENT_OPERATOR variableValue SEMICOLON;

// Properties
properties: OPEN_BRACE (property | ifClause)* CLOSE_BRACE;
propertyName: 'color' | 'background-color' | 'width' | 'height';
propertyValue: (colorValue | pixelValue | boolValue | percentageValue | variableReference | operation);
property: propertyName COLON propertyValue SEMICOLON;

// If-else
ifClause: IF BOX_BRACKET_OPEN (boolValue | variableReference) BOX_BRACKET_CLOSE properties elseClause?;
elseClause: ELSE properties;