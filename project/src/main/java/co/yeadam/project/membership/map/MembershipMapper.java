package co.yeadam.project.membership.map;

import co.yeadam.project.membership.service.MembershipVO;

public interface MembershipMapper {
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
