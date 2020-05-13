package br.bug.bean;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.Cliente;
import br.bug.dominio.Produto;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorCliente;
import net.sf.jasperreports.engine.JRException;

@Component
@Scope("request")
public class ClienteBean{

	
	
	@Autowired
	private GerenciadorCliente gerenciador;
	private Cliente cliente;

	public ClienteBean() {
		limpaDados();
	}

	private void limpaDados() {
		cliente = new Cliente();
	}

	public void gerarPdf() throws JRException, IOException {

		gerenciador.gerarPdf(getListagem());
	}

	public void carregar() {
		cliente = gerenciador.buscar(cliente.getId());
	}

	public String salvar() {
		try {
			
			Collection<Produto> produtos;
			produtos = gerenciador.buscarProdutosCliente(cliente.getId());			
			cliente.setProdutos(produtos);
			
			gerenciador.salvar(cliente);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/cliente/form";
		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "
							+ e.getMessage());
			return null;
		}
	}

	public String remover(int id) {
		try {
			cliente = gerenciador.buscar(id);
			gerenciador.remover(cliente);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "
							+ e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		cliente = gerenciador.buscar(id);
		
		/*Collection<Produto> produtos;
		produtos = gerenciador.buscarProdutosCliente(cliente.getId());			
		cliente.setProdutos(produtos);*/
		
		return "/views/cliente/form";
	}

	public String adicionarProduto() {
		return FacesUtil.redirect("/views/clienteproduto/form");
	}

	public List<Cliente> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
