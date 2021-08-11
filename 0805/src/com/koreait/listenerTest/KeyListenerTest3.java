package com.koreait.listenerTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class KeyListenerTest3 extends JPanel implements Runnable {

	int xpos = 50, ypos = 50;
	static int xbar = 100;
	
	public static void main(String[] args) {
		
		Frame window = new Frame("KeyListener");
		window.setBounds(800, 100, 500, 600);
		window.setLayout(new BorderLayout());
		
		KeyListenerTest3 graphic = new KeyListenerTest3();
		window.add(graphic, BorderLayout.CENTER);
		
		Thread thread = new Thread(graphic);
		thread.start();
		
		graphic.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				
				xbar = e.getX() - 75;
				xbar = xbar < 0 ? 0 : xbar;
				xbar = xbar > 335 ? 335 : xbar;
				
			}
			
		});
		
		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
//						3항 연산자 보다 대입 연산자의 우선순위가 낮기 때문에 ()로 묶어서 먼저 실행되게 한다.
						xbar = (xbar -= 10) < 0 ? 0 : xbar;
						break;
					case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
						xbar = (xbar += 10) > 335 ? 335 : xbar;
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
		g.setColor(Color.RED);
		g.fillOval(xpos, ypos, 50, 50);
		
//		수평 막대를 만든다.
		g.setColor(Color.GREEN);
		g.fillRect(xbar, 500, 150, 25);
		
	}

	@Override
	public void run() {
		
		int xsw = 1, ysw = 1;
		while (true) {
			xpos += xsw;
			if (xpos > 435 || xpos < 0) {
				xsw *= -1;
			}
			ypos += ysw;
			if ((ypos > 450 && xpos >= xbar && xpos <= xbar + 150) || ypos < 0) {
				ysw *= -1;
			}
			if (ypos >= 500) {
				break;
			}
			try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
			repaint();
		}
		JOptionPane.showMessageDialog(null, "Game Over!!!");
		System.exit(0);
		
	}

}























