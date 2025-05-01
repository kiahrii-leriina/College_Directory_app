package org.dbc.cda.util;

public class OtpGenerator {

	public static void main(String[] args) {
		System.out.println(getOtp());
	}
	public static int getOtp() {
		
		int otp = (int) Math.abs(Math.random()*10000);
		if(otp<999 || otp == 10000) {
			 otp = (int) Math.abs(Math.random()*10000);	
		}
		
		return otp;
	}
}
