package co.yeadam.project.membership.service;

import java.util.List;

public interface MembershipService {
	MembershipVO membershipSelectList(MembershipVO vo);
	MembershipVO membershipSelect(MembershipVO vo);
	MembershipVO membershipIdSelect(MembershipVO vo);
	int membershipInsert(MembershipVO vo);
	int membershipPassUpdate(MembershipVO vo);
	int membershipTelUpdate(MembershipVO vo);
	int membershipMailUpdate(MembershipVO vo);
	int membershipDelete(MembershipVO vo);
	int membershipFindPass(MembershipVO vo);
}
