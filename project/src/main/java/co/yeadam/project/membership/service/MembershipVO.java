package co.yeadam.project.membership.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MembershipVO {
	private String userId;
	private String userPassword;
	private String userName;
	private String userTel;
	private LocalDate userJoinDate;
	private String userMail;
}
