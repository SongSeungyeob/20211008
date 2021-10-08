package co.sy.prj.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> selectMemberList();
	MemberVO selectMember(MemberVO vo);	// 
	int insertMember(MemberVO vo);	
	int updateMember(MemberVO vo);
	int deleteMember(MemberVO vo);
}
