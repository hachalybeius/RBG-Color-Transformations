package rgb_trans.graphics;

import java.awt.Color;

import javax.swing.JFrame;

public class Display {

	/*
	 * TODO
	 * should contain all of the
	 * components of the program.
	 */
	private static final int UPS = 60;
	private int WIDTH = 1500;
	private int HEIGHT = 900;
	private static final long NANO = 1000000000;
	
	private static ColorGraphFrame canvas;
	
	private static Arrow arrow;
	
	public Display(int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	public void drawMain(){
		canvas = new ColorGraphFrame(WIDTH, HEIGHT);
		
		JFrame frame = new JFrame("Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
		
		run();
	}
	
private static int iteration = 0;
	
	public static void update(){
		float red = (float) (Math.cos(Math.toDegrees(iteration / 1)) / 2 + 0.5);
		float green = (float) (Math.cos(Math.toDegrees(iteration / 8)) / 2 + 0.5);
		float blue =(float) (Math.cos(Math.toDegrees(iteration / 16)) / 2 + 0.5);
		Color color = new Color(red, green, blue);
		canvas.setColor(color);
		iteration = (iteration + 1) % 16777216;
	}
	
	public static void render(){
	}
	
	public static void run(){
		boolean running = true;
		long lastUpTime = System.nanoTime();
		long lastSecTime = System.nanoTime();
		int ups = 0;
		while(running){
			if(System.nanoTime() - lastUpTime > NANO/UPS){
				update();
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
