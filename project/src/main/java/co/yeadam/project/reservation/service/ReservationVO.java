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
					"예약 번호: " + reserveNum + 
					"예약인: " + userName + 
					"예약자 전화번호: " + userTel +
					"호텔명: " + hotelName + 
					"예약시작일자: " + sdf.format(reserveStartDate) +
					"예약종료일자: " + sdf.format(reserveLastDate) +
					"예약 기간: " + reserveTerm +
					"예약금: "  + reservationFee;
		return str;
	}
}
