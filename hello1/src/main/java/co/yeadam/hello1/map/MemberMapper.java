package co.yeadam.hello1.map;

import java.util.List;

import co.yeadam.hello1.member.service.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDaelete(MemberVO vo);
}
