package br.bug.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.Produto;
import br.bug.dominio.Versao;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorProduto;

@Component
@Scope("request")
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private GerenciadorProduto gerenciador;
	private Produto produto;
	private List<Versao> versoesAtivas;

	public ProdutoBean() {
		limpaDados();		
	}
	
	private void limpaDados() {
		produto = new Produto();
		versoesAtivas = new ArrayList<Versao>();		
	}

	public void carregar() {
		produto = gerenciador.buscar(produto.getId());
		versoesAtivas = gerenciador.buscarVersoesAtivas(produto.getId());
	}

	public String salvar() {
		try {
			gerenciador.salvar(produto);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/produto/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem Técnica: "+e.getMessage());
			return null;
		}
	}


	public String remover(int id) {
		try {
			produto = gerenciador.buscar(id); 
			gerenciador.remover(produto);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar"+
					" sendo utilizado em outro cadastro. Mensagem Técnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		produto = gerenciador.buscar(id);
		//produto = gerenciador.buscar(produto.getId());
		return "/views/produto/form";
	}

	public List<Produto> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Versao> getVersoesAtivas() {
		return versoesAtivas;
	}

	public void setVersoesAtivas(List<Versao> versoesAtivas) {
		this.versoesAtivas = versoesAtivas;
	}

}
