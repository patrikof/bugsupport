package br.bug.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.Prioridade;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorPrioridade;

@Component
@Scope("request")
public class PrioridadeBean {

	
	@Autowired
	private GerenciadorPrioridade gerenciador;
	private Prioridade prioridade;

	public PrioridadeBean() {
		limpaDados();
	}
	
	private void limpaDados() {		
		prioridade = new Prioridade();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(prioridade);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/prioridade/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String remover(int id) {
		try {
			prioridade = gerenciador.buscar(id);
			gerenciador.remover(prioridade);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		//prioridade = gerenciador.buscar(prioridade.getId());
		prioridade = gerenciador.buscar(id);
		return "/views/prioridade/form";
	}

	public List<Prioridade> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
}
