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

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private final IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }
	@Override public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		ASTNode stylesheet = new Stylesheet();
		currentContainer.push(stylesheet);
	}

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot((Stylesheet) currentContainer.pop());
	}

	@Override public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
		ASTNode stylerule = new Stylerule();
		currentContainer.push(stylerule);
	}

	@Override public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
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
		ASTNode tagSelector = new TagSelector(ctx.getText());
		currentContainer.push(tagSelector);
	}

	@Override public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
		ASTNode tagSelector = currentContainer.pop();
		currentContainer.peek().addChild(tagSelector);
	}

	@Override public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
		ASTNode idSelector = new IdSelector(ctx.getText());
		currentContainer.push(idSelector);
	}

	@Override public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
		ASTNode idSelector = currentContainer.pop();
		currentContainer.peek().addChild(idSelector);
	}

	@Override public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ASTNode classSelector = new ClassSelector(ctx.getText());
		currentContainer.push(classSelector);
	}

	@Override public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ASTNode classSelector = currentContainer.pop();
		currentContainer.peek().addChild(classSelector);
	}

//	@Override public void enterDeclarations(ICSSParser.DeclarationsContext ctx) {
//
//	}
//	@Override public void exitDeclarations(ICSSParser.DeclarationsContext ctx) {
//
//	}

	@Override public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		ASTNode declaration = new Declaration();
		currentContainer.push(declaration);
	}

	@Override public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		ASTNode declaration = currentContainer.pop();
		if(!currentContainer.isEmpty()) {
		currentContainer.peek().addChild(declaration);
		} else {
			ast.setRoot((Stylesheet) declaration);
		}
	}

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		ASTNode propertyName = new PropertyName(ctx.getText());
		currentContainer.peek().addChild(propertyName);
	}

	@Override public void exitPropertyName(ICSSParser.PropertyNameContext ctx) { }

	@Override public void enterExpression(ICSSParser.ExpressionContext ctx) {
		if(ctx.getChildCount() == 2){
			Operation operation;
			switch(ctx.getChild(1).getText()){
				case "*":
					operation = new MultiplyOperation();
					break;
				case "+":
					operation = new AddOperation();
					break;
				default:
					operation = new SubtractOperation();
			}
			currentContainer.push(operation);
		}
	}

	@Override public void exitExpression(ICSSParser.ExpressionContext ctx) {
		if(expressionHasTerminalNode(ctx)){
			ASTNode operation = currentContainer.pop();
			currentContainer.peek().addChild(operation);
		}
	}

//	@Override public void enterLiteral(ICSSParser.LiteralContext ctx) { }
//
//	@Override public void exitLiteral(ICSSParser.LiteralContext ctx) { }

	@Override public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		ASTNode colorLiteral = new ColorLiteral(ctx.getText());
		currentContainer.peek().addChild(colorLiteral);
	}

	@Override public void exitColorLiteral(ICSSParser.ColorLiteralContext ctx) { }

	@Override public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		ASTNode boolLiteral = new BoolLiteral(ctx.getText());
		currentContainer.peek().addChild(boolLiteral);
	}

	@Override public void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx) { }

	@Override public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		ASTNode percentageLiteral = new PercentageLiteral(ctx.getText());
		currentContainer.peek().addChild(percentageLiteral);
	}

	@Override public void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) { }

	@Override public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		ASTNode pixelLiteral = new PixelLiteral(ctx.getText());
		currentContainer.peek().addChild(pixelLiteral);
	}

	@Override public void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx) { }

	@Override public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		ASTNode scalarLiteral = new ScalarLiteral(ctx.getText());
		currentContainer.peek().addChild(scalarLiteral);
	}

	@Override public void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx) { }

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		ASTNode variableAssignment = new VariableAssignment();
		currentContainer.push(variableAssignment);
	}

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		ASTNode variableAssignment = currentContainer.pop();
		currentContainer.peek().addChild(variableAssignment);
	}

	@Override public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		ASTNode variableReference = new VariableReference(ctx.getText());
		currentContainer.peek().addChild(variableReference);
	}

	@Override public void exitVariableReference(ICSSParser.VariableReferenceContext ctx) { }

	@Override public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		ASTNode ifClause = new IfClause();
		currentContainer.push(ifClause);
	}

	@Override public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		ASTNode ifClause = currentContainer.pop();
		currentContainer.peek().addChild(ifClause);
	}

	@Override public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		ASTNode elseClause = new ElseClause();
		currentContainer.push(elseClause);
	}
	@Override public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		ASTNode elseClause = currentContainer.pop();
		currentContainer.peek().addChild(elseClause);
	}

	private boolean expressionHasTerminalNode(ICSSParser.ExpressionContext ctx){
		return ctx.operation().PLUS() != null || ctx.operation().MIN() != null || ctx.operation().MUL() != null;
	}
//	@Override public void enterEveryRule(ParserRuleContext ctx) { }
//
//	@Override public void exitEveryRule(ParserRuleContext ctx) { }

//	@Override
//	public void enterStyleSheet() {
//
//	}

}