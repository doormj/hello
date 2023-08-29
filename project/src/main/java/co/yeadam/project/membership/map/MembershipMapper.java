package co.yeadam.project.membership.map;

import java.util.List;

import co.yeadam.project.membership.service.MembershipVO;

public interface MembershipMapper {
	List<MembershipVO> membershipSelectList();
	MembershipVO membershipSelect(MembershipVO vo);
	int membershipInsert(MembershipVO vo);
	int membershipUpdate(MembershipVO vo);
	int membershipDelete(MembershipVO vo);
}
