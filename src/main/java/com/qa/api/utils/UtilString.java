package com.qa.api.utils;

public class UtilString {
	
	
	public static String getRandomEmailId() {
		String emailId = "Apiautomation"+System.currentTimeMillis()+"@open.com";
		System.out.println("Email id is ===> "+emailId);
		return emailId;
	}

}
