package br.bug.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.dominio.Produto;
import br.bug.dominio.Versao;

@Transactional
@Component
public class GerenciadorProduto{

	
	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(Produto a) {
		if (a.getDataCadastro() == null) {
			a.setDataCadastro(new Date());
		}
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(Produto a) {
		dao.remover(a);
	}

	public Produto buscar(int id) {
		return dao.buscar(id, Produto.class);
	}

	public List<Produto> buscarTodos() {
		return dao.buscarTodos(Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Versao> buscarVersoesAtivas(int idProduto) {

		String QueryAux = "select v from Versao v where v.ativa=true and v.produto.id =?";
		List<Versao> lista = dao.getHt().find(QueryAux, idProduto);
		return lista;
	}
}