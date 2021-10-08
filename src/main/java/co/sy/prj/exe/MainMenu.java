package co.sy.prj.exe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.sy.prj.service.MemberService;
import co.sy.prj.service.MemberVO;
import co.sy.prj.serviceImpl.MemberServiceImpl;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memberService = new MemberServiceImpl();
	
	private void menuTitle() {
		System.out.println("===============================");
		System.out.println("==== [ MEMBER 관리 SYSTEM ] ====");
		System.out.println("======== 1. 전체목록 조회  ========");
		System.out.println("======== 2. 회원 조회     ========");
		System.out.println("======== 3. 회원 등록     ========");
		System.out.println("======== 4. 회원정보 수정  ========");
		System.out.println("======== 5. 회원정보 삭제  ========");
		System.out.println("======== 6. 종      료  ========");
		System.out.println("* 원하는 작업번호를 입력하세요. ");
	}
	
	public void run() {
		while(true) {
			menuTitle();
			int key = sc.nextInt(); sc.nextLine();
			switch (key) {
				case 1:
					selectMemberList();
					break;
				case 2:
					selectMember();
					break;
				case 3:
					insertMember();
					break;
				case 4:
					updateMember();
					break;
				case 5:
					deleteMember();
					break;
				case 6:
					sc.close();
					return;
			}
		}
	}

	private void deleteMember() {
		MemberVO vo = new MemberVO();
		System.out.println("====================== ");
		System.out.println("삭제할 회원 아이디를 입력하세요.");
		System.out.println("====================== ");
		vo.setId(sc.nextLine());
		int n = memberService.deleteMember(vo);
		if(n != 0) {
			System.out.println("==================");
			System.out.println("정상적으로 삭제되었습니다.");
			System.out.println("Enter Key를 누르세요..");
		} else {
			System.out.println("==================");
			System.out.println("삭제를 실패하였습니다.");
			System.out.println("Enter Key를 누르세요..");
		}
		sc.nextLine();
	}

	private void updateMember() {
		MemberVO vo = new MemberVO();
		System.out.println("UPDATE 하는 곳 입니다.");
		
		while(true) {
			System.out.println("===================");
			System.out.print("UPDATE 할 MEMBER의 ID를 입력하세요 : ");
			vo.setId(sc.nextLine());
			vo = memberService.selectMember(vo);
			if (vo.getName() == null) {
				System.out.println("존재하지 않는 회원입니다.");
			} else break;
		}
		
		System.out.print("UPDATE 할 MEMBER의 NAME을 입력하세요 : ");
		vo.setName(sc.nextLine());
		System.out.print("UPDATE 할 MEMBER의 PASSWORD를 입력하세요 : ");
		vo.setPassword(sc.nextLine());
		System.out.print("UPDATE 할 MEMBER의 TEL을 입력하세요 : ");
		vo.setTel(sc.nextLine());
		System.out.print("UPDATE 할 MEMBER의 ADDRESS를 입력하세요 : ");
		vo.setAddress(sc.nextLine());
		System.out.print("UPDATE 할 MEMBER의 AUTHOR(ADMIN / USER)를 입력하세요 : ");
		vo.setAuthor(sc.nextLine());
		
		int n = memberService.updateMember(vo);
		if(n != 0) {
			System.out.println("==================");
			System.out.println("정상적으로 UPDATE되었습니다.");
			System.out.println("Enter Key를 누르세요..");
		} else {
			System.out.println("==================");
			System.out.println("UPDATE를 실패하였습니다.");
			System.out.println("Enter Key를 누르세요..");
		}
		sc.nextLine();
		
	}

	private void insertMember() {
		MemberVO vo = new MemberVO();
		System.out.println("INSERT 하는 곳 입니다.");
		while (true) {
			System.out.println("=========================");
			System.out.print("INSERT 할 MEMBER의 ID를 입력하세요 : ");
			vo.setId(sc.nextLine());
			vo = memberService.selectMember(vo);
			if(vo.getName() != null) {
				System.out.println("이미 존재하는 MEMBER 입니다.");
			} else break;
		}
		
		System.out.print("INSERT 할 MEMBER의 NAME을 입력하세요 : ");
		vo.setName(sc.nextLine());
		System.out.print("INSERT 할 MEMBER의 PASSWORD를 입력하세요 : ");
		vo.setPassword(sc.nextLine());
		System.out.print("INSERT 할 MEMBER의 TEL을 입력하세요 : ");
		vo.setTel(sc.nextLine());
		System.out.print("INSERT 할 MEMBER의 ADDRESS를 입력하세요 : ");
		vo.setAddress(sc.nextLine());
		System.out.print("INSERT 할 MEMBER의 AUTHOR(ADMIN / USER)를 입력하세요 : ");
		vo.setAuthor(sc.nextLine());
		
		int n = memberService.insertMember(vo);
		if(n != 0) {
			System.out.println("==================");
			System.out.println("정상적으로 INSERT되었습니다.");
			System.out.println("Enter Key를 누르세요..");
		} else {
			System.out.println("==================");
			System.out.println("INSERT를 실패하였습니다.");
			System.out.println("Enter Key를 누르세요..");
		}
		sc.nextLine();
		
	}

	private void selectMember() {
		MemberVO vo = new MemberVO();
		System.out.println("======================");
		System.out.println("검색할 회원 아이디를 입력하세요.");
		vo.setId(sc.nextLine());
		vo = memberService.selectMember(vo);
		vo.toString();
		System.out.println("=====================");
		System.out.println("Enter Key를 누르세요....");
		sc.nextLine();
	}

	private void selectMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memberService.selectMemberList();
		System.out.println("=======================");
		System.out.println("==== 회원 목록 정보 =======");
		for(MemberVO member : list) {
			member.toString();
		}
		System.out.println("=====================");
		System.out.println("Enter Key를 누르세요....");
		sc.nextLine();
	}
}
