package br.bug.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.dominio.SistemaOperacional;

@Component @Transactional
public class GerenciadorSistemaOperacional{
	
	@Autowired
	private GenericDAO dao;
	@Secured ({"ROLE_GERENTE", "ROLE_ADMIN"})  
	public void salvar(SistemaOperacional a) {
		dao.salvar(a);
	}
	@Secured ({"ROLE_GERENTE", "ROLE_ADMIN"})  
	public void remover(SistemaOperacional a) {
		dao.remover(a);
	}
	//@Secured ({"ROLE_PROGRAMADOR", "ROLE_GERENTE", "ROLE_ADMIN", "ROLE_SUPORTE"}) 
	public SistemaOperacional buscar(int id) {
		return dao.buscar(id, SistemaOperacional.class);
	}
	//@Secured ({"ROLE_PROGRAMADOR", "ROLE_GERENTE", "ROLE_ADMIN", "ROLE_SUPORTE"}) 
	public List<SistemaOperacional> buscarTodos() {
		return dao.buscarTodos(SistemaOperacional.class);
	}

}
