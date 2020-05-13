package br.bug.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.TipoProduto;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorTipoProduto;

@Component
@Scope("request")
public class TipoProdutoBean {

	
	@Autowired
	private GerenciadorTipoProduto gerenciador;
	private TipoProduto tipoProduto;

	public TipoProdutoBean() {
		limpaDados();
	}
	
	private void limpaDados() {		
		tipoProduto = new TipoProduto();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(tipoProduto);			
			FacesUtil.addInfo("Dados salvos com sucesso!");	
			limpaDados();
			return "/views/tipoproduto/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String remover(int id) {
		try {
			tipoProduto = gerenciador.buscar(id);
			gerenciador.remover(tipoProduto);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		tipoProduto = gerenciador.buscar(id);
		//tipoProduto = gerenciador.buscar(tipoProduto.getId());
		return "/views/tipoproduto/form";
	}

	public List<TipoProduto> getListagem() {
		return gerenciador.buscarTodos();
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
