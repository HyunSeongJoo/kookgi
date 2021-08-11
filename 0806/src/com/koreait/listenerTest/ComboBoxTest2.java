package com.koreait.listenerTest;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ComboBoxTest2 extends JFrame implements ActionListener {

	JLabel topLabel;
	
	JPanel comboPanel;
//	Choice comboBox;				// 콤보 박스 => java.awt 패키지
	JComboBox<String> comboBox;		// 콤보 박스 => javax.swing 패키지
	JButton showButton;
	JButton removeButton;
	
	JPanel addPanel;
//	TextField addField;				// 텍스트 필드 => java.awt 패키지
	JTextField addField;			// 텍스트 필드 => javax.swing 패키지
	JButton addButton;
	
	JLabel bottomLabel;
	
	public ComboBoxTest2() {
		setTitle("ComboBox");
		setBounds(800, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));
		
//		윈도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("콤보 박스 테스트");
		topLabel.setFont(new Font("궁서체", Font.BOLD, 35));
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		add(topLabel);
		
//		윈도우 중단의 콤보 박스와 보기, 삭제 버튼 세팅
//		JComboBox 클래스를 사용해 콤보 박스를 만드는 방법은 2가지가 있다.
		comboPanel = new JPanel();
		
//		1. 콤보 박스에 포시할 목록을 저장한 배열을 만들고 그 배열을 JComboBox 클래스 생성자의 인수로 넘겨준다.
//		String[] fruits = {"바나나", "파인애플", "망고", "두리안", "베리베리스트로베리"};
//		comboBox = new JComboBox<String>(fruits);
		
//		2. 기본 생성자를 사용해서 JComboBox 클래스 객체를 만들고 setModel() 메소드로 DefaultComboBoxModel 객체에
//		   콤보 박스에 표시할 목록을 배열로 넘겨서 만든다.
		comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> model = 
				new DefaultComboBoxModel<String>(new String[] {"바나나", "파인애플", "망고", "두리안", "베리베리스트로베리"});
		comboBox.setModel(model);
		comboPanel.add(comboBox);
		
		showButton = new JButton("보기");
		comboPanel.add(showButton);
		removeButton = new JButton("삭제");
		comboPanel.add(removeButton);
		add(comboPanel);
		
//		윈도우 중단의 텍스트 필드와 추가 버튼 세팅
		addPanel = new JPanel();
		
		addField = new JTextField();
		addField.setPreferredSize(new Dimension(200, 27));
		addPanel.add(addField);
		
//		java.awt 패키지는 TextField 클래스 하나로 일반적인 입력과 비밀번호 입력을 모두 받지만 javax.swing 패키지는 일반 입력은
//		JTextField 클래스 객체로 받고 비밀번호는 JPasswordField 클래스 객체를 사용해서 받는다.
//		JPasswordField password = new JPasswordField(20);
//		addPanel.add(password);
		
		addButton = new JButton("추가");
		addPanel.add(addButton);
		
		add(addPanel);
		
//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다.");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);
		
//		보기, 삭제, 추가 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		removeButton.addActionListener(this);
		addButton.addActionListener(this);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ComboBoxTest2 graphic = new ComboBoxTest2();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		switch (e.getActionCommand()) {
			case "보기":
//				getSelectedIndex() 메소드로 콤보 박스에서 몇 번째 index가 선택되었나 얻어온다. => index는 0부터 시작한다.
//				bottomLabel.setText(comboBox.getSelectedIndex() + "");
//				getItemAt(index) 메소드로 index 번째 항목에 해당되는 데이터(레이블)을 얻어온다.
//				bottomLabel.setText(comboBox.getItemAt(comboBox.getSelectedIndex()));
//				getSelectedItem() 메소드로 콤보 박스에서 선택한 데이터를 얻어온다. comboBox.getItem(comboBox.getSelectedIndex())와 
//				같은 기능이 실행된다.
				bottomLabel.setText("선택된 아이템: " + comboBox.getSelectedItem());
				break;
			case "삭제":
				String message = comboBox.getSelectedItem() + "를(을) 삭제하시겠습니까?";
				int result = JOptionPane.showConfirmDialog(comboBox, message, "삭제 확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 성공");
//					removeItem(object) 메소드로 콤보 박스에서 선택된 데이터에 해당되는 데이터를 제거한다.
//					comboBox.removeItem(comboBox.getSelectedItem());
//					removeItemAt(anIndex) 메소드로 콤보 박스에서 선택된 anIndex 번째 index에 해당되는 데이터를 제거한다.
					comboBox.removeItemAt(comboBox.getSelectedIndex());
				} else {
					bottomLabel.setText(comboBox.getSelectedItem() + " 삭제 취소");
				}
				break;
			case "추가":
				String item = addField.getText().trim();
				if (item.length() == 0) {
					JOptionPane.showMessageDialog(addField, "콤보 박스에 추가할 데이터를 입력하세요", "입력 확인", JOptionPane.ERROR_MESSAGE);
				} else {
//					addItem() 메소드로 콤보 박스에 데이터를 추가한다.
					comboBox.addItem(item);
					bottomLabel.setText(item + " 추가 완료");
					
//					setSelectedIndex(anIndex) 메소드로 콤보 박스의 anIndex 번째 위치의 데이터를 콤보 박스에 표시한다. 
//					getItemCount() 메소드로 콤보 박스의 데이터 개수를 얻어온다.
					comboBox.setSelectedIndex(comboBox.getItemCount() - 1);
				}
				addField.setText("");
				addField.requestFocus();
				break;
		}
		
	}

}























