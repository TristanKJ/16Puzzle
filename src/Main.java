import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Generates the GUI and handles algorithm.
 * To enter a puzzle use the text area on the button of the GUI.
 * 
 * @ArchiveFile: TKJL1.zip
 * @ExecutibleJar: TKJL1
 * 
 * @author Tristan Knope-Jenkins
 *
 */

public class Main {
	
	DLLQueue<State> q = new DLLQueue<State>();
	GUI 			gui;
	State 			start;
	
	public Main(String startingConfig)
	{
		guiSetup();
		start = new State(startingConfig);
		q.add(start);
	}
	public Main()
	{
		guiSetup();
	}
	
	public void guiSetup()
	{
		gui = new GUI(800,1100);
		gui.solveButton.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(gui.output.getText().equals(""))
					getProblemFromBoard();
				else
					addProblemFromText(gui.output.getText());
			}
		});
	}
	
	/**
	 * Takes the puzzle from the board and passes it to the problem from text method.
	 */
	public void getProblemFromBoard()
	{
		
		if(gui.randomized) //Protects the user from trying to solve a randomized puzzle.
			gui.output.setText("You REALLY dont want to try that.");
		else {
				addProblemFromText(gui.getValues());
				gui.output.setText(solve());
			}
	}
	
	/**
	 * Passes the problem from the board or text area to the solve method and outputs the result.
	 * @param String representation of a problem
	 */
	public void addProblemFromText(String problem)
	{
		q.clear();
		q.add(new State(problem));
		gui.output.setText(solve());
	}
	
	/**
	 * Implements a statespace search for the solution
	 * @return A string with the moves made to solve the puzzle followed by commas
	 */
	public String solve()
	{
		State currentState;
		while(!q.isEmpty())
		{
			currentState = q.remove();

			if(currentState.isGoalState()) {
				return currentState.getMovesMade();
			}
			
			if(currentState.testDown()) {
				State tempDown = currentState.cloneState();
				tempDown.moveDown();
	 			q.add(tempDown);
			}
			if(currentState.testUp()){
				State tempUp = currentState.cloneState();
				tempUp.moveUp();
				q.add(tempUp);
			}
			if(currentState.testRight()){
				State tempRight = currentState.cloneState();
				tempRight.moveRight();
				q.add(tempRight);
			}
			if(currentState.testLeft()){
				State tempLeft = currentState.cloneState();
				tempLeft.moveLeft();
				q.add(tempLeft);
			}
		}
		return "No solution found";
	}

	
	public static void main(String[] args)
	{
		Main main = new Main();
	}
}
