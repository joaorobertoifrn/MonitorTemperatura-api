package br.edu.ifrn.monitor.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ifrn.monitor.model.Usuario;
import br.edu.ifrn.monitor.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		email = obj.getEmail();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
