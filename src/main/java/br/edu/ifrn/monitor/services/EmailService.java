package br.edu.ifrn.monitor.services;

import org.springframework.mail.SimpleMailMessage;

import br.edu.ifrn.monitor.model.Usuario;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
