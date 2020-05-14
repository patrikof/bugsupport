package br.bug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.model.GrupoCliente;

@Transactional
@Component
public class GrupoClienteService{

	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(GrupoCliente a) {
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(GrupoCliente a) {
		dao.remover(a);
	}

	public GrupoCliente buscar(int id) {
		return dao.buscar(id, GrupoCliente.class);
	}

	public List<GrupoCliente> buscarTodos() {
		return dao.buscarTodos(GrupoCliente.class);
	}
}