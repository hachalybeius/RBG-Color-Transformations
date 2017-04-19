package rgb_trans.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rgb_trans.graphics.DrawCanvas;

public class TestMainSven {
	
	private static final int UPS = 60;
	private static final int WIDTH = 1500;
	private static final int HEIGHT = 1000;
	private static final long NANO = 1000000000;
	
	private static DrawCanvas canvas;

	public static void main(String... args){
		canvas = new DrawCanvas(WIDTH, HEIGHT, Color.BLACK);
		
		JFrame frame = new JFrame("Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		
		run();
	}
	
	private static int iteration = 20;
	
	public static void render(){
		canvas.ready();
		//canvas.clear();
		canvas.drawLine((iteration*7)%WIDTH, (iteration*29)%HEIGHT, (iteration*13)%WIDTH, (iteration*41)%HEIGHT, Color.RED, 2);
		canvas.cleanUp();
		iteration++;
	}
	
	public static void run(){
		boolean running = true;
		long lastUpTime = System.nanoTime();
		long lastSecTime = System.nanoTime();
		int ups = 0;
		while(running){
			if(System.nanoTime() - lastUpTime > NANO/UPS){
				render();
				lastUpTime = System.nanoTime();
				ups++;
			}
			if(System.nanoTime() - lastSecTime > NANO){
				//System.out.println(ups);
				lastSecTime = System.nanoTime();
				ups = 0;
			}
		}
	}
}
