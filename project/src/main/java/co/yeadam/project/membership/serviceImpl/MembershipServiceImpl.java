package co.yeadam.project.membership.serviceImpl;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.membership.map.MembershipMapper;
import co.yeadam.project.membership.service.MembershipService;
import co.yeadam.project.membership.service.MembershipVO;

public class MembershipServiceImpl implements MembershipService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private MembershipMapper map = sqlSession.getMapper(MembershipMapper.class);

	@Override
	public MembershipVO membershipSelectList(MembershipVO vo) {
		return map.membershipSelectList(vo);
	}

	@Override
	public MembershipVO membershipSelect(MembershipVO vo) {
		return map.membershipSelect(vo);
	}

	@Override
	public int membershipInsert(MembershipVO vo) {
		return map.membershipInsert(vo);
	}

	@Override
	public int membershipDelete(MembershipVO vo) {
		return map.membershipDelete(vo);
	}

	@Override
	public int membershipPassUpdate(MembershipVO vo) {
		return map.membershipPassUpdate(vo);
	}

	@Override
	public int membershipTelUpdate(MembershipVO vo) {
		return map.membershipTelUpdate(vo);
	}

	@Override
	public int membershipMailUpdate(MembershipVO vo) {
		return map.membershipMailUpdate(vo);
	}

}
