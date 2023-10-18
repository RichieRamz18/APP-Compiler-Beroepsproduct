package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;



public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        variableTypes.addFirst(new HashMap<>());



    }

    private void checkUndefinedVariables(ASTNode toBeChecked) {
        if(toBeChecked.getChildren().size() != 1){
            if(toBeChecked instanceof VariableReference){
                // if(!variableTypes.contains(toBeChecked)){
                //     // throw new Exception("Variable not defined");
                // }
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

    }

    
}
