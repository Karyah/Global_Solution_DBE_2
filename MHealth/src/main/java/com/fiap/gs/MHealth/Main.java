package com.fiap.gs.MHealth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("coxinha1234"));
		System.out.println(encoder.encode("carlitos"));
		System.out.println(encoder.encode("Danielcarlitosdasilva123"));
		System.out.println(encoder.encode("0102060769d"));
		System.out.println(encoder.encode("Hmhmgmgmgmg"));
		
	}
}
