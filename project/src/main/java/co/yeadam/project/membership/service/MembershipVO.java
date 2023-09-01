package co.yeadam.project.membership.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class MembershipVO {
	private String userId;
	private String userPassword;
	private String userName;
	private String userTel;
	private Date userJoinDate;
	private String userMail;
	private String userKey;
	
	
	public String membershipInfo() {
		SimpleDateFormat sdf  = new SimpleDateFormat("yy-MM-dd");
		String str = 
					"이름: " + userName + "\n" + //
					"아이디: " + userId + "\n" + //
					"휴대폰 번호: " + userTel + "\n" + //
					"가입 일자: " + sdf.format(userJoinDate) + "\n" + //
					"이메일: " + userMail + "\n";
		return str;
	}
	
	public String showId() {
		String str = "유저 아이디: " + userId ;
		return str;
	}
}
