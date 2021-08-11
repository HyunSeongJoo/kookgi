package com.koreait.listenerTest;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiListUI;

public class ListBoxTest extends JFrame implements ActionListener {

	JLabel topLabel;		// 윈도우 상단의 제목 레이블
	
	JPanel listPanel;		// 단일 선택 리스트 박스와 다중 선택 리스트 박스가 올라갈 패널
	List singleList;		// 단일 선택 리스트 박스
	List multiList;			// 다중 선택 리스트 박스
	
	JPanel buttonPanel;		// 텍스트 필드와 추가 버튼이 올라갈 패널
	JButton showButton;		// 보기 버튼
	JButton removeButton;	// 삭제 버튼
	
	JLabel bottomLabel;		// 윈도우 하단에 작업 결과 메시지를 표시할 레이블
	
	public ListBoxTest() {
		setTitle("ListBox");
		setBounds(800, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));
		
//		윈도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("리스트 박스 테스트");
		topLabel.setFont(new Font("궁서체", Font.BOLD, 35));
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		add(topLabel);
		
//		윈도우 중단의 리스트 박스 세팅
		listPanel = new JPanel();
		
//		단일 선택 리스트 박스
//		new List(): 리스트 박스에 표시되는 데이터 개수가 기본값(4개)으로 보여지는 리스트 박스를 만든다.
//		new List(데이터 개수): 리스트 박스에 표시되는 데이터의 개수만큼 데이터가 보여지는 리스트 박스를 만든다.
		singleList = new List();	// 단일 선택 리스트 박스를 만든다.
//		List 클래스로 만든 리스트 박스에 add() 메소드로 표시할 항목을 추가한다.
		singleList.add("고구마");
		singleList.add("감자");
		singleList.add("옥수수");
		singleList.add("오이");
		singleList.add("호박");
		singleList.add("당근");
		listPanel.add(singleList);
		
//		다중 선택 리스트 박스
//		new List(데이터 개수, 목록 선택 모드): 리스트 박스에 표시되는 데이터의 개수만큼 데이터가 보여지는 리스트 박스를 만들고
//		목록 선택모드에 true를 입력하면 다중 목록 선택 리스트 박스가 된다.
//		목록 선택 모드의 기본값은 false이고 단일 선택 리스트 박스가 생성된다.
		multiList = new List(4, true);
		multiList.add("괌");
		multiList.add("코타키나바루");
		multiList.add("다낭");
		multiList.add("나트랑");
		multiList.add("대만");
		multiList.add("보라카이");
		listPanel.add(multiList);
		
		add(listPanel);
		
//		윈도우 중단의 보기, 삭제 버튼 세팅
		buttonPanel = new JPanel();
		showButton = new JButton("보기");
		buttonPanel.add(showButton);
		removeButton = new JButton("삭제");
		buttonPanel.add(removeButton);
		add(buttonPanel);
		
//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다.");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);
		
//		보기, 삭제, 추가 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		removeButton.addActionListener(this);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ListBoxTest graphic = new ListBoxTest();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		switch (e.getActionCommand()) {
			case "보기":
//				getSelectedIndex() 메소드로 단일 리스트 박스에서 몇 번째 index가 선택되었나 얻어온다. => index는 0부터 시작한다.
//				bottomLabel.setText(singleList.getSelectedIndex() + "");
//				getItem(index) 메소드로 index 번째 항목에 해당되는 데이터(레이블)을 얻어온다.
//				bottomLabel.setText(singleList.getItem(singleList.getSelectedIndex()));
//				getSelectedItem() 메소드로 단일 리스트 박스에서 선택한 데이터를 얻어온다.
//				bottomLabel.setText("왼쪽 목록: " + singleList.getSelectedItem());
				
				String str = "왼쪽 목록: ";
				if (singleList.getSelectedItem() == null) {
					str += "없음";
				} else {
					str += singleList.getSelectedItem();
				}
				
				str += ", 오른쪽 목록: ";
//				다중 선택 리스트 박스는 2개 이상을 선택할 수 있으므로 선택된 데이터 값을 배열로 받아야 한다.
//				getSelectedIndexes() 메소드로 다중 리스트 박스에서 선택된 데이터의 index를 배열 형태로 얻어온다.
//				getSelectedItems() 메소드는 다중 선택 리스트 박스에서 선택된 데이터를 배열 형태로 얻어온다.
				String[] items = multiList.getSelectedItems();
//				System.out.println(items.length);
				
				if (items.length == 0) {
					str += "없음";
				} else {
					for (int i = 0; i < items.length; i++) {
						if (i > 0) {
							str += ", ";
						}
						str += items[i];
					}
				}
				
				bottomLabel.setText(str);
				break;
			case "삭제":
//				getSelectedIndex() 리스트 박스에서 아무것도 선택하지 않으면 -1을 리턴한다.
//				System.out.println(singleList.getSelectedIndex());
				if (singleList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(singleList, "왼쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
				} else {
					JOptionPane.showMessageDialog(singleList, singleList.getSelectedItem() + " 삭제 성공");
//					singleList.remove(singleList.getSelectedItem());
					singleList.remove(singleList.getSelectedIndex());
				}
				
//				다중 리스트 박스에서 삭제할 데이터나 index를 배열로 받아와야 한다.
				int[] deleteIndex = multiList.getSelectedIndexes();
				String[] deleteItem = multiList.getSelectedItems();
				if (deleteIndex.length == 0) {
					JOptionPane.showMessageDialog(multiList, "오른쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
				} else {
					
					String deleteList = "";
					for (int i = 0; i < deleteIndex.length; i++) {
						if (i > 0) {
							deleteList += ", ";
						}
						deleteList += deleteItem[i];
					}
					JOptionPane.showMessageDialog(multiList, deleteList + " 삭제 성공");
					
//					리스트 박스 앞쪽부터 삭제
//					int delIndex = 0;
//					for (int i = 0; i < deleteIndex.length; i++) {
//						multiList.remove(deleteIndex[i] - delIndex++);
//					}
//					for (int position : deleteIndex) {
//						multiList.remove(position - delIndex++);
//					}
					
//					리스트 박스 뒤쪽부터 삭제
					for (int i = deleteIndex.length - 1; i >= 0; i--) {
						multiList.remove(deleteIndex[i]);
					}
					
				}
				break;
				
		}
	}

}























