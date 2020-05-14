package br.bug.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.jsf.FacesUtil;
import br.bug.model.Cliente;
import br.bug.model.Produto;
import br.bug.service.ClienteService;
import br.bug.service.ProdutoService;

@Component
@Scope("session")
public class ClienteProdutoBean{

	
	private Cliente cliente;
	private Produto produto;
	@Autowired
	private ClienteService gerenciaCliente;
	@Autowired
	private ProdutoService gerenciaProduto;
	
	private ArrayList<Produto> produtosCliente;
	
	private boolean contemProdutos;

	public ClienteProdutoBean() {
		cliente = new Cliente();
		produtosCliente = new ArrayList<Produto>();		
		produto = new Produto();
		contemProdutos = false;
	}

	public String adicionar() {
		if (produtosCliente.isEmpty() || (produtosCliente == null))
			produtosCliente = new ArrayList<Produto>();
		produto = gerenciaProduto.buscar(produto.getId());
		if (!produtosCliente.contains(produto)) {
			try {
				produtosCliente.add(produto);
				cliente.setProdutos(new ArrayList<Produto>());
				cliente.setProdutos(produtosCliente);
				gerenciaCliente.salvar(cliente);
			} catch (Exception e) {
				FacesUtil.addError("Ocorreu um erro ao tentar adicionar o produto para o cliente. Mensagem T�cnica: "+e.getMessage());
				return null;
			}
		} else {
			FacesUtil.addInfo("O produto j� pertence ao cliente!");
		}
		return null;
	}

	public String remover(int id) {
		try {	
			produto = gerenciaProduto.buscar(id);
			produtosCliente.remove(produto);
			cliente.setProdutos(produtosCliente);
			gerenciaCliente.salvar(cliente);
			FacesUtil.addInfo("Produto removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o produto escolhido Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	// Lista os produtos do Cliente	
	public List<Produto> getListagem() {
		produtosCliente = gerenciaCliente.buscarProdutosCliente(cliente.getId()); 
		
		 
	   contemProdutos = (produtosCliente.size()>0);
		
		return produtosCliente;		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosCliente() {
		return produtosCliente;
	}

	public void setProdutosCliente(ArrayList<Produto> produtosCliente) {
		this.produtosCliente = produtosCliente;
	}

	public boolean getContemProdutos() {
		return contemProdutos;
	}

	public void setContemProdutos(boolean contemProdutos) {
		this.contemProdutos = contemProdutos;
	}	

}
