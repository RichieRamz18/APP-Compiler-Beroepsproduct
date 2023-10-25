package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.Map;


public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        variableTypes.addFirst(new HashMap<>());

        findAllVariables(ast.root);
        checkAST(ast.root);
    }

    private void checkAST(ASTNode node){
        checkUndefinedVariables(node);
        //checkOperationTypes(node); TO DO: implementeren
        checkNoColorsInOperation(node);
        checkIfConditionIsBoolean(node);
        checkIfVariablesAreUsedInScope(node);

        node.getChildren().forEach(this::checkAST);
    }

    private void checkScope(VariableReference reference){
        boolean exists = false;

        for (int i = 0; i < variableTypes.getSize(); i++){
            if (variableTypes.get(i).containsKey(reference.name)){
                exists = true;
            }
        }
        if (!exists){
            reference.setError("The variable is used outside its scope!");
        }
    }

    /*
    * Function for CH01:
    * "Controleer of er geen variabelen worden gebruikt die niet gedefinieerd zijn."
    *
    * @param toBeChecked: The node that needs to be checked
    * */
    private void checkUndefinedVariables(ASTNode toBeChecked) {
        if(toBeChecked.getChildren().size() != 1){
            if(toBeChecked instanceof VariableReference){
                String name = ((VariableReference) toBeChecked).name;
                if(!variableTypes.getFirst().containsKey(name)) {
                    toBeChecked.setError("Variable " + name + " is not defined and can't be used");
                }
            }
        }
    }

    /*
     * Function for CH02:
     * "Controleer of de operanden van de operaties plus en min van gelijke type zijn.
     * Je mag geen pixels bij percentages optellen bijvoorbeeld.
     * Controleer dat bij vermenigvuldigen minimaal een operand een scalaire waarde is."
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

                    }
                }
            }
        }
    }

    /*
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

    /*
     * Function for CH04:
     * "Controleer of bij declaraties het type van de value klopt met de property.
     * Declaraties zoals width: #ff0000 of color: 12px zijn natuurlijk onzin."
     *
     * @param toBeChecked: The node that needs to be checked
     * */
//    private void checkIfDeclarationValueMatchesProperty(ASTNode toBeChecked){
//        if (toBeChecked.getChildren().size() != 1){
//            if (toBeChecked instanceof Declaration) {
//                if (((Declaration) toBeChecked).property.name.equals(AllowedAttributes.COLOR.attribute))
//            }
//        }
//    }

    /*
     * Function for CH05:
     * "Controleer of de conditie bij een if-statement van het type boolean is"
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

    /*
     * Function for CH06:
     * "Controleer of variabelen enkel binnen hun scope gebruikt worden"
     * Recursive function that checks if a variable is used in its scope.
     * If the variable is used outside its scope, an error is set.
     *
     * @param toBeChecked: The node that needs to be checked
     * */
    private void checkIfVariablesAreUsedInScope(ASTNode toBeChecked, HashMap<String, ExpressionType> currentScopeVariables){
        if(toBeChecked instanceof VariableReference){
            String name = ((VariableReference) toBeChecked).name;
            if(!currentScopeVariables.containsKey(name)){
                toBeChecked.setError("The variable " + name + " is used outside its scope!");
            }
        } else {
            for (ASTNode child : toBeChecked.getChildren()) {
                checkIfVariablesAreUsedInScope(child, currentScopeVariables);
            }
        }
    }

    private void findAllVariables(ASTNode toBeFound){
        if(toBeFound instanceof VariableAssignment){
            String name = ((VariableAssignment) toBeFound).name.name;
            ExpressionType expressionType = resolveExpressionType(((VariableAssignment) toBeFound).expression);
            variableTypes.getFirst().put(name, expressionType);
        }
        toBeFound.getChildren().forEach(this::findAllVariables);
    }

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
            //return //checkOperationResultType implementeren
        } else if (expression instanceof VariableReference){
            if(variableTypes.getFirst().containsKey(((VariableReference) expression).name)){
                return variableTypes.getFirst().get(((VariableReference) expression).name);
            }
        }
        return ExpressionType.UNDEFINED;
    }

    private ExpressionType checkOperationResultType(Operation operation){
        return ExpressionType.UNDEFINED;
        //TODO: implementeren
    }
}
