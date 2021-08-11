package com.koreait.listenerTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class keyListenerTest4 extends JPanel implements Runnable {

	static int xpos = 0, ypos = 0, position = 0;
	int angle = 0;
	static final int SPEED = 10;
	
	public static void main(String[] args) {
		
		Frame window = new Frame("KeyListener");
		window.setBounds(800, 100, 500, 600);
		window.setLayout(new BorderLayout());
		keyListenerTest4 graphic = new keyListenerTest4();
		window.add(graphic, BorderLayout.CENTER);
		Thread thread = new Thread(graphic);
		thread.start();
		
		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
						xpos = (xpos -= SPEED) < 0 ? 0 : xpos;
						position = 180;
						break;
					case KeyEvent.VK_W: case KeyEvent.VK_UP:
						ypos = (ypos -= SPEED) < 0 ? 0 : ypos;
						position = 90;
						break;
					case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
						xpos = (xpos += SPEED) > 405 ? 405 : xpos;
						position = 0;
						break;
					case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
						ypos = (ypos += SPEED) > 480 ? 480 : ypos;
						position = 270;
						break;
				}
				
			}
			
		});
		
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		window.setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 600);
		g.setColor(Color.ORANGE);
		g.fillArc(xpos, ypos, 80, 80, position + angle, 360 - angle * 2);
		
	}

	@Override
	public void run() {
		
		int asw = 1;
		while (true) {
			angle += asw;
			if (angle > 35 || angle < 0) {
				asw *= -1;
			}
			try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
			repaint();
		}
		
	}

}























