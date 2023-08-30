package co.yeadam.project;

import java.util.Scanner;

import co.yeadam.project.common.SHA256;
import co.yeadam.project.membership.service.MembershipService;
import co.yeadam.project.membership.service.MembershipVO;
import co.yeadam.project.membership.serviceImpl.MembershipServiceImpl;



public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private MembershipService dao = new MembershipServiceImpl();
	private SHA256 sha256 = new SHA256();
	private ReservationMenu rm = new ReservationMenu();	//예약 및 관리 메뉴
	private Mypage mp = new Mypage();	//예약 및 관리 메뉴
	static String loginId;

	private void loginTitle() {
		// 회원가입 //로그인
		System.out.println("=============================");
		System.out.println("========= plane plan =========");
		System.out.println("========= 로그인	메뉴 =========");
		System.out.println("=============================");
		System.out.println("======== 1. 로그인 하기 ========");
		System.out.println("======== 2. 회 원 가 입 ========");
		System.out.println("======== 3. 아이디 찾기 ========");
		System.out.println("======== 4. 비밀번호 찾기 ========");
		System.out.println("======== 5. 종     료 ========");
		System.out.println("원하는 작업을 선택하세요");
	}

	private void mainTitle() {
		System.out.println("=============================");
		System.out.println("========= plane plan =========");
		System.out.println("========= 메인	메뉴 =========");
		System.out.println("=============================");
		System.out.println("======== 1. 예약 및 관리 ========");
		System.out.println("======== 2. 내 정보 ========");
		System.out.println("======== 3. 로그 아웃 ========");
		System.out.println("원하는 작업을 선택하세요");
	}

	public void run() {
		mainpage();
	}

	private void mainpage() {
		boolean a = false;

		while (!a) {
			loginTitle();
			int menu = sc.nextInt(); sc.nextLine();

			switch (menu) {
			case 1:
				membershiplogin();
				break;
			case 2:
				membershipinsert();
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				a = true;
				System.out.println("프로그램 종료");
				break;
			}
		}
		a = true;
	}

	private void membershipinsert() {
		MembershipVO vo = new MembershipVO();
		System.out.println("====== 회원 가입 =======");
		System.out.println("이름 입력");
		vo.setUserName(sc.nextLine());
		System.out.println("회원 아이디 입력");
		vo.setUserId(sc.nextLine());
		System.out.println("패스워드 입력");
		vo.setUserPassword(sc.nextLine());
		System.out.println("휴대폰번호 입력");
		vo.setUserTel(sc.nextLine());
		System.out.println("이메일 정보 입력");
		vo.setUserMail(sc.nextLine());

		String x = sha256.encrypt(vo.getUserPassword());
		vo.setUserPassword(x);

		int n = dao.membershipInsert(vo);
		if (n != 0) {
			System.out.println("등록 완료");
		} else {
			System.out.println("등록 실패");
		}
	}

	private boolean membershiplogin() {
		SHA256 sha256 = new SHA256();
		MembershipVO vo = new MembershipVO();
		boolean b = false;

		System.out.println("====== 로그인 하기 ======");
		System.out.println("회원 아이디를 입력하세요");
		loginId = sc.nextLine();
		vo.setUserId(loginId);
		System.out.println("패스워드를 입력하세요");
		vo.setUserPassword(sc.nextLine());
		vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));

		vo = dao.membershipSelect(vo);

		if (vo != null) {
			System.out.println(vo.getUserId() + " 환영합니다.");
			b = true;
			subPage();
		} else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다");
			System.out.println("회원가입 또는 아이디 찾기, 비밀번호 찾기를 하십시오");
		}
		return b;
	}

	private void subPage() {
		boolean b = false;

		while (!b) {
			mainTitle();
			int menu = sc.nextInt(); sc.nextLine();

			switch (menu) {
			case 1:
				rm.run();
				break;
			case 2:
				mp.run();
				break;
			case 3:
				b = true;
				System.out.println("메인으료");
				break;
			}
		}
		b = true;
	}

}
