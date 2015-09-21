

import java.awt.*;
public class PuzzleConfig {
	
	public static String defaultFile = "puzzle.txt";
	
		public static String fontName = "Times New Roman";
	public static float fontSize = 14f; 
	public static String textColorUnselected = "BLACK";
	public static Color textColorSelected = Color.yellow;
	public static Color cellColorUnselected = Color.CYAN;
	public static Color cellbgColorSelected = Color.RED; 
	public static int cellHeight = 200; // in pixels
	public static int cellWidth = 200; // in pixel
		
		public static String gameCompletedMsg = "Congratulations!";
	public static String gameIncompleteMsg = "You are NOT done!";
	
	// NOTE: The number of words can range from 5 to 20
	public static int maxNumberOfWords = 20;
	public static int minNumberofWords = 5;
	
	
	public static int maxNumberOfWordsPerColumn = 5;
	
}
