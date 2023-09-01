package co.yeadam.project;

import java.util.Scanner;

import co.yeadam.project.common.SHA256;
import co.yeadam.project.membership.service.MembershipService;
import co.yeadam.project.membership.service.MembershipVO;
import co.yeadam.project.membership.serviceImpl.MembershipServiceImpl;

public class Mypage {
	private Scanner sc = new Scanner(System.in);
	private MembershipService dao = new MembershipServiceImpl();
	private SHA256 sha256 = new SHA256();
	
	private void mypageTitle() {
		System.out.println("=============================");
		System.out.println("========= plane plan =========");
		System.out.println("========= 나의	정보 =========");
		System.out.println("=============================");
		System.out.println("======== 1. 내 정보 확인 ========");
		System.out.println("======== 2. 내 정보 수정 ========");
		System.out.println("======== 3. 회원 탈퇴 ========");
		System.out.println("======== 4. 뒤로 가기 ========");
		System.out.println("원하는 작업을 선택하세요");
	}
	
	public void run() {
		boolean a = false;

		while (!a) {
			mypageTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				myList();
				break;
			case 2:
				membershipUpdate();
				break;
			case 3:
				userDelete();
				break;
			case 4:
				a = true;
				System.out.println("메인으로");
				break;
			}
		}
	}

	

	private void userDelete() { //회원탈퇴
		MainMenu main = new MainMenu();
		String id = main.loginId;
		MembershipVO vo = new MembershipVO();
		vo.setUserId(id);
		System.out.println("정말 탈퇴하겠습니까?");
		System.out.println("1.삭제 2.취소");
		
		int del = sc.nextInt();sc.nextLine();
		if(del == 1) {
			dao.membershipDelete(vo);
			System.out.println("plane plan을 탈퇴 하였습니다.");
		} if(del == 2) {
			System.out.println("취소하였습니다.");
		}
		main.run();
	}

	private void membershipUpdate() { //내정보 수정
		MainMenu main = new MainMenu();
		String id = main.loginId;
		MembershipVO vo = new MembershipVO();
		System.out.println("======== 내 정보 수정 ========");
//		System.out.println("비밀번호 재확인");
//		vo.setUserPassword(sc.nextLine());
//		vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));
		vo.setUserId(id);
		modifyTitle();
		int modifyKey = sc.nextInt(); sc.nextLine();
		switch(modifyKey) {
		case 1:
			System.out.println("새 비밀번호를 입력하세요");
			String newPassword = sc.nextLine();
			System.out.println("새 비밀번호 확인");
			String newPasswordcheck = sc.nextLine();
			if(newPassword.equals(newPasswordcheck)) {
				vo.setUserPassword(newPassword);
				vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));
				System.out.println("비밀번호 가 일치합니다");
				dao.membershipPassUpdate(vo);
//				System.out.println("비밀번호 변경 완료");
//			} else {
//				System.out.println("비밀번호가 다릅니다.");
			}	
			break;	
		case 2:
			System.out.println("새 휴대폰번로를 입력하세요");
			vo.setUserTel(sc.nextLine());
			int a = dao.membershipTelUpdate(vo);
			if (a != 0) {
				dao.membershipTelUpdate(vo);
				System.out.println("휴대폰 번호 변경 완료");
			} else {
				System.out.println("휴대폰 번호 변경 실패");
			}
			break;
		case 3:
			System.out.println("새 메일주소를 입력하세요");
			vo.setUserMail(sc.nextLine());
			int o = dao.membershipMailUpdate(vo);
			if (o != 0) {
				dao.membershipMailUpdate(vo);
				System.out.println("메일 주소 변경 완료");
			} else {
				System.out.println("메일 주소 변경 실패");
			}
			break;
		default :
			System.out.println("잘못 입력하였습니다");
			break;
		}
	}
	
	private void modifyTitle() {
		System.out.println("======== 수정 항목 선택 ========");
		System.out.println("======== 1. 비밀번호 ========");
		System.out.println("======== 2. 휴대폰번호 ========");
		System.out.println("======== 3. 이메일 ========");
		
	}

	private void myList() { //내정보 목록
		MainMenu main = new MainMenu();
		String id = main.loginId;
		MembershipVO vo = new MembershipVO();
		System.out.println("========= 나의 정보 =========");
		vo.setUserId(id);
		vo = dao.membershipSelectList(vo);
		
		if (vo != null) {
			System.out.println(vo.membershipInfo());
		} else {
			System.out.println("알수없는 오류");
		}
	}
}
