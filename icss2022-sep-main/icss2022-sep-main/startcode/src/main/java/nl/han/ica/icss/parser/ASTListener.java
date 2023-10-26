package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.logging.*;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private final IHANStack<ASTNode> currentContainer;
	/* Added a logging to keep track of entering and exiting rules*/
	private static final Logger LOGGER = Logger.getLogger(ASTListener.class.getName());

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }
	@Override public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		LOGGER.info("Entering Stylesheet");
		ASTNode stylesheet = new Stylesheet();
		currentContainer.push(stylesheet);
	}

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		LOGGER.info("Exiting Stylesheet");
		ast.setRoot((Stylesheet) currentContainer.pop());
	}

	@Override public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
		LOGGER.info("Entering StyleRule");
		ASTNode stylerule = new Stylerule();
		currentContainer.push(stylerule);
	}

	@Override public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
		LOGGER.info("Exiting StyleRule");
		ASTNode styleRule = currentContainer.pop();
		currentContainer.peek().addChild(styleRule);
	}
//	@Override public void enterSelector(ICSSParser.SelectorContext ctx) {
//	}
//	@Override public void exitSelector(ICSSParser.SelectorContext ctx) { }

//	@Override public void enterBody(ICSSParser.BodyContext ctx) { }
//
//	@Override public void exitBody(ICSSParser.BodyContext ctx) { }

	@Override public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
		LOGGER.info("Entering TagSelector");
		ASTNode tagSelector = new TagSelector(ctx.getText());
		currentContainer.push(tagSelector);
	}

	@Override public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
		LOGGER.info("Exiting TagSelector");
		ASTNode tagSelector = currentContainer.pop();
		currentContainer.peek().addChild(tagSelector);
	}

	@Override public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
		LOGGER.info("Entering IdSelector");
		ASTNode idSelector = new IdSelector(ctx.getText());
		currentContainer.push(idSelector);
	}

	@Override public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
		LOGGER.info("Exiting IdSelector");
		ASTNode idSelector = currentContainer.pop();
		currentContainer.peek().addChild(idSelector);
	}

	@Override public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
		LOGGER.info("Entering ClassSelector");
		ASTNode classSelector = new ClassSelector(ctx.getText());
		currentContainer.push(classSelector);
	}

	@Override public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
		LOGGER.info("Exiting ClassSelector");
		ASTNode classSelector = currentContainer.pop();
		currentContainer.peek().addChild(classSelector);
	}

	@Override public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		LOGGER.info("Entering Declaration");
		ASTNode declaration = new Declaration();
		currentContainer.push(declaration);
	}

	@Override public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		LOGGER.info("Exiting Declaration");
		ASTNode declaration = currentContainer.pop();
		if(!currentContainer.isEmpty()) {
		currentContainer.peek().addChild(declaration);
		} else {
			ast.setRoot((Stylesheet) declaration);
		}
	}

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		LOGGER.info("Entering PropertyName");
		ASTNode propertyName = new PropertyName(ctx.getText());
		currentContainer.peek().addChild(propertyName);
	}

	@Override public void exitPropertyName(ICSSParser.PropertyNameContext ctx) {
		LOGGER.info("Exiting PropertyName");
	}



	@Override public void enterMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		LOGGER.info("Entering MultiplyOperation");
		ASTNode multiplyOperation = new MultiplyOperation();
		currentContainer.peek().addChild(multiplyOperation);
		currentContainer.push(multiplyOperation);
	}

	@Override public void exitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		LOGGER.info("Exiting MultiplyOperation");
		currentContainer.pop();
	}

	@Override public void enterAddOperation(ICSSParser.AddOperationContext ctx) {
		LOGGER.info("Entering AddOperation");
		ASTNode addOperation = new AddOperation();

		if (currentContainer.peek() instanceof MultiplyOperation) {
			var multiplyList = new HANStack<MultiplyOperation>();

			var multiplyOperation = currentContainer.pop();
			multiplyList.push((MultiplyOperation) multiplyOperation);

			while (currentContainer.peek() instanceof MultiplyOperation) {
				multiplyOperation = currentContainer.pop();
				multiplyList.push((MultiplyOperation) multiplyOperation);
			}

			addOperation.addChild(multiplyOperation);
			currentContainer.peek()
							.removeChild(multiplyOperation)
							.addChild(addOperation);
			currentContainer.push(addOperation);
			while (multiplyList.peek() != null) {
				currentContainer.push(multiplyList.pop());
			}

		} else {
			currentContainer.peek().addChild(addOperation);
			currentContainer.push(addOperation);
		}
	}

	@Override public void exitAddOperation(ICSSParser.AddOperationContext ctx) {
		LOGGER.info("Exiting AddOperation");
		currentContainer.pop();
	}

	@Override public void enterSubtractOperation(ICSSParser.SubtractOperationContext ctx) {
		LOGGER.info("Entering SubtractOperation");
		ASTNode subtractOperation = new SubtractOperation();
		currentContainer.peek().addChild(subtractOperation);
		currentContainer.push(subtractOperation);
	}

	@Override public void exitSubtractOperation(ICSSParser.SubtractOperationContext ctx) {
		LOGGER.info("Exiting SubtractOperation");
		currentContainer.pop();
	}

	@Override public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		LOGGER.info("Entering ColorLiteral");
		ASTNode colorLiteral = new ColorLiteral(ctx.getText());
		currentContainer.peek().addChild(colorLiteral);
	}

	@Override public void exitColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		LOGGER.info("Exiting ColorLiteral");
	}

	@Override public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		LOGGER.info("Entering BoolLiteral");
		ASTNode boolLiteral = new BoolLiteral(ctx.getText());
		currentContainer.peek().addChild(boolLiteral);
	}

	@Override public void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		LOGGER.info("Exiting BoolLiteral");
	}

	@Override public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		LOGGER.info("Entering PercentageLiteral");
		ASTNode percentageLiteral = new PercentageLiteral(ctx.getText());
		currentContainer.peek().addChild(percentageLiteral);
	}

	@Override public void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		LOGGER.info("Exiting PercentageLiteral");
	}

	@Override public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		LOGGER.info("Entering PixelLiteral");
		ASTNode pixelLiteral = new PixelLiteral(ctx.getText());
		currentContainer.peek().addChild(pixelLiteral);
	}

	@Override public void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		LOGGER.info("Exiting PixelLiteral");
	}

	@Override public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		LOGGER.info("Entering ScalarLiteral");
		ASTNode scalarLiteral = new ScalarLiteral(ctx.getText());
		currentContainer.peek().addChild(scalarLiteral);
	}

	@Override public void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		LOGGER.info("Exiting ScalarLiteral");
	}

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		LOGGER.info("Entering VariableAssignment");
		ASTNode variableAssignment = new VariableAssignment();
		currentContainer.push(variableAssignment);
	}

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		LOGGER.info("Exiting VariableAssignment");
		ASTNode variableAssignment = currentContainer.pop();
		currentContainer.peek().addChild(variableAssignment);
	}

	@Override public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		LOGGER.info("Entering VariableReference");
		ASTNode variableReference = new VariableReference(ctx.getText());
		currentContainer.peek().addChild(variableReference);
	}

	@Override public void exitVariableReference(ICSSParser.VariableReferenceContext ctx) {
		LOGGER.info("Exiting VariableReference");
	}

	@Override public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		LOGGER.info("Entering IfClause");
		ASTNode ifClause = new IfClause();
		currentContainer.peek().addChild(ifClause);
		currentContainer.push(ifClause);
	}

	@Override public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		LOGGER.info("Exiting IfClause");
		//ASTNode ifClause = currentContainer.pop();
		currentContainer.pop();

		// Checks the body size of the ifClause, because of bug with ElseClause
//		if (ifClause instanceof IfClause) {
//			IfClause ifClauseNode = (IfClause) ifClause;
//			int bodySize = ifClauseNode.body.size();
//			System.out.println("IfClause body size: " + bodySize);
//		}
//
//		// Add ifClause to the parent node
//		currentContainer.peek().addChild(ifClause);
	}

	@Override public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		LOGGER.info("Entering ElseClause");
		ASTNode elseClause = new ElseClause();
		currentContainer.peek().addChild(elseClause);
		currentContainer.push(elseClause);
	}
	@Override public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		LOGGER.info("Exiting ElseClause");
		currentContainer.pop();
//		ASTNode elseClause = currentContainer.pop();

//		ASTNode ifClause = currentContainer.pop();
//		currentContainer.peek().addChild(ifClause);
//		currentContainer.peek().addChild(elseClause);
	}

}