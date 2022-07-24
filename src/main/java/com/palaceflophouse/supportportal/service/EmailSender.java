package com.palaceflophouse.supportportal.service;
/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
public interface EmailSender {
	void send(String to, String details);
}
