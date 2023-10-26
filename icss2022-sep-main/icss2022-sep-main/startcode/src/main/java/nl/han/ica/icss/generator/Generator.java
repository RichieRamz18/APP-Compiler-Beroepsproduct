package nl.han.ica.icss.generator;


import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
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

		generateResult(ast.root);

		return stringBuilder.toString() + "}";
	}

	private void generateResult(ASTNode node) {
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
		node.getChildren().forEach(this::generateResult);
	}

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

	private void generateBodyResult(ASTNode node) {
		if (node instanceof Declaration) {
			generateDeclarationResult((Declaration) node);
		}
	}

	private void generateDeclarationResult(ArrayList<ASTNode> nodes) {
		for (ASTNode node : nodes) {
			if (node instanceof PropertyName) {
				
			}
		}
	}
	
}
