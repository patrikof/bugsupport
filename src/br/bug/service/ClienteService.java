package br.bug.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.bug.dao.GenericDAO;
import br.bug.jsf.FacesUtil;
import br.bug.model.Cliente;
import br.bug.model.Produto;
import br.bug.report.RelatorioJasper;
import net.sf.jasperreports.engine.JRException;

@Transactional
@Component
public class ClienteService {

	@Autowired
	private GenericDAO dao;

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void salvar(Cliente a) {
		if (a.getDataCadastro() == null) {
			a.setDataCadastro(new Date());
		}
		dao.salvar(a);
	}

	@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	public void remover(Cliente a) {
		dao.remover(a);
	}

	public Cliente buscar(int id) {
		return dao.buscar(id, Cliente.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		String QueryAux = "select c from Cliente c";
		QueryAux = QueryAux + " order by c.nomeFantasia";
		ArrayList<Cliente> lista = (ArrayList<Cliente>) dao.getHt().find(QueryAux);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Produto> buscarProdutosCliente(int idCliente) {
		String QueryAux = "select p from Produto p join p.clientes c where c.id =?";
		QueryAux = QueryAux + " order by p.descricao, p.id";
		ArrayList<Produto> lista = (ArrayList<Produto>) dao.getHt().find(QueryAux, idCliente);
		return lista;
	}

	public void gerarPdf(List<Cliente> clientes) throws JRException, IOException {
		if (clientes != null)
			RelatorioJasper.imprimir(clientes, "RelatorioClientes", null);
		else
			FacesUtil.addInfo("Não há resultado para gerar um PDF.");
	}
}