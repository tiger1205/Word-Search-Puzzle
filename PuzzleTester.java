

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * A dummy class to test the logic of different classes
 * It does NOT involve GUI
 * It does NOT involve playing of the game through GUI
 * However, it tests the rest of the logic
 *
 public class PuzzleTester {

	public static void main(String[] args)throws UnsupportedEncodingException
	{

		// TESTS for puzzle 001
		// get 001 puzzle from the puzzlelist
		PuzzlePicker pp1 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=001");

		// populates the puzzle collection
		PuzzleCollection pc1 = new PuzzleCollection(PuzzleConfig.defaultFile);
		
		System.out.println("");

		// get the puzzle based on the id
		Puzzle p1 = pc1.getPuzzleByID(pp1.getPuzzleId());
//		System.out.println(p1);
		PrintStream out = new PrintStream(System.out, true, "UTF-8");		
		for(int i = 0; i < p1.getWordList().size();i++)
			out.println(p1.getWordList().get(i));
		for(int i = 0; i < p1.getGrid().length; i++) {
			for(int j = 0; j < p1.getGrid().length; j++){
				out.print(p1.getGrid()[i][j]+" ");
			}
			System.out.println();
		}
		

		// create a puzzle tracking object
		PuzzleTracker pt1 = new PuzzleTracker(p1);

		// BEAN BAG, IMPRESA, LENS, MANUAL, MATT, NIKON, PRISM, ROLL, SENSIA, TONE

		// find these words
		pt1.isWordInTheList("BEANBAG");
		pt1.isWordInTheList("IMPRESA");
		pt1.isWordInTheList("LENS");
		pt1.isWordInTheList("MANUAL");
		pt1.isWordInTheList("MATT");
		pt1.isWordInTheList("NIKON");
		pt1.isWordInTheList("PRISM");
		pt1.isWordInTheList("ROLL");
		pt1.isWordInTheList("SENSIA");

		// TONE is not found yet. So, run this test
		System.out.println("is TONE found? = " + pt1.isWordAlreadyFound("TONE"));
		// And the game is NOT complete
		System.out.println("Puzzle 001  Status" + pt1.getGameStatusMsg());
//		System.out.println(pt1);


		// now find the word TONE and rerun the test
		pt1.isWordInTheList("TONE");
		System.out.println("is TONE found? = " + pt1.isWordAlreadyFound("TONE"));
		// Now the game is complete
		System.out.println("Puzzle 001 Status" + pt1.getGameStatusMsg());
//		System.out.println(pt1);


		// TESTS for puzzle 003  (simulating multi-byte strings)
		// BEAN BALL, IMPRESA, LENS, MANUAL, NATE, NIKON, PRISM, ROLL, SENSIA, TONE
				PuzzlePicker pp3 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=003");

				// populates the puzzle collection
				PuzzleCollection pc3 = new PuzzleCollection(PuzzleConfig.defaultFile);
				
				System.out.println("");

				// get the puzzle based on the id
				Puzzle p3 = pc3.getPuzzleByID(pp3.getPuzzleId());
//				System.out.println(p3);
				PrintStream out1 = new PrintStream(System.out, true, "UTF-8");		
				for(int i = 0; i < p3.getWordList().size();i++)
					out1.println(p3.getWordList().get(i));
				for(int i = 0; i < p3.getGrid().length; i++) {
					for(int j = 0; j < p3.getGrid().length; j++){
						out1.print(p3.getGrid()[i][j]+" ");
					}
					System.out.println();
				}

				// create a puzzle tracking object
				PuzzleTracker pt3 = new PuzzleTracker(p3);

				// BEAN BALL, IMPRESA, LENS, MANUAL, NATE, NIKON, PRISM, ROLL, SENSIA, TONE
				// find these words
				pt3.isWordInTheList("BEANBALL");
				pt3.isWordInTheList("IMPRESA");
				pt3.isWordInTheList("LENS");
				pt3.isWordInTheList("MANUAL");
				pt3.isWordInTheList("NATE");
				pt3.isWordInTheList("NIKON");
				pt3.isWordInTheList("PRISM");
				pt3.isWordInTheList("ROLL");
				pt3.isWordInTheList("SENSIA");

				// TONE is not found yet. So, run this test
				System.out.println("is TONE found? = " + pt3.isWordAlreadyFound("TONE"));
				// And the game is NOT complete
				System.out.println("Puzzle 003 Status" + pt3.getGameStatusMsg());
//				System.out.println(pt3);


				// now find the word TONE and rerun the test
				pt3.isWordInTheList("TONE");
				System.out.println("is TONE found? = " + pt3.isWordAlreadyFound("TONE"));
				// Now the game is complete
				System.out.println("Puzzle 003 Status" + pt3.getGameStatusMsg());
//				System.out.println(pt3);

	}

}
