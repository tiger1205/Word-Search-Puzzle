


/**
 * This class is responsible for processing the URL parameters of the applet
 * and sets the puzzle ID
 * 
 * If there is no puzzle id from the URL, then the ID is set to NULL.
 * in such case, a random puzzle will be presented to the user. 
 * 
 *  FUTURE:  Separation of the logic and responsibility to this class helps in incorporating different ways
 *  of selecting the puzzle (based on the date, user id, session id, etc.)
 */

public class PuzzlePicker {
	
	private String puzzleId = "null";
			
	public PuzzlePicker(String url)
	{
		if (url.indexOf("=") > -1) {
			puzzleId = url.substring(url.indexOf("=") + 1);
		}
		
	}
	
	public void setPuzzleId(String an_id)
	{
		puzzleId = an_id;
	}
	
	public String getPuzzleId()
	{
		return puzzleId;
	}
	
}
