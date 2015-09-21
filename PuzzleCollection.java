

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class PuzzleCollection {

	// for maintaining a puzzle collection
	private ArrayList<Puzzle> allPuzzles = new ArrayList<Puzzle>();
	Random rand = new Random();
	private int collectionSize;

	/*
	 * No-arg constructor uses the default file name
	 */
	public PuzzleCollection()
	{
		this(PuzzleConfig.defaultFile);
	}
	
	/*
	 * Constructor takes the input file name and
	 * 	 * Reads each set of the puzzles
	 * 	 * Creates a Puzzle using Puzzle.java
	 *   * and adds the Puzzle to the collection allPuzzles
	 *   * NOTE: Please make sure that you remove * from puzzle.txt before using it
	 *   * NOTE: If you are keeping it, then you have to handle it here (additional complexity)
	 *   * That is there in the puzzle text file for representation only
	 *   The file is a UTF-8 file so that multi-byte characters can be handled
	 */
	public PuzzleCollection(String a_file_name)
	{
		
		try {
			
			/* To read the given file with the UTF-8 format */ 
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(a_file_name), "UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + a_file_name)));			
			String line;
			
		/* Read the first line of every puzzle*/
			while ((line = br.readLine()) != null) {
				/* Split the read line to get the ID of the puzzle*/
				String[] temp = line.split(":");
				String ID = temp[1].trim();
				if(ID.length() != 3)
				{
					JOptionPane.showMessageDialog(null, "INPUT FILE " + 
							"STRUCTURE AND CONTENTS ARE NOT IN THE " +
							"REQUIRED STATE:  \n" + 
										"INVALID ID\n" +
							"PLEASE CHECK YOUR INPUT FILE AND RELOAD");
					System.exit(0);
				}
				
				/* Read the second line to get the title of the puzzle*/
				String[] temp2 = br.readLine().split("[\\:(]+");
				
				String title = temp2[1].trim();
				//System.out.println(title);
				if(title.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "INPUT FILE " + 
							"STRUCTURE AND CONTENTS ARE NOT IN THE " +
							"REQUIRED STATE:  \n" + 
										"NO TITLE " +
								"IN PUZZLE ID: " + ID.toString() + "\n" +
							"PLEASE CHECK YOUR INPUT FILE AND RELOAD");
					System.exit(0);
				}
				/* Read the next line to get the words of the puzzle*/
				String[] temp3 = br.readLine().split("[\\,:]+");
				
		
				/* Add the words to an arraylist*/
				ArrayList<String> wordList = new ArrayList<String>();
				for(int i = 1 ; i < temp3.length; i++){
					wordList.add(temp3[i].trim());
					/*PrintStream stdout = new PrintStream(System.out, true, "UTF-8");
					stdout.print(temp3[i]);*/
				}

				
				/*Read the firstline of the grid*/
				line = br.readLine();
				String[] temp4 = line.split("[\\s,.]+");
				if(temp3.length == temp4.length)
				{
					JOptionPane.showMessageDialog(null, "INPUT FILE " + 
							"STRUCTURE AND CONTENTS ARE NOT IN THE " +
							"REQUIRED STATE: \n" + 
										" MISSING WORD " +
								"IN PUZZLE ID: " + ID.toString() + "\n" +
							"PLEASE CHECK YOUR INPUT FILE AND RELOAD");
					System.exit(0);
				}
				
				/*Inialize the grid with the number of characters in firstline*/
				String[][] grid = new String[temp4.length][];

				/* Add the first row of the grid*/
				grid[0] = temp4;
				
				for(int i = 1; i < temp4.length;i++) {
					temp4 = br.readLine().split("[\\s,.]+");
					if((grid.length < 10)||(temp4.length < 10))
					{
						JOptionPane.showMessageDialog(null, "INPUT FILE " + 
								"STRUCTURE AND CONTENTS ARE NOT IN THE " +
								"REQUIRED STATE: \n" + 
										"MISSING LETTER OR DELIMETER " +
								"IN THE GRID IN PUZZLE ID: " + 
								ID.toString() + "\n" +
								"PLEASE CHECK YOUR INPUT FILE AND RELOAD");
						System.exit(0);
					}
					else
					{
						grid[i] = temp4;
					}
				}

				
				/*Create the puzzle with collected information*/
				Puzzle p = new Puzzle(ID, title, wordList, grid);
				/*Add the puzzle to puzzle collection*/
				add(p);
				
				/* Read and leave the next line between two puzzles*/
				br.readLine();
			}
			br.close();
			collectionSize = allPuzzles.size();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Method to get the size of the puzzle collection.
	 * @return collectionSize
	 */
	public int getCollectionSize()
	{
		return collectionSize;
	}
		
	/**
	 * Method for adding a puzzle to a puzzle collection
	 * @param a_puzzle
	 */
	
	public void add(Puzzle a_puzzle)
	{
		allPuzzles.add(a_puzzle);
	}
	
	
	/*
	 * Method that returns a puzzle based on an Id
	 * from the puzzle collection
	 * Ifthere is no puzzle matching that id,
	 * then a random puzzle will be returned
	 */
	public Puzzle getPuzzleByID(String an_id)
	{
		/*Search for the puzzle with given id sequentially*/
		for(int i = 0;i<allPuzzles.size();i++){
			/*If the puzzle with the given id is found return it*/
			if(allPuzzles.get(i).getID().equalsIgnoreCase(an_id))
				return allPuzzles.get(i);
		}
			
		/*if the puzzle with given id is not found return a random puzzle*/
		return getRandomPuzzle();
		
	}
	
	
	/*
	 * Method that returns a puzzle based on an Id
	 * from the puzzle collection
	 * 
	 * If there are multiple puzzles matching the title, 
	 * then only the first match will be returned.
	 * 
	 * If there is no match found, then a random puzzle
	 * will be returned
	 */
	public Puzzle getPuzzleByTitle(String a_title)
	{
		/*Search for the puzzle with given title sequentially*/
		for(int i = 0;i<allPuzzles.size();i++){
			/*If the puzzle with the given title is found return it*/
			if(allPuzzles.get(i).getTitle().equalsIgnoreCase(a_title))
				return allPuzzles.get(i);
		}
		/*if the puzzle with given title is not found return a random puzzle*/
		return getRandomPuzzle();
		
	}
	
	
	
	/*
	 * Method that returns a a random puzzle from allPuzzles
	 * 
	 */
	
	public Puzzle getRandomPuzzle()
	{
		/* To get the random number within the size of the arraylist*/
		
		int random = 0 + (int)(Math.random() * ((allPuzzles.size()-1 - 0) + 1));
        
		/* return a random puzzle*/
		return allPuzzles.get(random);
	}
	
	
	/*public static void main(String[] args) throws UnsupportedEncodingException{
		PuzzleCollection p = new PuzzleCollection("C:\\puzzle.txt");//
		Puzzle temp = p.getPuzzleByTitle("Martin Luter King");
		System.out.println(temp.getID());
		
		PrintStream out = new PrintStream(System.out, true, "UTF-8");		
		for(int i = 0; i < temp.getWordList().size();i++)
			out.println(temp.getWordList().get(i));
		for(int i = 0; i < temp.getGrid().length; i++) {
			for(int j = 0; j < temp.getGrid().length; j++){
				out.print(temp.getGrid()[i][j]+" ");
			}
			System.out.println();
		}
		
	
	}
	
	
	
	/*public static void main(String[] args) throws UnsupportedEncodingException{
	PuzzleCollection p = new PuzzleCollection("C:\\puzzle.txt");
	Puzzle temp = p.getPuzzleByTitle("Photography2");
	System.out.println(temp.getID());
	
	PrintStream out = new PrintStream(System.out, true, "UTF-8");		
	for(int i = 0; i < temp.getWordList().size();i++)
		out.println(temp.getWordList().get(i));
	for(int i = 0; i < temp.getGrid().length; i++) {
		for(int j = 0; j < temp.getGrid().length; j++){
			out.print(temp.getGrid()[i][j]+" ");
		}
		System.out.println();
	}
	

}*/

}