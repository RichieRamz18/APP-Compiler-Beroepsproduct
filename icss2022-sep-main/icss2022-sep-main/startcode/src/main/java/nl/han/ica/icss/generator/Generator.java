package nl.han.ica.icss.generator;


import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

import java.util.ArrayList;
import java.util.HashMap;

public class Generator {
	private StringBuilder stringBuilder;
	private IHANLinkedList<HashMap<String, Expression>> variables;

	public String generate(AST ast) {
        variables = new HANLinkedList<>();
		variables.addFirst(new HashMap<>());
		stringBuilder = new StringBuilder();

		findAllVariables(ast.root);
		generateCSSResult(ast.root);

		return stringBuilder.toString() + "}";
	}

	/**
	 * Generates the final CSS result of the AST
	 * using the generateSelectorResult and generateBodyResult methods.
	 *
	 * @param node The ASTnode to start generating the CSS result.
	* */
	private void generateCSSResult(ASTNode node) {
		if (node instanceof Selector) {
			if (stringBuilder.toString().endsWith(";\n")) {
				stringBuilder.append("}\n\n");
			}
			generateSelectorResult((Selector) node);
		}
		if (node instanceof Declaration) {
			if (stringBuilder.toString().endsWith(" ")) {
				stringBuilder.append("{\n");
			}
			generateBodyResult(node);
		}
		node.getChildren().forEach(this::generateCSSResult);
	}

	/**
	 * This method checks the type of the selector and appends the correct value to the stringBuilder.
	 *
	 * @param selector The selector to check the type of.
	 * */
	private void generateSelectorResult(Selector selector) {
		if (selector instanceof ClassSelector) {
			stringBuilder.append(((ClassSelector) selector).cls);
		} else if (selector instanceof IdSelector) {
			stringBuilder.append(((IdSelector) selector).id);
		} else if (selector instanceof TagSelector) {
			stringBuilder.append(((TagSelector) selector).tag);
		}
		stringBuilder.append(" ");
	}

	/**
	 * This method calls generateDeclarationResult if the node is a Declaration.
	 *
	 * @param node The node to check the type of.
	 * */
	private void generateBodyResult(ASTNode node) {
		if (node instanceof Declaration) {
			generateDeclarationResult(node.getChildren());
		}
	}

	/**
	 * This method generates the CSS result of a declaration for the final result.
	 *
	 * @param nodes The nodes to generate the CSS result of.
	 * */
	private void generateDeclarationResult(ArrayList<ASTNode> nodes) {
		for (ASTNode node : nodes) {
			if (node instanceof PropertyName) {
				stringBuilder.append("  ").append(((PropertyName) node).name).append(": ");
			}
			if (node instanceof Expression) {
				generateLiteralResult(node);
			}
			if (node instanceof VariableReference) {
				generateVariableValueByName(node);
			}
		}
		stringBuilder.append(";\n");
	}

	/**
	 * This method generates the CSS result of a literal for the final result.
	 * BoolLiteral and ScalarLiteral are not used in the final result, because they are not supported by CSS.
	 *
	 * @param node The node to get the value of.
	 * */
	private void generateLiteralResult(ASTNode node) {
		if (node instanceof ColorLiteral) {
			stringBuilder.append(((ColorLiteral) node).value);
		} else if (node instanceof PercentageLiteral) {
			stringBuilder.append(((PercentageLiteral) node).value).append("%");
		} else if (node instanceof PixelLiteral) {
			stringBuilder.append(((PixelLiteral) node).value).append("px");
		}
	}

	/**
	 * This method retrieves the value of a variable by its name,
	 * and calls generateLiteralResult to generate the CSS result.
	 *
	 * @param node The node to get the value of.
	 * */
	private void generateVariableValueByName(ASTNode node) {
		if (node instanceof VariableReference) {
			if (variables.getFirst().containsKey(((VariableReference) node).name)) {
				generateLiteralResult(variables.getFirst().get(((VariableReference) node).name));
			}
		}
	}

	/**
	 * This method adds the variable name and expression to the variables list hashmap.
	 *
	 * @param toBeFound The node to find.
	 * */
	private void findAllVariables(ASTNode toBeFound) {
		if (toBeFound instanceof VariableAssignment) {
			String name = ((VariableAssignment) toBeFound).name.name;
			Expression expression = ((VariableAssignment) toBeFound).expression;
			variables.getFirst().put(name, expression);
		}
		toBeFound.getChildren().forEach(this::findAllVariables);
	}
	
}
