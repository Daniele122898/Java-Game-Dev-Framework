package com.argus.game.main;

import javax.swing.JPanel;




import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

import com.argus.game.state.LoadState;
import com.argus.game.state.State;
import com.argus.framework.util.InputHandler;

@SuppressWarnings("serial")//tell eclipse to fuck off with serial warnings

public class Game extends JPanel implements Runnable{ //becomes type of JPanel, your own mod tough
	
	private int gameWidth;
	private int gameHeight;
	private Image gameImage;//TODO
	
	private Thread gameThread;
	private volatile boolean running;//volatile means not changable depending on Thread
	private volatile State currentState;
	
	private InputHandler inputHandler;
	
	public Game(int gameWidth, int gameHeight){ //Constructor with Width and height attributes
		this.gameWidth = gameWidth; //initialize width and height
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));//game is resized to the dimensions set
		setBackground(Color.BLACK);//BG color
		setFocusable(true);//our game can be focused
		requestFocus();//to allow user input
		
	}
	
	public void setCurrentState(State newState){
		System.gc(); //garbage collector, remove unwanted memory space
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(currentState); //give inputhandler the current state!
	
	}
	
	@Override
	public void addNotify(){//runs automatically when Frame starts
		super.addNotify(); //add super class
		initInput(); //Initiate Inputhandler
		setCurrentState(new LoadState());
		initGame();//starts Gameloop 
	}
	
	private void initGame(){
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}

	@Override
	public void run() {
		//runnable thread
		System.out.println("Running");
		
		while (running){
			currentState.update();
			prepareGameImage();
			currentState.render(gameImage.getGraphics());
			repaint();
			
			try{
				Thread.sleep(14);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
		}//End game when running becomes false.
		System.exit(0);
		
	}
	
	private void prepareGameImage(){
		if (gameImage == null){
			gameImage = createImage(gameWidth, gameHeight);
		}
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	
	public void exit(){
		running = false;
	}
	
	@Override
	protected void paintComponent(Graphics g){ //is run with "repaint();"
		super.paintComponent(g);
		if (gameImage == null){
			return;
		}
		g.drawImage(gameImage, 0, 0, null);
	}
	
	private void initInput(){
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);//Listens to key input and hands it to inputHandler
		addMouseListener(inputHandler);//listens to mouse input and hands it to inputHandler
	}

}
