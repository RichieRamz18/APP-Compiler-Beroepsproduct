package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.Stack;


public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    private Stack<HashMap<String, ExpressionType>> variableScopeStack;


    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        variableScopeStack = new Stack<>();
        variableTypes.addFirst(new HashMap<>());
        for (int i = 0; i < variableTypes.getSize(); i++){
            HashMap<String, ExpressionType> scope = variableTypes.get(i);
            variableScopeStack.push(scope);
        }

        findAllVariables(ast.root);
        checkAST(ast.root);
    }

    private void checkAST(ASTNode node){
        checkVariables(node, variableScopeStack);
        checkOperationTypes(node);
        checkNoColorsInOperation(node);
        checkIfDeclarationValueMatchesProperty(node);
        checkIfConditionIsBoolean(node);

        node.getChildren().forEach(this::checkAST);
    }

    /**
     * Function for CH01 & CH06:
     * CH01: "Controleer of er geen variabelen worden gebruikt die niet gedefinieerd zijn."
     * CH06: "Controleer of variabelen niet buiten hun scope worden gebruikt."
     * Checks if the variable is defined in the current scope.
     * If the variable is not defined in the current scope, it checks if the variable is defined in a higher scope.
     * If the variable is not defined in a higher scope, it sets an error.
     *
     * @param toBeChecked: The node that needs to be checked
     * @param variableScopeStack: The stack that contains all the scopes
     * */
    private void checkVariables(ASTNode toBeChecked, Stack<HashMap<String, ExpressionType>> variableScopeStack) {
        if (toBeChecked instanceof VariableReference) {
            String name = ((VariableReference) toBeChecked).name;
            if (!isWithinScope(variableScopeStack, name)) {
                if (isWithinScope(variableScopeStack, name)) {
                    toBeChecked.setError("The variable " + name + " is used outside its scope!");
                } else {
                    toBeChecked.setError("Variable " + name + " is not defined (within this scope) and can't be used");
                }
            }
        } else {
            for (ASTNode child : toBeChecked.getChildren()) {
                checkVariables(child, variableScopeStack);
            }
        }
    }

    /**
     * This helper function checks if a variable is within the scope of the current scope.
     * If the variable is defined in the current scope or in a higher scope, it returns true.
     *
     * @param variableScopeStack: The stack that contains all the scopes
     *                          (the current scope is the last element in the stack)
     *                          (the highest scope is the first element in the stack)
     * @param variableName: The name of the variable that needs to be checked
     */
    private boolean isWithinScope(Stack<HashMap<String, ExpressionType>> variableScopeStack, String variableName) {
        for (HashMap<String, ExpressionType> scope : variableScopeStack) {
            if (scope.containsKey(variableName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function for CH02:
     * "Controleer of de operanden van de operaties plus en min van gelijke type zijn.
     * Je mag geen pixels bij percentages optellen bijvoorbeeld.
     * Controleer dat bij vermenigvuldigen minimaal een operand een scalaire waarde is."
     * Checks if the operands of the operations plus and minus are of the same type.
     * Checks if the operands of the operation multiply are of type scalar and pixel or percentage.
     * Else sets an error.
     *
     * @param toBeChecked: The node that needs to be checked
     * */
    private void checkOperationTypes(ASTNode toBeChecked) {
        if(toBeChecked.getChildren().size() != 1){
            if(toBeChecked instanceof AddOperation || toBeChecked instanceof SubtractOperation){
                if (((Operation) toBeChecked).lhs instanceof VariableReference){
                    if (variableTypes.getFirst().containsKey(((VariableReference) ((Operation) toBeChecked).lhs).name)) {
                        if (variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).lhs).name) != resolveExpressionType(((Operation) toBeChecked).rhs)) {
                            toBeChecked.setError("The operand types must be of the same type!");
                        }
                    }
                } else if (((Operation) toBeChecked).rhs instanceof VariableReference) {
                    if (variableTypes.getFirst().containsKey(((VariableReference) ((Operation) toBeChecked).rhs).name)) {
                        if (variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).rhs).name) != resolveExpressionType(((Operation) toBeChecked).lhs)) {
                            toBeChecked.setError("The operand types must be of the same type!");
                        }
                    }
                } else if (((Operation) toBeChecked).lhs instanceof VariableReference && ((Operation) toBeChecked).rhs instanceof VariableReference) {
                    if (variableTypes.getFirst().containsKey(((VariableReference) ((Operation) toBeChecked).lhs).name) && variableTypes.getFirst().containsKey(((VariableReference) ((Operation) toBeChecked).rhs).name)) {
                        if (variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).lhs).name) != variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).rhs).name)) {
                            toBeChecked.setError("The operand types must be of the same type!");
                        }
                    }
                } else if (resolveExpressionType(((Operation) toBeChecked).lhs) != resolveExpressionType(((Operation) toBeChecked).rhs)) {
                    toBeChecked.setError("The operand types must be of the same type!");
                }
            } else if (toBeChecked instanceof MultiplyOperation) {
                if ((resolveExpressionType(((MultiplyOperation) toBeChecked).lhs) != ExpressionType.SCALAR && resolveExpressionType(((MultiplyOperation) toBeChecked).rhs) != ExpressionType.SCALAR) ||
                        (resolveExpressionType(((MultiplyOperation) toBeChecked).lhs) == ExpressionType.SCALAR && resolveExpressionType(((MultiplyOperation) toBeChecked).rhs) == ExpressionType.SCALAR)) {
                    toBeChecked.setError("One of the operands must be of type scalar!");
                }
            }
        }
    }

    /**
     * Function for CH03:
     * "Controleer of er geen kleuren worden gebruikt in operaties (plus, min of keer)."
     * Checks if either the left or right side of an operation is a Colorliteral
     * or VariableReference with ExpressionType color, else sets an error.
     *
     * @param toBeChecked: The node that needs to be checked
     * */
    private void checkNoColorsInOperation(ASTNode toBeChecked){
        if (toBeChecked.getChildren().size() != 1){
            if (toBeChecked instanceof Operation){
                if (((Operation) toBeChecked).lhs instanceof VariableReference) {
                    if (variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).lhs).name) == ExpressionType.COLOR) {
                        toBeChecked.setError("Colors can't be used in operations!");
                    }
                }
                if (((Operation) toBeChecked).rhs instanceof VariableReference) {
                    if (variableTypes.getFirst().get(((VariableReference) ((Operation) toBeChecked).rhs).name) == ExpressionType.COLOR) {
                        toBeChecked.setError("Colors can't be used in operations!");
                    }
                }
                if (((Operation) toBeChecked).lhs instanceof ColorLiteral || ((Operation) toBeChecked).rhs instanceof ColorLiteral) {
                    toBeChecked.setError("Colors can't be used in operations!");
                }
            }
        }
    }

    /**
     * Function for CH04:
     * "Controleer of bij declaraties het type van de value klopt met de property.
     * Declaraties zoals width: #ff0000 of color: 12px zijn natuurlijk onzin."
     * Checks for the properties color and background-color if the value is of type color.
     * Checks for the properties width and height if the value is of type pixel or percentage.
     * Checks if the value is a VariableReference if the variable is of type color, pixel or percentage.
     * If the value is an Operation, checks if the result of the operation is of type color, pixel or percentage.
     * Else sets an error.
     *
     * @param toBeChecked: The node that needs to be checked
     * */
    private void checkIfDeclarationValueMatchesProperty(ASTNode toBeChecked) {
        if (toBeChecked.getChildren().size() != 1) {
            if (toBeChecked instanceof Declaration) {
                PropertyName propertyName = ((Declaration) toBeChecked).property;
                Expression expressionOfProperty = ((Declaration) toBeChecked).expression;
                if ((propertyName.name.equals("color") || propertyName.name.equals("background-color"))) {
                    if (expressionOfProperty instanceof Operation) {
                        toBeChecked.setError("Operations are not allowed in color declarations!");
                    }
                    if (expressionOfProperty instanceof VariableReference) {
                        if (variableTypes.getFirst().containsKey(((VariableReference) expressionOfProperty).name)) {
                            if (variableTypes.getFirst().get(((VariableReference) expressionOfProperty).name) != ExpressionType.COLOR) {
                                toBeChecked.setError("The variable in the declaration must be of type color!");
                            }
                        }
                    } else if (resolveExpressionType(expressionOfProperty) != ExpressionType.COLOR) {
                        toBeChecked.setError("The declaration must be of type color!");
                    }
                }
                if ((propertyName.name.equals("width")|| propertyName.name.equals("height"))) {
                    if (expressionOfProperty instanceof Operation) {
                        if (checkOperationResultType((Operation) expressionOfProperty) != ExpressionType.PIXEL && checkOperationResultType((Operation) expressionOfProperty) != ExpressionType.PERCENTAGE) {
                            toBeChecked.setError("Operations in width and height declarations must be of type pixel or percentage!");
                        }
                    }
                    if (expressionOfProperty instanceof VariableReference) {
                        if (variableTypes.getFirst().containsKey(((VariableReference) expressionOfProperty).name)) {
                            if (variableTypes.getFirst().get(((VariableReference) expressionOfProperty).name) != ExpressionType.PIXEL && variableTypes.getFirst().get(((VariableReference) expressionOfProperty).name) != ExpressionType.PERCENTAGE) {
                                toBeChecked.setError("Variables in width and height declarations must be of type pixel or percentage!");
                            }
                        }
                    } else if (resolveExpressionType(expressionOfProperty) != ExpressionType.PIXEL && resolveExpressionType(expressionOfProperty) != ExpressionType.PERCENTAGE) {
                        toBeChecked.setError("The declaration must be of type pixel or percentage!");
                    }
                }
            }
        }
    }

    /**
     * Function for CH05:
     * "Controleer of de conditie bij een if-statement van het type boolean is"
     * Checks if the condition of an if-statement is of type boolean.
     * If the condition is a VariableReference, checks if the variable is of type boolean.
     * Else sets an error.
     *
     * @param toBeChecked: The node that needs to be checked
     * */
    private void checkIfConditionIsBoolean(ASTNode toBeChecked){
        if (toBeChecked.getChildren().size() != 1 ){
            if (toBeChecked instanceof IfClause){
                if (((IfClause) toBeChecked).getConditionalExpression() instanceof VariableReference) {
                    if (variableTypes.getFirst().containsKey(((VariableReference) ((IfClause) toBeChecked).getConditionalExpression()).name)) {
                        if (variableTypes.getFirst().get(((VariableReference) ((IfClause) toBeChecked).getConditionalExpression()).name) != ExpressionType.BOOL) {
                            toBeChecked.setError("The variable in the if-statement must be of type boolean!");
                        }
                    }
                } else if (!(((IfClause) toBeChecked).getConditionalExpression() instanceof BoolLiteral)) {
                    toBeChecked.setError("The if-statement must be of type boolean!");
                }
            }
        }
    }

    /**
     * This method finds all the variables in the AST and adds them to the variableScopeStack.
     * It checks if the variable is already defined in the current scope, if so it sets an error.
     *
     * @param toBeFound: The node that needs to be checked
     * @param variableScopeStack: The stack that contains all the scopes
     */
    private void findAllVariables(ASTNode toBeFound, Stack<HashMap<String, ExpressionType>> variableScopeStack){
        if(toBeFound instanceof VariableAssignment){
            String name = ((VariableAssignment) toBeFound).name.name;
            ExpressionType expressionType = resolveExpressionType(((VariableAssignment) toBeFound).expression);
            variableScopeStack.peek().put(name, expressionType);
        } else if (toBeFound instanceof Stylerule) {
            //Betreden nieuwe scope, voegt een nieuwe map toe aan de stack
            variableScopeStack.push(new HashMap<>());
        }
        for(ASTNode child : toBeFound.getChildren()){
            findAllVariables(child, variableScopeStack);
        }
        if(toBeFound instanceof Stylerule){
            //Verlaat scope, verwijdert de map van de stack
            variableScopeStack.pop();
        }
    }

    /**
     * overloaded method for findAllVariables
     * This method finds all the variables in the AST and adds them to the variableScopeStack.
     *
     * @param toBeFound: The node that needs to be checked
     */
    private void findAllVariables(ASTNode toBeFound){
        findAllVariables(toBeFound, variableScopeStack);
    }

    /**
     * This method resolves the ExpressionType of the given Expression.
     * If the Expression is a VariableReference, it checks if the variable is defined and returns the ExpressionType of the variable.
     * Else it returns the ExpressionType of the Expression.
     *
     * @param expression: The expression that needs to be checked
     * @return ExpressionType: The ExpressionType of the given Expression
     */
    private ExpressionType resolveExpressionType(Expression expression){
        if(expression instanceof BoolLiteral){
            return ExpressionType.BOOL;
        } else if (expression instanceof ColorLiteral){
            return ExpressionType.COLOR;
        } else if (expression instanceof PercentageLiteral){
            return ExpressionType.PERCENTAGE;
        } else if (expression instanceof PixelLiteral){
            return ExpressionType.PIXEL;
        } else if (expression instanceof ScalarLiteral){
            return ExpressionType.SCALAR;
        } else if (expression instanceof Operation){
            return checkOperationResultType((Operation) expression);
        } else if (expression instanceof VariableReference){
            if(variableTypes.getFirst().containsKey(((VariableReference) expression).name)){
                return variableTypes.getFirst().get(((VariableReference) expression).name);
            }
        }
        return ExpressionType.UNDEFINED;
    }

    /**
     * This recursive method gets the left and right side of an ExpressionType, it then checks which type of operation it is
     * and returns the ExpressionType of the result of the operation.
     * If the operation is not valid, it returns ExpressionType.UNDEFINED.
     *
     * @param operation: The operation that needs to be checked (AddOperation, SubtractOperation or MultiplyOperation)
     * @return ExpressionType or Undefined: The ExpressionType of the result of the operation
     */
    private ExpressionType checkOperationResultType(Operation operation){
        ExpressionType left = resolveExpressionType(operation.lhs);
        ExpressionType right = resolveExpressionType(operation.rhs);

        if(operation instanceof MultiplyOperation) {
            if (left == ExpressionType.SCALAR) {
                if (right == ExpressionType.PIXEL) {
                    return ExpressionType.PIXEL;
                }
                if (right == ExpressionType.PERCENTAGE){
                    return ExpressionType.PERCENTAGE;
                }
            } else if (right == ExpressionType.SCALAR) {
                if (left == ExpressionType.PIXEL) {
                    return ExpressionType.PIXEL;
                }
                if (left == ExpressionType.PERCENTAGE){
                    return ExpressionType.PERCENTAGE;
                }
            }
        }
        if (operation instanceof AddOperation || operation instanceof SubtractOperation){
            if (left == ExpressionType.PIXEL) {
                if (left == right) {
                    return ExpressionType.PIXEL;
                }
            }
            if (right == ExpressionType.PERCENTAGE) {
                if (left == right) {
                    return ExpressionType.PERCENTAGE;
                }
            }
        }

        return ExpressionType.UNDEFINED;
    }
}
