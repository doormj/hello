package co.yeadam.project.reservation.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yeadam.project.common.DataSource;
import co.yeadam.project.reservation.map.ReservationMapper;
import co.yeadam.project.reservation.service.ReservationService;
import co.yeadam.project.reservation.service.ReservationVO;

public class ReservationServiceImpl implements ReservationService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ReservationMapper map = sqlSession.getMapper(ReservationMapper.class);

	@Override
	public List<ReservationVO> reservationList() {
		return map.reservationList();
	}

	@Override
	public ReservationVO reservationSelect(ReservationVO vo) {
		return map.reservationSelect(vo);
	}

	@Override
	public int reservationInsert(ReservationVO vo) {
		return map.reservationInsert(vo);
	}

	@Override
	public int reservationUpdate(ReservationVO vo) {
		return map.reservationUpdate(vo);
	}

	@Override
	public int reservationDelete(ReservationVO vo) {
		return map.reservationDelete(vo);
	}

}
