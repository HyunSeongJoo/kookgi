package com.koreait.networkTest3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server3 extends JFrame implements ActionListener, Runnable {

	JTextArea textArea;		// 전체 대화 내용이 출력될 영역
	JPanel panel;			// 대화 내용을 입력하는 텍스트 필드와 전송 버튼이 올라갈 패널
	JTextField textField;	// 대화 내용을 입력하는 텍스트 필드
	JButton button;			// 텍스트 필드에 입력한 대화 내용을 전송하는 버튼

	ServerSocket serverSocket;
	Socket socket;
	PrintWriter printWriter;
	Scanner scanner;
	String massage = "";
	
	public Server3() {
		setTitle("1:1 채팅 프로그램 - 서버");
		setBounds(100, 50, 500, 700);
		
//		채팅창 만들기
		textArea = new JTextArea();
		textArea.setBackground(Color.ORANGE);
//		textArea.setEnabled(false);		// 비활성화
		textArea.setEditable(false);	// 편집 불가능
		add(textArea);

		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(500, 40));
		textField = new JTextField();
		panel.add(textField);
		button = new JButton("전송");
		panel.add(button, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);
		
//		텍스트 필드와 전송 버튼에 ActionListener를 걸어준다.
		textField.addActionListener(this);
		button.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
//				서버 채팅 창이 닫힐 때 클라이언트에게 나간다고 알려준다. => 통신을 종료한다.
				int result = JOptionPane.showConfirmDialog(textArea, "채팅을 종료하겠습니까?", "채팅 종료", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
//					클라이언트에게 나간다고 알려준다.
					printWriter.write("저 나가요~~~~~ 바이바이~~~~~\n");
					printWriter.write("bye\n");
					printWriter.flush();
//					채팅에 사용한 통신 객체를 닫아준다.
					if (serverSocket != null) { try { serverSocket.close(); } catch (IOException err) { err.printStackTrace(); } }
					if (socket != null) { try { socket.close(); } catch (IOException err) { err.printStackTrace(); } }
//					채팅창을 닫는다.
					System.exit(0);
				} else {
//					윈도우에서 "X"을 클릭해도 윈도우가 닫히지 않게한다.
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Server3 server = new Server3();
	}
	
//	클라이언트에서 언제 메시지를 보내올지 모르기 때문에 통신이 종료될 때 까지 반복하며 클라이언트에서 전송되는 메시지는 스레드로 받는다.
	@Override
	public void run() {
		
	}

//	텍스트 필드에서 엔터키를 누르거나 전송 버튼을 클릭해서 ActionListener가 실행되면 클라이언트로 데이터를 전송한다.
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}












