package co.yeadam.hello1;

import java.util.Scanner;

import co.yeadam.hello1.common.SHA256;
import co.yeadam.hello1.member.menu.MemberMenu;
import co.yeadam.hello1.member.service.MemberService;
import co.yeadam.hello1.member.service.MemberVO;
import co.yeadam.hello1.member.serviceImpl.MemberServiceImpl;
import co.yeadam.hello1.menu.ProductManager;

public class MainMenu {
	private MemberService dao = new MemberServiceImpl();
	private ProductManager pm = new ProductManager(); // 제품관리 메뉴
	private MemberMenu mm = new MemberMenu(); // 멤버관리 메뉴
	private Scanner sc = new Scanner(System.in);

	private void title() {
		System.out.println("===========================");
		System.out.println("=====My Home Shopping=====");
		System.out.println("===========================");
		System.out.println("======= 1. 멤 버 관 리 =======");
		System.out.println("======= 2. 상 품 관 리 =======");
		System.out.println("======= 3. 종      료 =======");
		System.out.println("원하는 작업을 선택하시오");
	}

	public void run() {
		boolean b = false;
		if (memberLogin()) {
			do {
				title();
				int key = sc.nextInt();
				sc.nextLine();
				switch (key) {
				case 1:
					mm.run();
					break;
				case 2:
					pm.run();
					break;
				case 3:
					b = true;
					sc.close();
					System.out.println("잘 가세요...");
					break;
				}
			} while (!b);
		}
	}

	private boolean memberLogin() {
		SHA256 sha256 = new SHA256();
		MemberVO vo = new MemberVO();
		boolean b = false;

		System.out.println("====== 로그인 ======");
		System.out.println("회원아이디(이메일)을 입력하세요");
		vo.setMemberId(sc.nextLine());
		System.out.println("패스워드를 입력하세요");
		vo.setMemberPassword(sc.nextLine());
		vo.setMemberPassword(sha256.encrypt(vo.getMemberPassword()));

		vo = dao.memberSelect(vo);
		// 처리
		if (vo != null) {
			System.out.println(vo.getMemberName() + "님 환영합니다.");
			b = true;
		} else {
			System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
			System.out.println("시스템을 재시작 하세요");
		}
		return b;
	}
}
