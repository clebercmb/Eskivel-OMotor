package com.test.model.dto.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.test.model.entities.user.User;

public class UserDTO implements Serializable  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotBlank (message = "Nome é campo obrigatório")
	private String nome;
	
	@Email (message = "Endereço de email inválido")
	private String email;

	@NotBlank (message = "Telefone é campo obrigatório")	
	private String telefone;

	@NotBlank (message =  "Sexo é campo obrigatório")	
	private String sexo;
	
	public UserDTO () {}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.telefone = obj.getTelefone();
		this.sexo = obj.getSexo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	
	
}