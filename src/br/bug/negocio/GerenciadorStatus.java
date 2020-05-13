package br.bug.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.dominio.Status;

@Transactional
@Component
public class GerenciadorStatus{

	
	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(Status a) {
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(Status a) {
		dao.remover(a);
	}

	public Status buscar(int id) {
		return dao.buscar(id, Status.class);
	}

	public List<Status> buscarTodos() {
		return dao.buscarTodos(Status.class);
	}
}
