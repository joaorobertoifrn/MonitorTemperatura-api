package br.edu.ifrn.monitor.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifrn.monitor.model.Monitor;
import br.edu.ifrn.monitor.model.Usuario;
import br.edu.ifrn.monitor.repository.MonitorRepository;
import br.edu.ifrn.monitor.repository.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MonitorRepository monitorRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Usuario usuario1 = new Usuario(null, "usuario1@email.com", pe.encode("123"));
		Usuario usuario2 = new Usuario(null, "usuario2@email.com", pe.encode("123"));
		Usuario usuario3 = new Usuario(null, "usuario3@email.com", pe.encode("123"));
		Usuario usuario4 = new Usuario(null, "usuario4@email.com", pe.encode("123"));
		Usuario usuario5 = new Usuario(null, "usuario5@email.com", pe.encode("123"));
		
		
		usuarioRepository.save(Arrays.asList(usuario1,usuario2,usuario3,usuario4,usuario5));
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Monitor monitor1 = new Monitor("35","18");
		Monitor monitor2 = new Monitor("36","17");
		Monitor monitor3 = new Monitor("37","20");
		Monitor monitor4 = new Monitor("35","21");
		Monitor monitor5 = new Monitor("34","22");
		Monitor monitor6 = new Monitor("28","23");
		Monitor monitor7 = new Monitor("30","24");
		Monitor monitor8 = new Monitor("32","25");
		Monitor monitor9 = new Monitor("33","20");
		Monitor monitor10 = new Monitor("34","25");
		Monitor monitor11 = new Monitor("35","26");
		Monitor monitor12 = new Monitor("36","24");
		Monitor monitor13 = new Monitor("39","25");
		Monitor monitor14 = new Monitor("29","26");
		Monitor monitor15 = new Monitor("36","25");
		Monitor monitor16 = new Monitor("37","20");
		Monitor monitor17 = new Monitor("38","24");
		Monitor monitor18 = new Monitor("39","26");
		Monitor monitor19 = new Monitor("35","28");
		Monitor monitor20 = new Monitor("35","30");
		
		monitorRepository.save(Arrays.asList(monitor1,monitor2,monitor3,monitor4,monitor5,monitor6,monitor7,
				monitor8,monitor9,monitor10,monitor11,monitor12,monitor13,monitor14,monitor15,monitor16,monitor17,
				monitor18,monitor19,monitor20));
	}
}
