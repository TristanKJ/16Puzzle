import java.awt.*;
import java.awt.event.*;
import java.awt.Dialog;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Random;

/**
 * 
 * @author Tristan Knope-Jenkins
 *
 */

public class GUI extends JFrame{
	
	final JFrame frame = new JFrame("16 Puzzle Solver");
	
	private int 			height;
	private int 			width;
	private PuzzleButton[] 	values;
	private String 			nums = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
	public  JTextArea       output;
	public  JButton 		solveButton;
	public 	JButton			randomize;
	public  State			currentState;
	public	boolean			randomized = false;
	
	class PuzzleButton extends JButton {
		private int position;
		private int value;

		public PuzzleButton(int val, int pos) {
			position = pos;
			value = val;
			setText( val + "");
			this.setBackground(Color.white);
			this.setFont(new Font("button", 1, 20));
			addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					System.out.println(position + "");
					currentState.makePlayerMove(position);
					updateButtons();
				}
			});}}

	
	
	public GUI(int height, int width)
	{
		this.height = height;
		this.width = width;
		currentState = new State(nums);
		makeFrame();
	}
	
	
	private void makeFrame()
	{
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(new GridLayout(5,5));
		makeButtons(panel);
		solveButton = new JButton("Solve");
		randomize = new JButton("Randomize");
		randomize.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				randomizeBoard();
			}
		});
		frame.add(solveButton);
		output = new JTextArea(50,50);
		frame.add(output);
		output.setLineWrap(true);
		output.append("Replace this text with your puzzle in format: #,#,#  To solve a puzzle from the board this box must     be empty.");
		frame.add(randomize);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public String getValues()
	{
		String answer = "";
		for(PuzzleButton b : values)
		{
			answer += (b.value + ",");
		}
		return answer;
	}
	
	public void setValues(int[] newValues)
	{
		for(int i = 0; i < 16; i++)
		{
			values[i].value = newValues[i];
		}
	}

	
	private void makeButtons(JPanel panel)
	{
		values = new PuzzleButton[16];
		int[] buttonValues = currentState.getBoard();
		for(int i = 0; i < 16; i++)
		{
				PuzzleButton but = new PuzzleButton(buttonValues[i],i);
				panel.add(but);
				values[i] = but;
		}
		updateButtons();
	}
	
	
	private void updateButtons()
	{
		int[] buttonValues = currentState.getBoard();
		for(int i = 0; i<16; i++)
		{
			values[i].value = buttonValues[i];
			values[i].setText(buttonValues[i]+"");
		}
	}
	
	public void randomizeBoard()
	{
		Random dice = new Random();
		String[] availible = nums.split(",");
		String newBoard ="";
		for(int i = 0; i < availible.length; i++)
		{
			if(dice.nextBoolean())
				newBoard = newBoard + availible[i] + ",";
			else
				newBoard = availible[i] + "," + newBoard ;
		}
		currentState = new State(newBoard);
		updateButtons();
		randomized = true;
	}
	
	
	public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
}
	
}
