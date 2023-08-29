package co.yeadam.project.membership.service;

import java.util.List;

public interface MembershipService {
	List<MembershipVO> membershipSelectList();
	MembershipVO membershipSelect(MembershipVO vo);
	int membershipInsert(MembershipVO vo);
	int membershipUpdate(MembershipVO vo);
	int membershipDelete(MembershipVO vo);
}
