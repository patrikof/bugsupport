package br.bug.negocio;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.bug.dao.GenericDAO;
import br.bug.dominio.Usuario;

@Component 
public class GerenciadorUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericDAO dao;

	public void salvar(Usuario a) {
		dao.salvar(a);
	}
	public void remover(Usuario a) {
		dao.remover(a);
	}	
	public Usuario buscar(int id) {
		return dao.buscar(id, Usuario.class);
	}

	public List<Usuario> buscarTodos() {
		return dao.buscarTodos(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public Usuario buscarPorLogin(String login) {
		
		List<Usuario> usuarios = dao.getHt().find("from Usuario where login=?",
				login);	

		if (usuarios == null || usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuário '" + login
					+ "' não encontrado...");
		} else {
			return usuarios.get(0);
		}
	}	
}
