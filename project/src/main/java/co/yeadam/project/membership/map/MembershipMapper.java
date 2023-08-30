package co.yeadam.project.membership.map;

import java.util.List;

import co.yeadam.project.membership.service.MembershipVO;

public interface MembershipMapper {
	MembershipVO membershipSelectList(MembershipVO vo);
	MembershipVO membershipSelect(MembershipVO vo);
	int membershipInsert(MembershipVO vo);
	int membershipPassUpdate(MembershipVO vo);
	int membershipTelUpdate(MembershipVO vo);
	int membershipMailUpdate(MembershipVO vo);
	int membershipDelete(MembershipVO vo);
}
