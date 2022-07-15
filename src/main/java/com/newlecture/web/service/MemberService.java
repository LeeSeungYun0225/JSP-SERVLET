package com.newlecture.web.service;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.constants.Dbconstants;
import com.newlecture.web.entity.Member;

public class MemberService // Login / Sign Dao
{
	
	private Member member;
	
	
	public MemberService() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean memberJoin(Member member)
	{
		int result = 0;
		LocalDate date = LocalDate.now();// 현재 날짜 구하기 
		String today = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//포멧 형식 지정 
		// LocalTime = 현재 시간 
		// LocalDateTime = 현재 날짜 & 시간 

		String sql = "INSERT INTO member(ID,PWD,NAME,GENDER,BIRTHDAY,PHONE,EMAIL,REGDATE) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 String url  = Dbconstants.Db_address;
			 String adminId = Dbconstants.Db_id;
			 String adminPass = Dbconstants.Db_pass;
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, member.getId());
			 statement.setString(2, member.getPassword());
			 statement.setString(3, member.getName());
			 statement.setString(4, member.getGender());
			 statement.setString(5, member.getBirthday());
			 statement.setString(6, member.getPhone());
			 statement.setString(7, member.getEmail());
			 statement.setString(8, today);
			 result = statement.executeUpdate();
			 
		
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result==1?true:false;
	}
	

	
	public boolean memberLogin(Member member) // 로그인 서비스 
	{
		int result=0;
		ResultSet rs = null;
		String sql = "SELECT ID,NAME,GENDER,BIRTHDAY,PHONE,EMAIL FROM member WHERE ID=? AND PWD = ?";
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 String url  = Dbconstants.Db_address;
			 String adminId = Dbconstants.Db_id;
			 String adminPass = Dbconstants.Db_pass;
			 Connection con = DriverManager.getConnection(url,adminId,adminPass); 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, member.getId());
			 statement.setString(2, member.getPassword());
			 rs = statement.executeQuery();
			 
			 while(rs.next())
			 {
				 member.setName(rs.getString("Name"));
				 member.setGender(rs.getString("GENDER"));
				 member.setBirthday(rs.getString("BIRTHDAY"));
				 member.setPhone(rs.getString("PHONE"));
				 member.setEmail(rs.getString("EMAIL"));
				 result=1;
			 }
		
			 con.close();
			 statement.close();
			 
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return result==1?true:false;
	}
	
}