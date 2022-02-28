package com.NepalCode.DBConwithSpring;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHostel {
	
	public static void main(String[] args) throws SQLException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		HostelDB db = context.getBean("hostelDb", HostelDB.class);
		db.showInfo();
		
		context.close();
		
	}

}
