package nl.han.ica.icss.generator;


import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Selector;

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
		}
	}
	
}
