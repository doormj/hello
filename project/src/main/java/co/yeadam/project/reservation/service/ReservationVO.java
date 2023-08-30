package co.yeadam.project.reservation.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class ReservationVO {
	private String location;
	private String hotelName;
	private Date reserveStartDate;
	private Date reserveLastDate;
	private String userTel;
	private int reservationFee;
	private String userName;
	private String reserveNum;
	private int reserveTerm;
	
	
	public String showInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String str = 
					"예약인: " + userName + "\n" + //
					"예약자 전화번호: " + userTel + "\n" + //
					"호텔명: " + hotelName + "\n" + //
					"예약시작일자: " + sdf.format(reserveStartDate) + "\n" + //
					"예약종료일자: " + sdf.format(reserveLastDate) + "\n" + //
					"예약 기간: " + reserveTerm + "일" + "\n" + //
					"예약금: "  + reservationFee + "원" + "\n"; //
		return str;
	}
}
