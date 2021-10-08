package co.sy.prj;

import co.sy.prj.exe.MainMenu;

public class MainApp {
	public static void main(String[] args) {	
		MainMenu mainMenu = new MainMenu();
		mainMenu.run();
//	      DataSource dao = DataSource.getInstance();
//	      dao.getConnection();
//	      System.out.println("Hello ~~~~~~ ");
		
//		MemberService memberService = new MemberServiceImpl();
//		List<MemberVO> members = new ArrayList<MemberVO>();
//		MemberVO vo = new MemberVO();
//		vo.setId("sy");
//		vo = memberService.selectMember(vo);
//		vo.toString();
//		System.out.println("===================================================");
//		
//		vo = new MemberVO();
//		vo.setId("kim");
//		vo.setPassword("4567");
//		vo.setName("김치국");
//		vo.setTel(null);
//		vo.setAddress(null);
//		vo.setAuthor("USER");
//		int n = memberService.insertMember(vo);
//		if(n != 0) {
//			System.out.println("SUCCESS INSERT MEMBER");
//		} else {
//			System.out.println("FAILURE INSERT MEMBER.");
//		}
//		
//		members = memberService.selectMemberList();
//		for(MemberVO member : members) {
//			member.toString();
//		}
//		
//		System.out.println("===================================================");
		
	}
}
