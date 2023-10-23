package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;



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
    }

    /*
    * Function for CH01: Controleer of er geen variabelen worden gebruikt die niet gedefinieerd zijn.
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
