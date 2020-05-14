package br.bug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.model.TipoProduto;

@Transactional
@Component
public class TipoProdutoService{
	
	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(TipoProduto a) {
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(TipoProduto a) {
		dao.remover(a);
	}

	public TipoProduto buscar(int id) {
		return dao.buscar(id, TipoProduto.class);
	}

	public List<TipoProduto> buscarTodos() {
		return dao.buscarTodos(TipoProduto.class);
	}
}