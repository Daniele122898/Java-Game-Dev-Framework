package com.argus.game.main;

import javax.swing.JFrame;

public class GameMain {
	
	/*static means: variables are NOT instance variables but class v. 
	 * do NOT belong to any particular instance -> belong to class 
	 * final means not changeable and uppercase constant*/
	private static final String GAME_TITLE ="LoneBall"; //Title
	public static final int GAME_WIDTH = 800; //Width
	public static final int GAME_HEIGHT = 450; //height
	public static Game sGame;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes app with exit (X)
		frame.setResizable(false);//prevents manual resizing of window
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);//create game instance
		frame.add(sGame);//like the CENTER command / inserts the Game instance in the center borderlayout
		frame.pack();//resize to accommodate the preferred size -> game.java Panel
		frame.setVisible(true);
		frame.setIconImage(Resources.iconimage); //set icon
		
	}
	
}
