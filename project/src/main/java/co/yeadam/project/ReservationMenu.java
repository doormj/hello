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
		System.out.println("========= 예약 및 관리 =========");
		System.out.println("=============================");
		System.out.println("======== 1. 예약지 선택 및 예약 ========");
		System.out.println("======== 2. 예약 정보 확인 ========");
		System.out.println("======== 3. 예약 정보 수정 ========");
		System.out.println("======== 4. 예약 취소 하기 ========");
		System.out.println("======== 5. 뒤로	가기 ========");
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
				reserveInsert();
				break;
			case 2:
				reserveSelectList();
				break;
			case 3:
				System.out.println("예약 정보 수정은 투숙할 호텔로 문의 바랍니다..");
				break;
			case 4:
				reserveDelete();
				break;
			case 5:
				a = true;
				System.out.println("메인으로");
				break;
			}
		}
	}

	private void reserveDelete() {
		ReservationVO vo = new ReservationVO();
		System.out.println("======== 4. 예약 취소 하기 ========");
		System.out.println("취소할 예약번호를 입력하세요.");
		vo.setReserveNum(sc.nextLine());
		
		int n = dao.reservationDelete(vo);
		
		if (n != 0) {
			System.out.println("예약 취소 성공");
		} else {
			System.out.println("예약 취소 실패");			
		}
	}

	private void reserveSelectList() {
		ReservationVO vo = new ReservationVO();
		System.out.println("======== 2. 예약 정보 확인 ========");
		System.out.println("조회할 예약번호를 입력하세요.");
		String num = sc.nextLine();
		vo.setReserveNum(num);
		vo = dao.reservationSelect(vo);
		
		if (vo != null) {
			System.out.println(vo.showInfo());
		} else {
			System.out.println("예약정보가 존재하지 않습니다");
		}
	}

	private void reserveInsert() {
		ReservationVO vo = new ReservationVO();
		System.out.println("======== 1. 예약지 선택 및 예약 ========");
		System.out.println("예약자명을 입력하세요");
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
			System.out.println("1.신라스테이 해운대 / 1박: 115334원");
			System.out.println("2.시그니엘 부산 / 1박: 304919원");
			System.out.println("3.파라다이스 호텔 부산 / 1박: 223469원");
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
			System.out.println("1.할레쿨라니 오키나와 / 1박: 436470원");
			System.out.println("2.호텔 몬토레 오키나와 / 1박: 128694원");
			System.out.println("3.르네상스 오키나와 리조트 / 1박: 381263원");
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
			System.out.println("1.풀먼 파리 투르 에펠 / 1박: 462033원");
			System.out.println("2.호텔 오페라 드안틴 / 1박: 142714원");
			System.out.println("3.호텔 르 더비 알마 / 1박: 311827");
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
			SimpleDateFormat transFormat = new SimpleDateFormat("yy-MM-dd");
			System.out.println("숙박 시작 날짜를 입력하세요(yy-MM-dd)");
			String str = sc.nextLine();	
			Date startDate = null;
			try {
				startDate = transFormat.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(startDate);
			System.out.println("숙박 시작날짜: " + transFormat.format(cal.getTime()));
			System.out.println("숙박 기간을 입력하세요");
			int dateTerm = sc.nextInt(); sc.nextLine();
			cal.add(Calendar.DATE, dateTerm);
			System.out.println("숙박 끝 날짜: " + transFormat.format(cal.getTime()));
			String str2 = transFormat.format(cal.getTime());
			Date endDate = null;
			try {
				endDate = transFormat.parse(str2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vo.setReserveStartDate(startDate);
			vo.setReserveLastDate(endDate);
			vo.setReserveTerm(dateTerm);
			
//			String yes = "y";
//			String no = "n";
//			String YES = yes.toUpperCase();
//			String NO = no.toUpperCase();
			
			int hotelMoney = 0;
			if(vo.getHotelName().equals("신라스테이 해운대")) {
				hotelMoney = dateTerm * 115334;
			}
			if(vo.getHotelName().equals("시그니엘 부산")) {
				hotelMoney = dateTerm * 304919;
			}
			if(vo.getHotelName().equals("파라다이스 호텔 부산")) {
				hotelMoney = dateTerm * 223469;
			}
			if(vo.getHotelName().equals("할레쿨라니 오키나와")) {
				hotelMoney = dateTerm * 436470;
			}
			if(vo.getHotelName().equals("호텔 몬토레 오키나와")) {
				hotelMoney = dateTerm * 128694;
			}
			if(vo.getHotelName().equals("르네상스 오키나와 리조트")) {
				hotelMoney = dateTerm * 381263;
			}
			if(vo.getHotelName().equals("풀먼 파리 투르 에펠")) {
				hotelMoney = dateTerm * 462033;
			}
			if(vo.getHotelName().equals("호텔 오페라 드안틴")) {
				hotelMoney = dateTerm * 142714;
			}
			if(vo.getHotelName().equals("호텔 르 더비 알마")) {
				hotelMoney = dateTerm * 311827;
			}
			
			System.out.println("결제금액은 " + hotelMoney +"원 입니다. 결제하시겠습니까?");
			System.out.println("결제는 Y 를 입력, 취소는 N 을 입력하세요");
			String decide = sc.nextLine();
			switch(decide) {
			case "Y":
				vo.setReservationFee(hotelMoney);	
				int n = dao.reservationInsert(vo);
				System.out.println("예약이 완료 되었습니다.");
				System.out.println("예약 번호는 " + vo.getReserveNum() + "입니다.");
				break;
			case "N":
				System.out.println("결제를 취소하셨습니다.");
				break;
			default :
				System.out.println("잘못 입력하였습니다.");
				break;
			}
	}
}
