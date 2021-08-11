package com.koreait.memoProjectDB;

import java.util.ArrayList;
import java.util.Scanner;

public class MemoProjectDBMain {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int menu = 0;
		
		while (menu != 5) {
			while (true) {
				System.out.println("============================================");
				System.out.println(" 1.입력  2.목록보기  3.수정  4.삭제  5.종료 ");
				System.out.println("============================================");
				System.out.print("원하는 메뉴를 입력하세요: ");
				menu = scanner.nextInt();
				if (menu >= 1 && menu <= 5) {
					break;
				}
				System.out.println("메뉴는 1 ~ 5 사이로 입력해야 합니다.");
			}
			
			switch (menu) {
				case 1:
					insert();
					break;
				case 2:
					select();
					break;
				case 3:
					update();
					break;
				case 4:
					delete();
					break;
			}
		}
		System.out.println("프로그램을 종료합니다.");
		
	}

//	키보드로 수정할 글번호를 넘겨받고 테이블에 저장된 글을 수정하는 update sql 명령을 실행하는 MemoDAO 클래스의 메소드를
//	호출하는 메소드
	private static void update() {
//		수정할 글번호를 입력받는다.
		Scanner scanner = new Scanner(System.in);
		System.out.print("수정할 글번호를 입력하세요: ");
		int idx = scanner.nextInt();
		
//		MemoDAO 클래스의 테이블에 저장된 글을 수정하는 메소드를 호출한다.
		if (MemoDAO.update(idx)) {
			System.out.println(idx + "번 글 수정완료!!!");
		} else {
			System.out.println(idx + "번 글이 존재하지 않거나 비밀번호가 올바르지 않습니다.");
		}
	}

//	키보드로 삭제할 글번호를 넘겨받고 테이블에 저장된 글을 삭제하는 delete sql 명령을 실행하는 MemoDAO 클래스의 메소드를
//	호출하는 메소드
	private static void delete() {
//		삭제할 글번호를 입력받는다.
		Scanner scanner = new Scanner(System.in);
		System.out.print("삭제할 글번호를 입력하세요: ");
		int idx = scanner.nextInt();
		
//		MemoDAO 클래스의 테이블에 저장된 글을 삭제하는 메소드를 호출한다.
		if (MemoDAO.delete(idx)) {
			System.out.println(idx + "번 글 삭제완료!!!");
		} else {
			System.out.println(idx + "번 글이 존재하지 않거나 비밀번호가 올바르지 않습니다.");
		}
	}

//	테이블에 저장된 전체 글 목록을 글번호(idx)의 내림차순(최신글 순)으로 얻어오는 select sql 명령을 실행(실행 결과는 ResultSet
//	객체에 저장)하고 실행된 결과를 ArrayList에 저장시켜 리턴하는 MemoDAO 클래스 객체의 메소드를 호출하는 메소드
	private static void select() {
//		MemoDAO 클래스에서 테이블에 저장된 글 목록을 얻어오는 메소드를 호출하고 얻어온 글 목록을 ArrayList에 저장한다.
		ArrayList<MemoVO> list = MemoDAO.select();
//		얻어온 글 목록을 출력한다. 반드시 null을 먼저 비교한다.
		if (list == null || list.size() == 0) {
			System.out.println("테이블에 저장된 데이터가 없습니다.");
		} else {
			for (MemoVO vo : list) {
				System.out.println(vo);
			}
		}
	}

//	키보드로 이름, 비밀번호, 메모를 입력받고 테이블에 저장하는 insert sql 명령을 실행하는 MemoDAO 클래스의 메소드를 호출하는 메소드
	private static void insert() {
//		키보드로 이름, 비밀번호, 메모를 입력받는다.
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름: ");
		String name = scanner.nextLine().trim();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine().trim();
		System.out.print("메모: ");
		String memo = scanner.nextLine().trim();
		
//		입력받은 데이터를 MemoVO 클래스 객체를 생성하고 저장한다.
		MemoVO vo = new MemoVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMemo(memo);
		
//		테이블에 저장할 데이터가 저장된 MemoVO 클래스 객체 vo를 인수로 넘겨서 MemoDAO 클래스의 테이블에 데이터를 저장하는 insert sql
//		명령을 실행하는 메소드를 호출한다.
		if (MemoDAO.insert(vo)) {
			System.out.println("저장완료!!!");
		} else {
			System.out.println("저장실패!!!");
		}
	}

}

























