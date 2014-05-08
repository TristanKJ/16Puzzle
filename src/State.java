import java.util.Arrays;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Tristan Knope-Jenkins
 *
 */
public class State {
	
	private int[] 	board;
	private int 	emptySpaceIndex;
	private String 	movesMade;

	
	/**
	 * Creates a new state based off the information from a previous state.
	 * 
	 * @param newBoard
	 * @param index
	 * @param moves
	 */
	public State(int[] newBoard, int index, String moves)
	{
		board = newBoard;
		emptySpaceIndex = index;
		movesMade = moves;
	}
	
	/**
	 * Generates a state from a string of numbers delineated by commas.
	 * 
	 * @param newBoard
	 */
	public State(String newBoard)
	{
		board = new int[16];
		String[] tempBoard = newBoard.split(",");
		for(int i = 0; i < tempBoard.length; i++)
		{
			board[i] = Integer.parseInt(tempBoard[i].trim());
			
			if(board[i] == 0)
				emptySpaceIndex = i;
		}
		movesMade = " ";
	}
	
	/**
	 * Tests whether it is safe to move the space up.
	 */
	public boolean testUp()
	{
		return emptySpaceIndex > 3 && movesMade.charAt(movesMade.length()-1) != 'D';
	}	
	
	/**
	 *  Rearranges the array to move the space up, swapping it with the tile at space - 4.
	 */
	public void moveUp()
	{
		swap(emptySpaceIndex - 4);
		movesMade += 'U';
	}
	
	/**
	 * Tests whether it is safe to move the space Down.
	 */
	public boolean testDown()
	{
		return emptySpaceIndex < 12 && movesMade.charAt(movesMade.length()-1) != 'U';
	}
	
	
	/**
	 *  Rearranges the array to move the space Down, swapping it with the tile at space + 4.
	 */
	public void moveDown()
	{
		swap(emptySpaceIndex + 4);
		movesMade += 'D';
	}
	
	/**
	 * Tests whether it is safe to move the space Right.
	 */
	public boolean testRight()
	{
		return emptySpaceIndex % 4 != 3 && movesMade.charAt(movesMade.length()-1) != 'L';
	}
	
	/**
	 *  Rearranges the array to move the space Left, swapping it with the tile at space+1.
	 */
	public void moveRight()
	{
		swap(emptySpaceIndex + 1);
		movesMade += 'R';
	}
	
	/**
	 * Tests whether it is safe to move the space Left.
	 */
	public boolean testLeft()
	{
		return emptySpaceIndex % 4 != 0 && movesMade.charAt(movesMade.length()-1) != 'R';
	}
	
	/**
	 *  Rearranges the array to move the space Left, swapping it with the tile at Space-1.
	 */
	public void moveLeft()
	{
		swap(emptySpaceIndex-1);
		movesMade += 'L';
	}
	
	/**
	 * Implements swapping the space with the desired tile.
	 * @param spacesNewPosition
	 */
	private void swap(int spacesNewPosition)
	{
		int zero = board[emptySpaceIndex]; //take value of empty space
		int newSpace = board[spacesNewPosition]; //take value of where its moving to
		
		board[spacesNewPosition] = zero;
		board[emptySpaceIndex] = newSpace;
		
		emptySpaceIndex = spacesNewPosition;
	}
	
	/**
	 * Checks every element, returns false after a single mistake.
	 * @return every element is smaller than the one before and larger than the one after.
	 */
	public boolean isGoalState()
	{
		for(int i = 1; i < 14; i++)
		{
			if(!(board[i] > board[i-1] && board[i] < board[i+1]))
				return false;
		}
		if(board[0] != 0)
			return false;
		
		return true;
	}
	
	
	public int[] getBoard()
	{
		return board;
	}
	
	public int getEmptySpace()
	{
		return emptySpaceIndex;
	}
	
	public String getMovesMade()
	{
		return movesMade;
	}
	
	public State cloneState()
	{
		return new State(Arrays.copyOf(board,16), Integer.valueOf(emptySpaceIndex), movesMade+"");
	}
	
	/**
	 * Makes moves based on the buttons clicked.
	 * Does not require the Test___ checks because the user
	 * cannot click outside of the game.
	 * 
	 * @param position of tile the player clicked
	 */
	public void makePlayerMove(int position)
	{
		if(emptySpaceIndex == position)
		{
			return;
		}	
		else if(emptySpaceIndex == position -4)
		{
			moveDown();
		}
		else if(emptySpaceIndex == position + 4)
		{
			moveUp();
		}
		else if(emptySpaceIndex == position + 1)
		{
			moveLeft();
		}
		else if(emptySpaceIndex == position - 1)
		{
			moveRight();
		}
	}

	
	public String toString()
	{
		String result = "";
		for(int i : board)
		{
			result = result + i + ",";
		}
		return result;
	}
	
	public boolean equals(State s)
	{
		if(!s.getMovesMade().equals(movesMade))
			return false;
		if(s.getEmptySpace() != emptySpaceIndex)
			return false;
		
		for(int i = 0; i < 16; i++)
		{
			if(s.board[i] != board[i])
				return false;
		}
		return true;
	}
}