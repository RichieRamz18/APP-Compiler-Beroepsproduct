package nl.han.ica.icss.transforms;

import com.google.errorprone.annotations.Var;
import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Evaluator implements Transform {

    private IHANLinkedList<HashMap<String, Literal>> variableValues;
    private IHANLinkedList<HashMap<String, Literal>> variables;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
        variables = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();
        variableValues.addFirst(new HashMap<>());
        variables = new HANLinkedList<>();
        variables.addFirst(new HashMap<>());

        evaluateExpression(ast.root.getChildren(), ast.root);
        evaluateIfStatements(ast.root.getChildren(), ast.root);
    }

    /**
     * This method evaluates the expression of the given children and parent
     * For every child is decided which type the parent is, then it is checked if the child is an operation.
     * Then sets the parents expression to the calculated expression.
     * When the child is a VariableReference, the parents expression is set to the value of the variable.
     *
     * @param children the children of the given parent
     * @param parent the given parent
     */
    private void evaluateExpression(ArrayList<ASTNode> children, ASTNode parent) {
        HashMap<String, Literal> hashMap = new HashMap<>();
        variableValues.addFirst(hashMap);
        for (ASTNode child : children) {
            if (child  instanceof VariableAssignment){
                hashMap.put(((VariableAssignment) child).name.name, getLiteralFromExpression(((VariableAssignment) child).expression));
            }
            if (parent instanceof Declaration) {
                Declaration declaration = (Declaration) parent;
                if (child instanceof Operation) {
                    declaration.expression = calculateExpression((Expression) child);
                }
                if (child instanceof VariableReference) {
                    VariableReference variableReference = (VariableReference) child;
                    for (int i = 0; i < variableValues.getSize(); i++) {
                        HashMap<String, Literal> map = variableValues.get(i);
                        if (map.containsKey(variableReference.name)) {
                            ((Declaration) parent).expression = map.get(variableReference.name);
                        }
                    }
                }
            }
            evaluateExpression(child.getChildren(), child);
        }
        variableValues.removeFirst();
    }


