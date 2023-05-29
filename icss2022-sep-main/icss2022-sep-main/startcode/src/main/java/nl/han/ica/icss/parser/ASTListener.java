package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {

	}

	@Override public void enterStyleRule(ICSSParser.StyleRuleContext ctx) { }

	@Override public void exitStyleRule(ICSSParser.StyleRuleContext ctx) { }

	@Override public void enterSelector(ICSSParser.SelectorContext ctx) { }

	@Override public void exitSelector(ICSSParser.SelectorContext ctx) { }

	@Override public void enterBody(ICSSParser.BodyContext ctx) { }

	@Override public void exitBody(ICSSParser.BodyContext ctx) { }

	@Override public void enterTagSelector(ICSSParser.TagSelectorContext ctx) { }

	@Override public void exitTagSelector(ICSSParser.TagSelectorContext ctx) { }

	@Override public void enterIdSelector(ICSSParser.IdSelectorContext ctx) { }

	@Override public void exitIdSelector(ICSSParser.IdSelectorContext ctx) { }

	@Override public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) { }

	@Override public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) { }

	@Override public void enterDeclarations(ICSSParser.DeclarationsContext ctx) { }

	@Override public void exitDeclarations(ICSSParser.DeclarationsContext ctx) { }

	@Override public void enterDeclaration(ICSSParser.DeclarationContext ctx) { }

	@Override public void exitDeclaration(ICSSParser.DeclarationContext ctx) { }

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) { }

	@Override public void exitPropertyName(ICSSParser.PropertyNameContext ctx) { }

	@Override public void enterExpression(ICSSParser.ExpressionContext ctx) { }

	@Override public void exitExpression(ICSSParser.ExpressionContext ctx) { }

	@Override public void enterLiteral(ICSSParser.LiteralContext ctx) { }

	@Override public void exitLiteral(ICSSParser.LiteralContext ctx) { }

	@Override public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) { }

	@Override public void exitColorLiteral(ICSSParser.ColorLiteralContext ctx) { }

	@Override public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) { }

	@Override public void exitBoolLiteral(ICSSParser.BoolLiteralContext ctx) { }

	@Override public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) { }

	@Override public void exitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) { }

	@Override public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) { }

	@Override public void exitPixelLiteral(ICSSParser.PixelLiteralContext ctx) { }

	@Override public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) { }

	@Override public void exitScalarLiteral(ICSSParser.ScalarLiteralContext ctx) { }

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) { }

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) { }

	@Override public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) { }

	@Override public void exitVariableReference(ICSSParser.VariableReferenceContext ctx) { }

	@Override public void enterOperation(ICSSParser.OperationContext ctx) { }

	@Override public void exitOperation(ICSSParser.OperationContext ctx) { }

	@Override public void enterIfClause(ICSSParser.IfClauseContext ctx) { }

	@Override public void exitIfClause(ICSSParser.IfClauseContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }

	@Override public void exitEveryRule(ParserRuleContext ctx) { }

}