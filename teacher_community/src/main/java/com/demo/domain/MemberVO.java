package com.demo.domain;

import java.time.LocalDateTime;

public class MemberVO {

	private String id;
	private String email;
	private String pw;
	private String name;
	private String school_name; // 학교 이름
	private String role; // 역할(TEACHER, ADMIN, GUEST)
	private boolean isEmailVerified; // 이메일 인증 여부
	private boolean isSecondaryVerified; // 1년 마다의 인증 여부
	private LocalDateTime lastVerifiedAt; // 가장 최근 인증된 시각
	private LocalDateTime nextVerifyDue; // lastVerifiedAt + 1년(그 안에 다시 인증해야 로그인 가능하게)
	private LocalDateTime create_at; // 가입 시간
	private LocalDateTime updated_at; // 수정 시간

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", email=" + email + ", pw=" + pw + ", name=" + name + ", school_name="
				+ school_name + ", role=" + role + ", isEmailVerified=" + isEmailVerified + ", isSecondaryVerified=" + isSecondaryVerified
				+ ", lastVerifiedAt=" + lastVerifiedAt + ", nextVerifyDue=" + nextVerifyDue + ", create_at=" + create_at
				+ ", updated_at=" + updated_at + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public boolean isSecondaryVerified() {
		return isSecondaryVerified;
	}

	public void setSecondaryVerified(boolean isSecondaryVerified) {
		this.isSecondaryVerified = isSecondaryVerified;
	}

	public LocalDateTime getLastVerifiedAt() {
		return lastVerifiedAt;
	}

	public void setLastVerifiedAt(LocalDateTime lastVerifiedAt) {
		this.lastVerifiedAt = lastVerifiedAt;
	}

	public LocalDateTime getNextVerifyDue() {
		return nextVerifyDue;
	}

	public void setNextVerifyDue(LocalDateTime nextVerifyDue) {
		this.nextVerifyDue = nextVerifyDue;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
