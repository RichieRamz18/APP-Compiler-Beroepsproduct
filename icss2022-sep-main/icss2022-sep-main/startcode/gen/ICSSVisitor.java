// Generated from C:/Users/richa/OneDrive/Email attachments/Documenten/School/Herkansingen/APP/APP Compiler opdracht/APP-Compiler-Beroepsproduct/icss2022-sep-main/icss2022-sep-main/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(ICSSParser.VariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#elseClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseClause(ICSSParser.ElseClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplyOperation}
	 * labeled alternative in {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addOperation}
	 * labeled alternative in {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOperation(ICSSParser.AddOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divideOperation}
	 * labeled alternative in {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivideOperation(ICSSParser.DivideOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lit}
	 * labeled alternative in {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLit(ICSSParser.LitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtractOperation}
	 * labeled alternative in {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtractOperation(ICSSParser.SubtractOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(ICSSParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorLiteral(ICSSParser.ColorLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentageLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentageLiteral(ICSSParser.PercentageLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixelLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelLiteral(ICSSParser.PixelLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalarLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarLiteral(ICSSParser.ScalarLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#classSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSelector(ICSSParser.ClassSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#tagSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagSelector(ICSSParser.TagSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#idSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdSelector(ICSSParser.IdSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#ruleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleBody(ICSSParser.RuleBodyContext ctx);
}