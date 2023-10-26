package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.HashMap;
import java.util.LinkedList;

public class Evaluator implements Transform {

    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();
    }

    private void transformStyleSheet(Stylesheet stylesheet){
        variableValues.addFirst(new HashMap<>());

    }

    private void transformStylerule(Stylerule stylerule){
        variableValues.addFirst(new HashMap<>());
    }

//    private void transformVariableAssignment(VariableAssignment variableAssignment){
//        variableValues.getFirst().put(variableAssignment.name.name, variableAssignment.value);
//    }

    private void transformIfClause(IfClause ifClause){
        variableValues.addFirst(new HashMap<>());
    }

    private Literal transformOperation(Operation operation){

    }

    private Literal tranformToLiteral(Literal literal){
        return null;
    }



    
}