//    private void transformStyleSheet(Stylesheet stylesheet){
//        variableValues.addFirst(new HashMap<>());
//
//    }
//    private void transformStylerule(Stylerule stylerule){
//
//    }
//    private void transformVariableAssignment(VariableAssignment variableAssignment){
//        variableValues.getFirst().put(variableAssignment.name.name, variableAssignment.value);
//    }
//    private void transformIfClause(IfClause ifClause){
//        variableValues.addFirst(new HashMap<>());
//    }


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

    /**
     * This method decides the type of the expression and can return three different types of literals:
     * 1. returns the retrieved variable value if the expression is a VariableReference
     * 2. returns the calculated value of the operation if the expression is an Operation
     * 3. returns the literal if the expression is a literal
     * or returns null
     *
     * @param expression the given expression
     * @return literal or null
     */
    private Literal calculateExpression(Expression expression) {
        if (expression instanceof VariableReference) {
            VariableReference variableReference = (VariableReference) expression;
            for (int i = 0; i < variableValues.getSize(); i++){
                HashMap<String, Literal> map = variableValues.get(i);
                if (map.containsKey(variableReference.name)){
                    return map.get(variableReference.name);
                }
            }
        }
        if (expression instanceof Operation) {
            return calculateOperation((Operation) expression);
        }
        if (expression instanceof Literal) {
            return (Literal) expression;
        }
        return null;
    }

    /**
     * This method sets the left and right part of the operation to a literal
     * using the calculateExpression method. It then decides which type of operation it is
     * and returns the result of the operation with the compliant calculateOperation method
     *
     * @param operation the given operation
     * @return literal with the calculated value as result of the operation
     */
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
     * @param left the left part of the expression (operation)
     * @param right the right part of the expression (operation)
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

    /**
     * This method calculates the outcome of the subtract operation
     *
     * @param left the left part of the expression (operation)
     * @param right the right part of the expression (operation)
     * @return literal with the calculated value as result of the operation
     */
    private Literal calculateSubtractOperation(Expression left, Expression right) {
        if (left instanceof PercentageLiteral) {
            int outcome = ((PercentageLiteral) left).value - ((PercentageLiteral) right).value;
            return new PercentageLiteral(outcome);
        }
        if (left instanceof PixelLiteral) {
            int outcome = ((PixelLiteral) left).value - ((PixelLiteral) right).value;
            return new PixelLiteral(outcome);
        }
        if (left instanceof ScalarLiteral) {
            int outcome = ((ScalarLiteral) left).value - ((ScalarLiteral) right).value;
            return new ScalarLiteral(outcome);
        }
        return null;
    }


    /**
     * This method calculates the outcome of the multiply operation
     *
     * @param left the left part of the expression (operation)
     * @param right the right part of the expression (operation)
     * @return literal with the calculated value as result of the operation
     */
    private Literal calculateMultiplyOperation(Expression left, Expression right) {
        if (left instanceof ScalarLiteral) {
            if (right instanceof PercentageLiteral) {
                int outcome = ((ScalarLiteral) left).value * ((PercentageLiteral) right).value;
                return new PercentageLiteral(outcome);
            }
            if (right instanceof PixelLiteral) {
                int outcome = ((ScalarLiteral) left).value * ((PixelLiteral) right).value;
                return new PixelLiteral(outcome);
            }
        }
        if (right instanceof ScalarLiteral) {
            if (left instanceof PercentageLiteral) {
                int outcome = ((PercentageLiteral) left).value * ((ScalarLiteral) right).value;
                return new PercentageLiteral(outcome);
            }
            if (left instanceof PixelLiteral) {
                int outcome = ((PixelLiteral) left).value * ((ScalarLiteral) right).value;
                return new PixelLiteral(outcome);
            }
        }
        if (left instanceof ScalarLiteral && right instanceof ScalarLiteral){
            int outcome = ((ScalarLiteral) left).value * ((ScalarLiteral) right).value;
            return new ScalarLiteral(outcome);
        }
        return null;
    }

    /**
     * This method evaluates for every child, if the child is an ifClause, using the evaluateIfClause method.
     *
     * @param children the children of the given parent
     * @param parent the given parent node to evaluate
     */
    private void evaluateIfStatements(List<ASTNode> children, ASTNode parent) {
        for (ASTNode child : children) {
            if (child instanceof IfClause) {
                evaluateIfClause((IfClause) child, parent);
            }
            evaluateIfStatements(child.getChildren(), child);
        }
    }

    /**
     * This method checks if the condition of the given ifClause is true or false.
     * If the condition is true, the body of the ifClause is added to the parent.
     * If the condition is false, the ifClause is removed from the parent.
     *
     * @param ifClause the given ifClause
     * @param parent the given parent
     * @return list of ASTNodes, to keep track of the nodes that are added to the parent
     */
    private List<ASTNode> evaluateIfClause(IfClause ifClause, ASTNode parent) {
        List<ASTNode> values = new ArrayList<>();

        if (ifClause.conditionalExpression instanceof BoolLiteral || ifClause.conditionalExpression instanceof VariableReference) {
            if (getBoolLiteral(ifClause.conditionalExpression).value) {
                for (ASTNode node : ifClause.body) {
                    if (node instanceof IfClause) {
                        values.addAll(evaluateIfClause((IfClause) node, ifClause));
                    } else {
                        values.add(node);
                    }
                }
            }
        }
        removeIfClause(ifClause, parent);
        if (!(parent instanceof IfClause)) {
           for (ASTNode node : values) {
               parent.addChild(node);
           }
        }
        return values;
    }

    /**
     * This method removes the given ifClause from the parent
     *
     * @param ifClause the given ifClause
     * @param parent the given parent
    * */
    private void removeIfClause(IfClause ifClause, ASTNode parent) {
        if (parent instanceof Stylerule) {
            Stylerule stylerule = (Stylerule) parent;
            stylerule.body.remove(ifClause);
        } else if (parent instanceof IfClause) {
            parent.removeChild(ifClause);
        }
    }

    /**
     * This method checks if the node is a BoolLiteral or VariableReference
     * and returns the value of the node, which can be True or False
     *
     * @param node the given node
     * @return boolean value of the given node
     */
    private BoolLiteral getBoolLiteral(ASTNode node) {
        if (node instanceof BoolLiteral) {
            return (BoolLiteral) node;
        }
        if (node instanceof VariableReference) {
            VariableReference variableReference = (VariableReference) node;
            if (variables.getFirst().containsKey(variableReference.name)) {
                return (BoolLiteral) variables.getFirst().get(variableReference.name);
            }
        }
        return null;
    }

    private void findAllVariables (ASTNode toBeFound) {
        if (toBeFound instanceof VariableAssignment) {
            String name = ((VariableAssignment) toBeFound).name.name;
            Expression expression = ((VariableAssignment) toBeFound).expression;
            variables.getFirst().put(name, (Literal) expression);
        }
        toBeFound.getChildren().forEach(this::findAllVariables);
    }


}
