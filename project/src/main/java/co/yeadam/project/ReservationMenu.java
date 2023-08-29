package co.yeadam.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import co.yeadam.project.reservation.service.ReservationService;
import co.yeadam.project.reservation.service.ReservationVO;
import co.yeadam.project.reservation.serviceImpl.ReservationServiceImpl;

public class ReservationMenu {
	private Scanner sc = new Scanner(System.in);
	ReservationService dao = new ReservationServiceImpl();

	private void reservationTitle() {
		System.out.println("=============================");
		System.out.println("========= plane plan =========");
		System.out.println("========= 예약 관리 =========");
		System.out.println("=============================");
		System.out.println("======== 1. 예약지 선택 및 예약 ========");
		System.out.println("======== 2. 예약 정보 확인 ========");
		System.out.println("======== 3. 예약 정보 수정 ========");
		System.out.println("======== 4. 예약 취소 하기 ========");
		System.out.println("======== 5. 메인 으로 ========");
		System.out.println("원하는 작업을 선택하세요");
	}

	public void run() {
		boolean a = false;

		while (!a) {
			reservationTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				reserveinsert();
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				a = true;
				System.out.println("메인으로");
				break;
			}
		}
	}

	private void reserveinsert() {
		ReservationVO vo = new ReservationVO();
		System.out.println("이름을 입력하세요");
		vo.setUserName(sc.nextLine());
		System.out.println("전화번호를 입력하세요");
		vo.setUserTel(sc.nextLine());
		System.out.println("1.부산 2.오키나와 3.파리");
		System.out.println("지역을 선택하시오>>");
		int selectlocation = sc.nextInt(); sc.nextLine();
		switch(selectlocation) {
		case 1:
			vo.setLocation("부산");
			System.out.println("부산을 선택하였습니다.");
			System.out.println("원하는 호텔을 고르세요");
			System.out.println("1.신라스테이 해운대");
			System.out.println("2.시그니엘 부산");
			System.out.println("3.파라다이스 호텔 부산");
			int busanHotel = sc.nextInt();sc.nextLine();
			
			if(busanHotel == 1) {
				vo.setHotelName("신라스테이 해운대");
			} else if(busanHotel == 2) {
				vo.setHotelName("시그니엘 부산");
			} else if (busanHotel == 3){
				vo.setHotelName("파라다이스 호텔 부산");
			} else {
				System.out.println("다시 선택해주십시오");
			}
			break;
		case 2:
			vo.setLocation("오키나와");
			System.out.println("오키나와를 선택하였습니다.");
			System.out.println("원하는 호텔을 고르세요");
			System.out.println("1.할레쿨라니 오키나와");
			System.out.println("2.호텔 몬토레 오키나와");
			System.out.println("3.르네상스 오키나와 리조트");
			int okinawaHotel = sc.nextInt();sc.nextLine();
			
			if(okinawaHotel == 1) {
				vo.setHotelName("할레쿨라니 오키나와");
			} else if(okinawaHotel == 2) {
				vo.setHotelName("호텔 몬토레 오키나와");
			} else if (okinawaHotel == 3){
				vo.setHotelName("르네상스 오키나와 리조트");
			} else {
				System.out.println("다시 선택해주십시오");
			}
			break;
		case 3:
			vo.setLocation("파리");
			System.out.println("파리를 선택하였습니다.");
			System.out.println("원하는 호텔을 고르세요");
			System.out.println("1.신라스테이 해운대");
			System.out.println("2.시그니엘 부산");
			System.out.println("3.파라다이스 호텔 부산");
			int parisHotel = sc.nextInt();sc.nextLine();
			
			if(parisHotel == 1) {
				vo.setHotelName("풀먼 파리 투르 에펠");
			} else if(parisHotel == 2) {
				vo.setHotelName("호텔 오페라 드안틴");
			} else if (parisHotel == 3){
				vo.setHotelName("호텔 르 더비 알마");
			} else {
				System.out.println("다시 선택해주십시오");
			}
			break;
		}
		
			Calendar cal = Calendar.getInstance();
			System.out.println("숙박 시작 날짜를 입력하세요(yy-MM-dd)");
			String str = sc.nextLine();	
			SimpleDateFormat transFormat = new SimpleDateFormat("yy-MM-dd");
			Date startDate = null;
			try {
				startDate = transFormat.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(startDate);
			System.out.println("숙박 시작날짜: " + transFormat.format(cal.getTime()));
			System.out.println("숙박 끝 날짜를 입력하세요");
			int lastDate = sc.nextInt(); sc.nextLine();
			cal.add(Calendar.DATE, lastDate);
			System.out.println("숙박 끝 날짜: " + transFormat.format(cal.getTime()));
			vo.setReserveStartDate(startDate);
			vo.setReserveLastDate(Date);
			
	}

}
