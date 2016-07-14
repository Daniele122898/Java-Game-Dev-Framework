package com.argus.game.main;

import java.applet.Applet;

import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Resources {
	
	public static BufferedImage welcome, iconimage; //load resources --> Define where the resource is below
	
	public static void load(){
		//TODO
		welcome = loadImage("welcome.png"); //load welcome.png
		iconimage = loadImage("iconimage.png");//load iconimage.png
	}
	
	private static AudioClip loadSound(String filename){
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL); //return loaded audio
	}
	
	private static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try { //Try catch to catch errors with filename! -> prevent crash + report
		img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (Exception e){
			System.out.println("Error while reading: " + filename);
			e.printStackTrace();
		}
		return img; //return loaded image
	}

}