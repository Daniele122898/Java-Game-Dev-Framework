package com.argus.game.state;
import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.argus.game.main.GameMain;

public abstract class State { //template for other states, not actually a game state itselfe

	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract void onClick(MouseEvent e);
	public abstract void onKeyPress(KeyEvent e);
	public abstract void onKeyRelease(KeyEvent e);
	
	public void setCurrentState(State newState){ //change state
		GameMain.sGame.setCurrentState(newState);
	}
	
}
