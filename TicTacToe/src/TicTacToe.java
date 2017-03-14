/****************************************************************************************
 * TicTacToe.java
 * Nate Johnson
 *
 * This is a GUI tutorial from chapter 18 of "Introduction to Programming with Java" from
 * my Programming Fundamentals course. It implements a simplified version of tic-tac-toe.
 * As a user clicks blank buttons, the buttons' labels change to X and O in alternating
 * sequence.
 ***************************************************************************************/

import javax.swing.*;			// for JFrame and JButton

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;		// for ActionListener and ActionEvent

public class TicTacToe extends JFrame{

	private static final int WIDTH = 400;
	private static final int HEIGHT = 440;
	private boolean xTurn = true;	// Is it X's turn?
	private JButton[][] buttons = new JButton[3][3];
	
	//***********************************************************************************
	
	public TicTacToe(){
		setTitle("Tic-Tac-Toe");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
	} // end TicTacToe constructor
	
	//***********************************************************************************

	// Create components and add to window
	
	private void createContents(){
		Listener listener = new Listener();
		
		setLayout(new GridLayout(3, 3));
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(listener);
				add(buttons[i][j]);
			} // end for j
		} // end for i
		
	} // end createContents
	
	//***********************************************************************************
	
	// If user clicks a button, change its label to "X" or "O".
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton btn = (JButton) e.getSource();
			if(btn.getText().isEmpty()){
				btn.setText(xTurn ? "X" : "O");
				if(xTurn){
					btn.setForeground(Color.RED);
				}else
					btn.setForeground(Color.BLUE);
								
				if(isWinner()){				
					JOptionPane.showMessageDialog(null, "Congrats " + (xTurn ? "X" : "O") + "! You win!");
					resetGame();
				} else 
					xTurn = !xTurn;					
				if(isBoardFull() && !isWinner()){
					JOptionPane.showMessageDialog(null, "Looks like the Cat won this one!");
					resetGame();
				}
			}
		} // end actionPerformed
	} // end class Listener
		
	//***********************************************************************************
	
	// Check if all boxes are full.

	private boolean isBoardFull() {
		
		int count = 0;
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(!buttons[i][j].getText().isEmpty()){
					count++;	// count number of full boxes
				}
			}
		}
		
		if(count>=9){
			return true;
		}		
		return false;
	} // end isFull	
	
	//***********************************************************************************
	
	// Check for winning move.
	
	private boolean isWinner(){
		String player; // track whose turn it is
		
		// set whose turn it is
		if(xTurn){
			player = "X"; 
		}else
			player = "O";
		
		// check for 3 in a row for each row
		for (int i=0; i<3; i++){
			if(buttons[i][0].getText().equals(player) && 
					buttons[i][1].getText().equals(player)	&&
					buttons[i][2].getText().equals(player)){
				return true;
			}
		} // end for i
		
		// check for 3 in a row for each column
		for(int j=0; j<3; j++){
			if(buttons[0][j].getText().equals(player) && 
					buttons[1][j].getText().equals(player)	&&
					buttons[2][j].getText().equals(player)){
				return true;
			}
		} // end for j
		
		// check for 3 in a row for each diagonal
		if(buttons[0][0].getText().equals(player) && 
				buttons[1][1].getText().equals(player) &&
				buttons[2][2].getText().equals(player) ||
				buttons[0][2].getText().equals(player) &&
				buttons[1][1].getText().equals(player) &&
				buttons[2][0].getText().equals(player)){
			return true;
		} // end if
		
		return false;	// if no winner
	} // end isWinner
	
	//***********************************************************************************
	
	private void resetGame(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				buttons[i][j].setText("");
			} // end for j
		} // end for i
		xTurn = true;
	}
	
	//***********************************************************************************

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "X always goes first! Good Luck!");
		new TicTacToe();		
	}
	
} // end class TicTacToe
