package br.edu.ifrn.monitor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifrn.monitor.dto.UsuarioDTO;
import br.edu.ifrn.monitor.dto.UsuarioNewDTO;
import br.edu.ifrn.monitor.model.Usuario;
import br.edu.ifrn.monitor.repository.UsuarioRepository;
import br.edu.ifrn.monitor.security.UserSS;
import br.edu.ifrn.monitor.services.exceptions.AuthorizationException;
import br.edu.ifrn.monitor.services.exceptions.DataIntegrityException;
import br.edu.ifrn.monitor.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository repo;

	public Usuario find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario findByEmail(String email) {

		UserSS user = UserService.authenticated();
		if (user == null || !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getEmail());
	} 

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getEmail(), pe.encode(objDto.getSenha()) );
		return usuario;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
	}

}
