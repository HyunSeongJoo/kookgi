package com.koreait.listenerTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyListenerTest2 extends JFrame {

	boolean shiftFlag = false;			// 왼쪽 shift 키가 눌렸나 기억하는 변수
	
	public KeyListenerTest2() {
		setTitle("KeyListener");
		setBounds(800, 100, 400, 500);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) {
				
//				shift키에서 손가락이 떨어지면 shiftFlag 변수의 값을 false로 변경한다.
				if (e.getKeyCode() == 16) {
					shiftFlag = false;
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				System.out.println(e.getKeyCode());
//				왼쪽 shift 키가 눌리면 shiftFlag 변수의 값을 true로 변경한다.
				if (e.getKeyCode() == 16 && e.getKeyLocation() == 2) {
					System.out.println("왼쪽 shift 키 눌림");
					shiftFlag = true;
				}
				
//				왼쪽 shift 키와 spacebar가 동시 눌렸나 검사한다.
				if (e.getKeyCode() == 32 && e.getModifiersEx() == 64 && shiftFlag) {
					System.out.println("왼쪽 shift + space");
				}
				
//				오른쪽 shift 키와 spacebar가 동시 눌렸나 검사한다.
				if (e.getKeyCode() == 32 && e.getModifiersEx() == 64 && !shiftFlag) {
					System.out.println("오른쪽 shift + space");
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		
		KeyListenerTest2 window = new KeyListenerTest2();

	}

}

















