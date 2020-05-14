package br.bug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.model.Prioridade;

@Transactional
@Component
public class PrioridadeService{

	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(Prioridade a) {
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(Prioridade a) {
		dao.remover(a);
	}

	public Prioridade buscar(int id) {
		return dao.buscar(id, Prioridade.class);
	}

	public List<Prioridade> buscarTodos() {
		return dao.buscarTodos(Prioridade.class);
	}
}