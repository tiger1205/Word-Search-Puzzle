

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
public class Tester {

	public static void main(String[] args)throws UnsupportedEncodingException
	{

		PrintStream out = new PrintStream(System.out, true, "UTF-8");		
				
				//ID: 004
				//Title:  Martin Luther King  (Telugu)
				//Words:  喟熬喟�?��?�喟熰翱喟ㄠ�?� 喟侧眰喟ム�?啾�喟曕翱喟傕皸啾�  喟眹喟о熬喟掂翱


				// TESTS for puzzle 004
				// get 004 puzzle from the puzzlelist
				PuzzlePicker pp4 = new PuzzlePicker("http://www.dropbox.com/PuzzleLauncher.html?id=004");

				// populates the puzzle collection
				PuzzleCollection pc4 = new PuzzleCollection(PuzzleConfig.defaultFile);
				
				System.out.println("");

				// get the puzzle based on the id
				Puzzle p4 = pc4.getPuzzleByID(pp4.getPuzzleId());
//				System.out.println(p1);
				PrintStream out4 = new PrintStream(System.out, true, "UTF-8");		
				for(int i = 0; i < p4.getWordList().size();i++)
					out.println(p4.getWordList().get(i));
				for(int i = 0; i < p4.getGrid().length; i++) {
					for(int j = 0; j < p4.getGrid().length; j++){
						out.print(p4.getGrid()[i][j]+" ");
					}
					System.out.println();
				}
				

				// create a puzzle tracking object
				PuzzleTracker pt4 = new PuzzleTracker(p4);
				// find these words
				pt4.isWordInTheList("మార�?టిన�? లూథర�? కింగ�?");
				pt4.isWordInTheList("మేధావి");				// Now the game is complete
				System.out.println("Puzzle 004 Status" + pt4.getGameStatusMsg());
				System.out.println(pt4);	
	}

}
