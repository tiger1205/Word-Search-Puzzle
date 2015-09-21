

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 * A java class that acts as a wrapper to the Puzzle
 * to track the play progress and completion
 * @author srj
 *
 */
public class PuzzleTracker {
	private Puzzle puzzle;  // this is the selected puzzle; 
	private ArrayList<Boolean> word_list_status; 
	private int number_of_words;
	private int number_of_words_found;
	private Date start_time;
	private Date end_time;

	/**
	 * constructor for setting the puzzle to play
	 * @param a_puzzle
	 */
	PuzzleTracker(Puzzle a_puzzle)
	{
		puzzle = a_puzzle;
		word_list_status = new ArrayList<Boolean>();  // by default, 
													// all will be FALSE
		number_of_words = puzzle.getWordList().size();
		number_of_words_found = 0;
		for ( int i = 0; i < number_of_words; i++)
		{
			word_list_status.add(false);
		}
	}

	/** 
	 * This method checks whether a_selected_word is in the list of the words.
	 * When a match is found, word_list_status is updated at the corresponding 
	 * location.
	 * @param a_selected_word
	 * @return boolean
	 */
	public boolean isWordInTheList(String a_selected_word)
	{
		boolean match = false;
		int index = 0;
		for ( int i = 0; i < puzzle.getWordList().size(); i++)
		{
			String selected_word = a_selected_word.replaceAll("\\s+", "");
			String word_from_list = puzzle.getWordList().get(i).replaceAll("\\s+", "");
			String reverse_word_from_list = reverseWord(word_from_list);
//			System.out.println(i);
//			System.out.println("Selected Word: " + selected_word);
//			System.out.println("Word From List: " + word_from_list);
//			System.out.println("Reverse Word From List: " + reverse_word_from_list);
			if (selected_word.equals(word_from_list) || selected_word.equals(reverse_word_from_list))
			{
				match = true;
				index = i;
				break;
			}
		}
		if ( match )
		{
			setSelectedWordAsFound(index);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method returns a string containing characters of the 
	 * String argument in reverse order. 
	 * @param a_word
	 * @return reversed_word
	 */
	public String reverseWord(String a_word)
	{
		Stack<Character> stack_of_word = new Stack<Character>();
		String reversed_word = "";
		for ( int i = 0 ; i < a_word.length(); i++ )
		{
			stack_of_word.push(a_word.charAt(i));
		}
		while ( !stack_of_word.empty() )
		{
			reversed_word += String.valueOf(stack_of_word.pop());
		}
		return reversed_word;
	}
	
	/**
	 * This method sets the a_selected_word as FOUND
	 * precondition is  isWordInTheList = true
	 */
	public void setSelectedWordAsFound(int a_index)
	{
		word_list_status.set(a_index, true);
		number_of_words_found++;
	}

	/** 
	 * This method checks whether a word has already been marked as found
	 * @param a_selected_word
	 * @return
	 */
	public boolean isWordAlreadyFound(String a_selected_word)
	{		
		return word_list_status.get(puzzle.getWordList().
				indexOf(a_selected_word));
	}

	/**
	 * Checks whether all words have been found by the user
	 */
	public boolean areAllWordsFound()
	{
		return (number_of_words == number_of_words_found);
	}


	/**
	 * Checks the game Status
	 */
	public boolean isGameComplete()
	{
		return (number_of_words == number_of_words_found);
	}	

	/**
	 * Gets the game completed message
	 * only if the game is completed
	 */
	public String getGameStatusMsg()
	{
		if (isGameComplete() == true)
			return (PuzzleConfig.gameCompletedMsg);
		else
			return (PuzzleConfig.gameIncompleteMsg);
	}
	
	/**
	 * This method sets the start time.
	 */
	public void setStartTime()
	{
		start_time = new Date(); // time right now		
	}

	/**
	 * This method sets the end time (completion time).
	 */
	public void setEndTime()
	{
		end_time = new Date(); // time right now		
	}

	/**
	 * This method calculates and returns the elapsed time based on the 
	 * start time and end time.
	 * @return elapsed_time
	 */
	public String getElapsedTime()
	{ 
		String elapsed_time = "";	
		try { 
			//in milliseconds
			long diff = end_time.getTime() - start_time.getTime(); 
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			
			elapsed_time = String.format("%02d", diffHours) + ":" + 
					String.format("%02d", diffMinutes) + ":" + 
					String.format("%02d", diffSeconds);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elapsed_time;
	}

	public String toString()
	{
		String trackerString = "";
		for ( int i = 0; i < word_list_status.size(); i++)
		{
			trackerString += i + ": " + 
					String.valueOf(word_list_status.get(i)) + "\n";
		}
		trackerString += "Number of Words:" + number_of_words + "\n";
		trackerString += "Number of Words Found:" + number_of_words_found + "\n";

		return trackerString;
	}
}



