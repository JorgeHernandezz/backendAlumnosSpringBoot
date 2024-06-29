package com.ipn.mx.service;

public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);
}

