// Generated from C:/Users/richa/OneDrive/Email attachments/Documenten/School/Herkansingen/APP/APP Compiler opdracht/APP-Compiler-Beroepsproduct/icss2022-sep-main/icss2022-sep-main/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#operationValue}.
	 * @param ctx the parse tree
	 */
	void enterOperationValue(ICSSParser.OperationValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#operationValue}.
	 * @param ctx the parse tree
	 */
	void exitOperationValue(ICSSParser.OperationValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(ICSSParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(ICSSParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(ICSSParser.MultiplyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(ICSSParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(ICSSParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#subtract}.
	 * @param ctx the parse tree
	 */
	void enterSubtract(ICSSParser.SubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#subtract}.
	 * @param ctx the parse tree
	 */
	void exitSubtract(ICSSParser.SubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#scalarValue}.
	 * @param ctx the parse tree
	 */
	void enterScalarValue(ICSSParser.ScalarValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#scalarValue}.
	 * @param ctx the parse tree
	 */
	void exitScalarValue(ICSSParser.ScalarValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 */
	void enterColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 */
	void exitColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#pixelValue}.
	 * @param ctx the parse tree
	 */
	void enterPixelValue(ICSSParser.PixelValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#pixelValue}.
	 * @param ctx the parse tree
	 */
	void exitPixelValue(ICSSParser.PixelValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#percentageValue}.
	 * @param ctx the parse tree
	 */
	void enterPercentageValue(ICSSParser.PercentageValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#percentageValue}.
	 * @param ctx the parse tree
	 */
	void exitPercentageValue(ICSSParser.PercentageValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolValue}.
	 * @param ctx the parse tree
	 */
	void enterBoolValue(ICSSParser.BoolValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolValue}.
	 * @param ctx the parse tree
	 */
	void exitBoolValue(ICSSParser.BoolValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void enterVariableReference(ICSSParser.VariableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void exitVariableReference(ICSSParser.VariableReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableValue}.
	 * @param ctx the parse tree
	 */
	void enterVariableValue(ICSSParser.VariableValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableValue}.
	 * @param ctx the parse tree
	 */
	void exitVariableValue(ICSSParser.VariableValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(ICSSParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(ICSSParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propertyValue}.
	 * @param ctx the parse tree
	 */
	void enterPropertyValue(ICSSParser.PropertyValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propertyValue}.
	 * @param ctx the parse tree
	 */
	void exitPropertyValue(ICSSParser.PropertyValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 */
	void enterIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#ifClause}.
	 * @param ctx the parse tree
	 */
	void exitIfClause(ICSSParser.IfClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#elseClause}.
	 * @param ctx the parse tree
	 */
	void enterElseClause(ICSSParser.ElseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#elseClause}.
	 * @param ctx the parse tree
	 */
	void exitElseClause(ICSSParser.ElseClauseContext ctx);
}