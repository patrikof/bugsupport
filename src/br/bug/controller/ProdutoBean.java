package br.bug.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.jsf.FacesUtil;
import br.bug.model.Produto;
import br.bug.model.Versao;
import br.bug.service.ProdutoService;

@Component
@Scope("request")
public class ProdutoBean{

	
	@Autowired
	private ProdutoService gerenciador;
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
			return "/view/produto/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
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
					" sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		produto = gerenciador.buscar(id);
		//produto = gerenciador.buscar(produto.getId());
		return "/view/produto/form";
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
