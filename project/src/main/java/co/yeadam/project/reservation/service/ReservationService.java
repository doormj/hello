package co.yeadam.project.reservation.service;

import java.util.List;

public interface ReservationService {
	List<ReservationVO> reservationList();				// 여행객 조회
	ReservationVO reservationSelect(ReservationVO vo);	// 하나의 여행객 조회
	int reservationInsert(ReservationVO vo);			// 여행객 등록
	int reservationUpdate(ReservationVO vo);			// 여행 정보 수정
	int reservationDelete(ReservationVO vo);			// 여행 정보 삭제
	
}
