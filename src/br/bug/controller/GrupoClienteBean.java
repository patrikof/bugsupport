package br.bug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.jsf.FacesUtil;
import br.bug.model.GrupoCliente;
import br.bug.service.GrupoClienteService;

@Component
@Scope("request")
public class GrupoClienteBean {

	
	@Autowired
	private GrupoClienteService gerenciador;
	private GrupoCliente grupoCliente;

	public GrupoClienteBean() {
		limpaDados();
	}
	
	private void limpaDados() {		
		grupoCliente = new GrupoCliente();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(grupoCliente);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/view/grupocliente/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}
	

	public String remover(int id) {
		try {
			grupoCliente = gerenciador.buscar(id);
			gerenciador.remover(grupoCliente);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		//grupoCliente = gerenciador.buscar(grupoCliente.getId());
		grupoCliente = gerenciador.buscar(id);
		return "/view/grupocliente/form";
	}

	public List<GrupoCliente> getListagem() {
		return gerenciador.buscarTodos();
	}

	public GrupoCliente getGrupoCliente() {
		return grupoCliente;
	}

	public void setGrupoCliente(GrupoCliente grupoCliente) {
		this.grupoCliente = grupoCliente;
	}

}
