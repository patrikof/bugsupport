package br.bug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.jsf.FacesUtil;
import br.bug.model.Status;
import br.bug.service.StatusService;

@Component
@Scope("request")
public class StatusBean {

	
	@Autowired
	private StatusService gerenciador;
	private Status status;

	public StatusBean() {
		limpaDados();
	}
	
	private void limpaDados() {
		status = new Status();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(status);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/view/status/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String remover(int id) {
		try {
			status = gerenciador.buscar(id); 
			gerenciador.remover(status);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		status = gerenciador.buscar(id);
		//status = gerenciador.buscar(status.getId());
		return "/view/status/form";
	}

	public List<Status> getListagem() {
		return gerenciador.buscarTodos();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
