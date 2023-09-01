package co.yeadam.project;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.yeadam.project.common.SHA256;
import co.yeadam.project.membership.service.MembershipService;
import co.yeadam.project.membership.service.MembershipVO;
import co.yeadam.project.membership.serviceImpl.MembershipServiceImpl;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private MembershipService dao = new MembershipServiceImpl();
	private SHA256 sha256 = new SHA256();
	private ReservationMenu rm = new ReservationMenu(); // 예약 및 관리 메뉴
	private Mypage mp = new Mypage(); // 예약 및 관리 메뉴
	static String loginId;
	final String host = "smtp.naver.com";
	final String WebUser = "wnansdl12@naver.com"; // 발신자 이메일 아이디
	final String WebPassword = "wnfansml159";

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
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				membershiplogin();
				break;
			case 2:
				membershipinsert();
				break;
			case 3:
				membershipidfind();
				break;
			case 4:
				membershippassfind();
				break;
			case 5:
				a = true;
				System.out.println("프로그램 종료");
				break;
			}
		}
		a = true;
	}
	
	private void membershippassfind() { //비밀번호 찾기
		MembershipVO vo = new MembershipVO();
		System.out.println("======== 4. 비밀번호 찾기 ========");	
		System.out.println("비밀번호를 찾을 아이디를 입력하시오: ");
		vo.setUserId(sc.nextLine());
		System.out.println("가입 시 입력한 이메일을 입력하시오: ");
		String logmail = sc.nextLine();
		vo.setUserMail(logmail);
		System.out.println("비밀번호 분실 질문에 답변하시오");
		System.out.println("출신 초등학교는? 혹은 어렸을 때 별명은?");
		vo.setUserKey(sc.nextLine());
		
		System.out.println("임시 비밀번호를 메일로 보냅니다.");
		String newPass = random().toString();

		vo.setUserPassword(newPass);
		vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));
		
		String to = logmail;
		String Subject = "임시 비밀번호 메일입니다";
		String content = newPass;
		
		int n = dao.membershipFindPass(vo);
		if(n != 0) {
			dao.membershipFindPass(vo);
			sendMail(to,Subject,content);
			System.out.println("메일 전송 완료");
		} else {
			System.out.println("메일 전송 실패, 다시 입력하세요");
		}
	}

	private void membershipidfind() { //id 찾기
		MembershipVO vo = new MembershipVO();
		System.out.println("======== 3. 아이디 찾기 ========");
		System.out.println("가입 시 입력한 휴대폰 번호와 이메일 정보를 입력하시오");
		System.out.println("휴대폰 번호: ");
		vo.setUserTel(sc.nextLine());
		System.out.println("이메일: ");
		vo.setUserMail(sc.nextLine());
		
		vo = dao.membershipIdSelect(vo);
		
		if (vo != null) {
			System.out.println(vo.showId());
		} else {
			System.out.println("회원 정보가 존재하지 않습니다.");
		}
	}

	private StringBuffer random() { // 랜덤 문자열 생성 메소드
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int randomIndex = rnd.nextInt(3);
			switch (randomIndex) {
			case 0: // a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1: // A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2: // 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		return temp;
	}

	
	private Session setting() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		// Property에 SMTP 서버 정보를 설정

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(WebUser, WebPassword);
			}
		}); // SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스를 생성
		return session;
	}
	
	private void sendMail(String to, String subject, String content) {
		MimeMessage message = new MimeMessage(setting());
		
		try {
			message.setFrom(new InternetAddress(WebUser));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 수신자 메일 주소

			// 메일 제목
			message.setSubject(to);

			// 메일 내용
			message.setText(content);

			// 메세지 보내기
			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void membershipinsert() { //회원가입
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
		System.out.println("출신 초등학교는? 혹은 어렸을 때 별명은?");
		System.out.println("(비밀번호 분실 시 찾기 위한 질문입니다)");
		vo.setUserKey(sc.nextLine());

		String x = sha256.encrypt(vo.getUserPassword());
		vo.setUserPassword(x);

		int n = dao.membershipInsert(vo);
		if (n != 0) {
			System.out.println("등록 완료");
		} else {
			System.out.println("등록 실패");
		}
	}

	private boolean membershiplogin() { //로그인
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
			int menu = sc.nextInt();
			sc.nextLine();

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
