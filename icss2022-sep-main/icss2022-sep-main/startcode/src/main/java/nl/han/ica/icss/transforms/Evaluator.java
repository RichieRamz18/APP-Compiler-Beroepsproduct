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


    /**
     * This method returns the literal from the given expression
     *
     * @param expression the given expression
     * @return literal of null
     */
    private Literal getLiteralFromExpression (Expression expression){
        Literal literal = null;
        if (expression instanceof Operation){
            literal = calculateOperation((Operation) expression);
        } else if (expression instanceof VariableReference) {
            VariableReference variableReference = (VariableReference) expression;
            for (int i = 0; i < variableValues.getSize(); i++) {
                HashMap<String, Literal> map = variableValues.get(i);
                if(map.containsKey(variableReference.name)){
                    literal = map.get(variableReference.name);
                    break;
                }
            }
        } else if (expression instanceof Literal){
            literal = (Literal) expression;
        }
        return literal;
    }

    private Literal calculateExpression(Expression lhs) {
    }

    /**
     * This method sets the left and right part of the operation to a literal
     * using the calculateExpression method. It then decides which type of operation it is
     * and returns the result of the operation with the compliant calculateOperation method
     *
     * @param operation the given operation
     * @return literal with the calculated value as result of the operation
     */
    /
    private Literal calculateOperation(Operation operation) {
        Literal left = calculateExpression(operation.lhs);
        Literal right = calculateExpression(operation.rhs);

        if (operation instanceof MultiplyOperation) {
            return calculateMultiplyOperation(left, right);
        } else if (operation instanceof AddOperation){
            return calculateAddOperation(left, right);
        } else if (operation instanceof SubtractOperation){
            return calculateSubtractOperation(left, right);
        }
        return null;
    }

    /**
     * This method calculates the outcome of the add operation
     *
     * @param left the left part of the operation
     * @param right the right part of the operation
     * @return literal with the calculated value as result of the operation
     */
    private Literal calculateAddOperation(Expression left, Expression right) {
        if (left instanceof PercentageLiteral) {
            int outcome = ((PercentageLiteral) left).value + ((PercentageLiteral) right).value;
            return new PercentageLiteral(outcome);
        }
        if (left instanceof PixelLiteral) {
            int outcome = ((PixelLiteral) left).value + ((PixelLiteral) right).value;
            return new PixelLiteral(outcome);
        }
        if (left instanceof ScalarLiteral) {
            int outcome = ((ScalarLiteral) left).value + ((ScalarLiteral) right).value;
            return new ScalarLiteral(outcome);
        }
        return null;
    }

    private Literal calculateSubtractOperation(Literal left, Literal right) {
    }



    private Literal calculateMultiplyOperation(Literal left, Literal right) {
    }


}
