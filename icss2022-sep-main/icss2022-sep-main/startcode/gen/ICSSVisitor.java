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
	 * Visit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operationValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationValue(ICSSParser.OperationValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(ICSSParser.MultiplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ICSSParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#subtract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract(ICSSParser.SubtractContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalarValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarValue(ICSSParser.ScalarValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixelValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelValue(ICSSParser.PixelValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentageValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentageValue(ICSSParser.PercentageValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolValue(ICSSParser.BoolValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(ICSSParser.VariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableValue(ICSSParser.VariableValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(ICSSParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyValue(ICSSParser.PropertyValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
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
}