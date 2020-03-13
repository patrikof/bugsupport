package br.bug.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.dominio.TipoSolicitacao;

@Component @Transactional
public class GerenciadorTipoSolicitacao{
	
	@Autowired
	private GenericDAO dao;
	 
	public void salvar(TipoSolicitacao a) {
		dao.salvar(a);
	}
	@Secured ({"ROLE_GERENTE", "ROLE_ADMIN"})  
	public void remover(TipoSolicitacao a) {
		dao.remover(a);
	}
	
	public TipoSolicitacao buscar(int id) {
		return dao.buscar(id, TipoSolicitacao.class);
	}
	
	public List<TipoSolicitacao> buscarTodos() {
		return dao.buscarTodos(TipoSolicitacao.class);
	}
}
