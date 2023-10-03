package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class Fixtures {

    public static AST uncheckedLevel0() {
		Stylesheet stylesheet = new Stylesheet();
		/*
		p {
			background-color: #ffffff;
			width: 500px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("p"))
				.addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("500px")))
		);
		/*
		a {
			color: #ff0000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("a"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ff0000")))
		);
		/*
		#menu {
			width: 520px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#menu"))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("520px")))
		);
		/*
		.menu {
			color: #000000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new ClassSelector(".menu"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#000000")))
		);

        return new AST(stylesheet);
    }

	public static AST uncheckedLevel0inputBestand() {
		Stylesheet stylesheet = new Stylesheet();
		/*
		h1 {
    		background-color: #f0f0f0;
    		font-size: 24px;
    		padding: 10px;
    	}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("h1"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#f0f0f0")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("24px")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px"))));
		/*
		li {
    		color: #0078d4;
    		font-size: 18px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("li"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#0078d4")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("18px"))));
		/*
		#header {
    		background-color: #333333;
    		height: 80px;
   			color: #ffffff;
    		padding: 10px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#header"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#333333")))
				.addChild((new Declaration("height"))
						.addChild(new PixelLiteral("80px")))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px"))));

		/*
		.button {
    		background-color: #0078d4;
    		color: #ffffff;
    		padding: 10px;
    		font-size: 18px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new ClassSelector(".button"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#0078d4")))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("18px"))));

		return new AST(stylesheet);
	}


	public static AST uncheckedLevel1() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("LinkColor"))
                .addChild(new ColorLiteral("#ff0000"))
        );
		stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("500px"))
        );
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
	        background-color: #ffffff;
	        width: ParWidth;
            }
	    */
        stylesheet.addChild((new Stylerule())
            .addChild(new TagSelector("p"))
            .addChild((new Declaration("background-color"))
                    .addChild(new ColorLiteral("#ffffff")))
            .addChild((new Declaration("width"))
                    .addChild(new VariableReference("ParWidth")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor")))
		);
        /*
            #menu {
	            width: 520px;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new IdSelector("#menu"))
			.addChild((new Declaration("width"))
				.addChild(new PixelLiteral("520px")))
		);
        /*
            .menu {
	            color: #000000;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new ClassSelector(".menu"))
			.addChild((new Declaration("color"))
				.addChild(new ColorLiteral("#000000")))
		);
		return new AST(stylesheet);
	}

	public static AST uncheckedLevel1inputBestand() {
		Stylesheet stylesheet = new Stylesheet();





		/*
		h1 {
    		background-color: #f0f0f0;
    		font-size: 24px;
    		padding: 10px;
    	}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("h1"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#f0f0f0")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("24px")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px"))));
		/*
		li {
    		color: #0078d4;
    		font-size: 18px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("li"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#0078d4")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("18px"))));
		/*
		#header {
    		background-color: #333333;
    		height: 80px;
   			color: #ffffff;
    		padding: 10px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#header"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#333333")))
				.addChild((new Declaration("height"))
						.addChild(new PixelLiteral("80px")))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px"))));

		/*
		.button {
    		background-color: #0078d4;
    		color: #ffffff;
    		padding: 10px;
    		font-size: 18px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new ClassSelector(".button"))
				.addChild((new Declaration("background-color"))
						.addChild(new ColorLiteral("#0078d4")))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("padding"))
						.addChild(new PixelLiteral("10px")))
				.addChild((new Declaration("font-size"))
						.addChild(new PixelLiteral("18px"))));

		return new AST(stylesheet);
	}



	public static AST uncheckedLevel2() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("LinkColor"))
				.addChild(new ColorLiteral("#ff0000"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("ParWidth"))
				.addChild(new PixelLiteral("500px"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
	        background-color: #ffffff;
	        width: ParWidth;
            }
	    */
        stylesheet.addChild((new Stylerule())
            .addChild(new TagSelector("p"))
            .addChild((new Declaration("background-color"))
                    .addChild(new ColorLiteral("#ffffff")))
            .addChild((new Declaration("width"))
                    .addChild(new VariableReference("ParWidth")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor")))
		);
        /*
            #menu {
        	width: ParWidth + 2 * 10px;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new IdSelector("#menu"))
			.addChild((new Declaration("width"))
				.addChild((new AddOperation())
                        .addChild(new VariableReference("ParWidth"))
                        .addChild((new MultiplyOperation())
                                .addChild(new ScalarLiteral("2") )
                                .addChild(new PixelLiteral("10px"))

        ))));
        /*
            .menu {
	            color: #000000;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new ClassSelector(".menu"))
			.addChild((new Declaration("color"))
				.addChild(new ColorLiteral("#000000")))
		);
		return new AST(stylesheet);
	}

	public static AST uncheckedLevel2inputBestand() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			TextColour := #0088cc;
			Background:= #FFFFFF;
			FontSize := 16px;
			UseBackgroundColour := TRUE;
			UseTextColour := TRUE;
		*/
		stylesheet.addChild(new VariableAssignment())
				.addChild(new VariableReference("TextColour"))
				.addChild(new ColorLiteral("#0088cc"));
		stylesheet.addChild(new VariableAssignment())
				.addChild(new VariableReference("Background"))
				.addChild(new ColorLiteral("#FFFFFF"));
		stylesheet.addChild(new VariableAssignment())
				.addChild(new VariableReference("FontSize"))
				.addChild(new PixelLiteral("16px"));
		stylesheet.addChild(new VariableAssignment())
				.addChild(new VariableReference("UseBackgroundColour"))
				.addChild(new BoolLiteral(true));
		stylesheet.addChild(new VariableAssignment())
				.addChild(new VariableReference("UseTextColour"))
				.addChild(new BoolLiteral(true));
		/*
		body {
  			background-color: Background;
  			color: TextColour;
			font-size: FontSize;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("body"))
				.addChild((new Declaration("background-color"))
						.addChild(new VariableReference("Background")))
				.addChild((new Declaration("color"))
						.addChild(new VariableReference("TextColour"))
				.addChild((new Declaration("font-size"))
						.addChild(new VariableReference("FontSize")))));

		/*
		h1 {
  			color: TextColour;
  			font-size: FontSize + 16 - 4px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("h1"))
				.addChild((new Declaration("color"))
						.addChild(new VariableReference("TextColour")))
				.addChild((new Declaration("font-size"))
						.addChild((new AddOperation())
								.addChild(new VariableReference("FontSize"))
								.addChild((new SubtractOperation())
										.addChild(new ScalarLiteral("16"))
										.addChild(new PixelLiteral("4px"))))));


		/*
		#sidebar {
  			background-color: Background;
  			width: 200px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#sidebar"))
				.addChild((new Declaration("background-color"))
						.addChild(new VariableReference("Background")))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("200px"))));

		return new AST(stylesheet);
	}


	public static AST uncheckedLevel3() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("LinkColor"))
				.addChild(new ColorLiteral("#ff0000"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("ParWidth"))
				.addChild(new PixelLiteral("500px"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
				background-color: #ffffff;
				width: ParWidth;
				if[AdjustColor] {
	    			color: #124532;
	    			if[UseLinkColor]{
	        			bg-color: LinkColor;
	    			}
				}
			}
			p {
				background-color: #ffffff;
				width: ParWidth;
				if[AdjustColor] {
	    			color: #124532;
	    		if[UseLinkColor]{
	        		background-color: LinkColor;
	    		} else {
	        		background-color: #000000;
	    		}
	    		height: 20px;
			}
}
	    */
        stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("p"))
					.addChild((new Declaration("background-color"))
							.addChild(new ColorLiteral("#ffffff")))
					.addChild((new Declaration("width"))
							.addChild(new VariableReference("ParWidth")))
					.addChild((new IfClause())
						.addChild(new VariableReference("AdjustColor"))
						.addChild((new Declaration("color")
								.addChild(new ColorLiteral("#124532"))))
							.addChild((new IfClause())
									.addChild(new VariableReference("UseLinkColor"))
									.addChild(new Declaration("background-color").addChild(new VariableReference("LinkColor")))
									.addChild((new ElseClause())
											.addChild(new Declaration("background-color").addChild(new ColorLiteral("#000000")))

									)
					))
					.addChild((new Declaration("height"))
							.addChild(new PixelLiteral("20px")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor"))
            )
		);
        /*
            #menu {
        	width: ParWidth + 20px;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new IdSelector("#menu"))
			.addChild((new Declaration("width"))
				.addChild((new AddOperation())
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("20px"))
                )
            )
        );
        /*


         .menu {
				color: #000000;
    			background-color: LinkColor;

			}

        */
        stylesheet.addChild((new Stylerule())
			.addChild(new ClassSelector(".menu"))

			.addChild((new Declaration("color"))
				.addChild(new ColorLiteral("#000000"))
            )
				.addChild((new Declaration("background-color"))
						.addChild(new VariableReference("LinkColor"))
				)

		);

		return new AST(stylesheet);
	}
}
