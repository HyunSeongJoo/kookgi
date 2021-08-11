package com.koreait.listenerTest;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.multi.MultiListUI;

public class ListBoxTest2 extends JFrame implements ActionListener {

	JLabel topLabel;
	
	JPanel listPanel;
	
//	List singleList;					// 단일 선택 리스트 박스 => java.awt 패키지
	JList<String> singleList;			// 단일 선택 리스트 박스 => javax.swing 패키지
	DefaultListModel<String> single; 	// singleList에 표시할 데이터 목록을 저장하는 객체
	
//	List multiList;						// 다중 선택 리스트 박스 => java.awt 패키지
	JList<String> multiList;			// 다중 선택 리스트 박스 => javax.swing 패키지
	DefaultListModel<String> multi; 	// multiList에 표시할 데이터 목록을 저장하는 객체
	
	JPanel buttonPanel;
	JButton showButton;
	JButton removeButton;
	
	JLabel bottomLabel;
	
	public ListBoxTest2() {
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
//		DefaultListModel 클래스 객체를 만들고 addElement() 메소드로 데이터를 추가한 후 DefaultListModel 클래스 객체를
//		JList 클래스의 생성자 인수로 넘겨서 만든다.
		single = new DefaultListModel<String>();
		single.addElement("고구마");
		single.addElement("감자");
		single.addElement("옥수수");
		single.addElement("오이");
		single.addElement("호박");
		single.addElement("당근");
		singleList = new JList<String>(single);
		
//		JList 클래스는 스크롤 바를 표시할 수 없으므로 JScrollPane 클래스 객체를 사용해서 스크롤 바를 표시한다.
//		JScrollPane 클래스 객체를 생성할 때 생성자의 인수로 JList 클래스 객체를 넘겨서 스크롤 바를 만든다.
		JScrollPane jspSingle = new JScrollPane(singleList);
//		JScrollPane 클래스 객체의 크기를 설정하지 않으면 JScrollPane 클래스 객체를 생성할 때 넣어준 JList 클래스 객체의
//		크기로 크기가 설정되서 화면에 스크롤 바가 보이지 않기 때문에 setPreferredSize() 메소드를 실행해서 크기를 크게
//		지정해야 스크롤 바가 표시된다.
		jspSingle.setPreferredSize(new Dimension(120, 75));
//		JList 클래스 객체를 넣어준 JScrollPane 클래스 객체를 윈도우에 추가한다.
//		listPanel.add(singleList);		// 이렇게 추가하면 안된다.
		
//		setSelectionMode() 메소드로 JList 클래스로 생성한 리스트 박스의 데이터 선택 모드를 지정할 수 있다.
//		데이터 선택 모드는 ListSelectionModel 인터페이스에 상수로 준비되어 있다.
//		SINGLE_SELECTION: 단일 선택 모드
//		SINGLE_INTERVAL_SELECTION: 다중 선택 모드 => shift 키를 사용하는 다중 선택만 지원한다.
//		MULTIPLE_INTERVAL_SELECTION: 다중 선택 모드 => shift와 ctrl 키를 사용하는 다중 선택을 지원한다. => 기본값
		singleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPanel.add(jspSingle);
		
		multi = new DefaultListModel<String>();
		multi.addElement("괌");
		multi.addElement("코타키나바루");
		multi.addElement("다낭");
		multi.addElement("나트랑");
		multi.addElement("대만");
		multi.addElement("보라카이");
		multiList = new JList<String>(multi);
		JScrollPane jspMulti = new JScrollPane(multiList);
		jspMulti.setPreferredSize(new Dimension(120, 75));
		multiList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPanel.add(jspMulti);
		
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
		
		ListBoxTest2 graphic = new ListBoxTest2();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		switch (e.getActionCommand()) {
			case "보기":
//				getSelectedIndex() 메소드로 단일 리스트 박스에서 몇 번째 index가 선택되었나 얻어온다. => index는 0부터 시작한다.
//				bottomLabel.setText(singleList.getSelectedIndex() + "");
//				getSelectedValue() 메소드로 단일 리스트 박스에서 선택한 데이터를 얻어온다.
//				bottomLabel.setText("왼쪽 목록: " + singleList.getSelectedValue());
				
				String str = "왼쪽 목록: ";
				if (singleList.getSelectedValue() == null) {
					str += "없음";
				} else {
					str += singleList.getSelectedValue();
				}
				
				str += ", 오른쪽 목록: ";
//				다중 선택 리스트 박스는 2개 이상을 선택할 수 있으므로 선택된 데이터 값을 배열로 받아야 한다.
//				getSelectedIndices() 메소드로 다중 리스트 박스에서 선택된 데이터의 index를 배열 형태로 얻어온다.
//				int[] item = multiList.getSelectedIndices();
//				for (int i = 0; i < item.length; i++) {
//					System.out.println(item[i]);
//				}
//				getSelectedValuesList() 메소드는 다중 선택 리스트 박스에서 선택된 데이터를 List 인터페이스 형태로 얻어온다.
//				ArrayList로 형변환 시켜서 사용한다.
				ArrayList<String> items = null;
				try {
					items = (ArrayList<String>) multiList.getSelectedValuesList();
				} catch (Exception e2) { }
				
				if (items == null) {
					str += "없음";
				} else {
					System.out.println(items.size());
					for (int i = 0; i < items.size(); i++) {
						if (i > 0) {
							str += ", ";
						}
						str += items.get(i);
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
					JOptionPane.showMessageDialog(singleList, singleList.getSelectedValue() + " 삭제 성공");
//					JList에 데이터를 추가할 때 DefaultListModel 클래스 객체에 addElement() 메소드를 사용해 데이터를 추가했던 것 처럼
//					데이터를 제거할 때도 DefaultListModel 클래스 객체에서 해야 한다.
//					removeElement() 메소드로 인수로 지정된 데이터를 리스트 박스에서 제거한다.
//					single.removeElement(singleList.getSelectedValue());
//					removeElementAt() 메소드로 인수로 지정된 index에 해당되는 데이터를 리스트 박스에서 제거한다.
					single.removeElementAt(singleList.getSelectedIndex());
				}
				
//				다중 리스트 박스에서 삭제할 데이터는 ArrayList로 index는 배열로 받아와야 한다.
				int[] deleteIndex = multiList.getSelectedIndices();
				ArrayList<String> deleteItem = null;
				try {
					deleteItem = (ArrayList<String>) multiList.getSelectedValuesList();
				} catch (Exception e2) { }

				if (deleteIndex.length == 0) {
					JOptionPane.showMessageDialog(multiList, "오른쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
				} else {
					
					String deleteList = "";
					for (int i = 0; i < deleteIndex.length; i++) {
						if (i > 0) {
							deleteList += ", ";
						}
						deleteList += deleteItem.get(i);
					}
					JOptionPane.showMessageDialog(multiList, deleteList + " 삭제 성공");
					
//					리스트 박스 앞쪽부터 삭제
					int delIndex = 0;
					for (int i = 0; i < deleteIndex.length; i++) {
						multi.removeElementAt(deleteIndex[i] - delIndex++);
					}
//					for (int position : deleteIndex) {
//						multi.removeElementAt(position - delIndex++);
//					}
					
//					리스트 박스 뒤쪽부터 삭제
//					for (int i = deleteIndex.length - 1; i >= 0; i--) {
//						multi.removeElementAt(deleteIndex[i]);
//					}
					
				}
				break;
		}
	}

}























