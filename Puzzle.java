

import java.util.ArrayList;

/**
 * A class representing a single word search puzzle
 *
 */
public class Puzzle {

	private String id;
	private String title;
	private ArrayList<String> wordList;
	private String[][] puzzleGrid;
	private int gridWidth; //derived
	private int gridHeight; // derived

	/**
	 * Default Constructor For Puzzle Class
	 */
	 public Puzzle()
	 {
		 
	 };

	 /**
	  * Overloaded Constructor For Puzzle Class
	  */			 
	public Puzzle(String an_id, String a_title, ArrayList<String> a_word_list, String[][] puzzle_grid)
	{
		id = an_id;
		title = a_title;
		wordList = a_word_list;
		puzzleGrid = puzzle_grid;
		gridWidth = puzzle_grid[0].length;  
		gridHeight = puzzle_grid.length; 
	}
	
	/**
	 * Set method for the variable id
	 */
	public void setId(String a_id)
	{
		id = a_id;
	}

	/**
	 * Set method for the variable title
	 */
	public void setTitle(String a_title)
	{
		title = a_title;
	}

	/**
	 * Set method for the variable wordList
	 */
	public void setWordList(ArrayList<String> a_word_list)
	{
		wordList = a_word_list;
	}

	/**
	 * Set method for the variable puzzleGrid
	 */
	public void setPuzzleGrid(String[][] a_puzzle_grid)
	{
		puzzleGrid = a_puzzle_grid;
	}

	/**
	 * Get method for the variable id
	 */
	public String getID( )
	{
		return id;
	}

	/**
	 * Get method for the variable title
	 */
	public String getTitle( )
	{
		return title;
	}

	/**
	 * Get method for the variable wordList
	 */
	public ArrayList<String> getWordList( )
	{
		return wordList;
	}

	/**
	 * Get method for the variable puzzleGrid
	 */
	public String[][] getGrid( )
	{
		return puzzleGrid;
	}

	/** 
	 * Returns the String representation of Puzzle object 
	 */	
	public String toString()
	{
		String puzzleString = "ID: " + id + "\n";
		puzzleString += "Title: " + title + "\n";
		puzzleString += "Grid Width: " + gridWidth + "\n";
		puzzleString += "Grid Height: " + gridHeight + "\n";
		for ( int i = 0; i < wordList.size(); i++)
		{
			puzzleString += String.valueOf(wordList.get(i)) + "\n";
		}
		for (int row = 0; row < puzzleGrid.length; row++)
		{
			for (int column = 0; column < puzzleGrid[row].length; column++)
			{
				puzzleString += puzzleGrid[row][column] + " ";
			}
			puzzleString += "\n";
		}
		return puzzleString;
		
	}
}
