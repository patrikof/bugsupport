package br.bug.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.GrupoCliente;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorGrupoCliente;

@Component
@Scope("request")
public class GrupoClienteBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private GerenciadorGrupoCliente gerenciador;
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
			return "/views/grupocliente/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem Técnica: "+e.getMessage());
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
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem Técnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		//grupoCliente = gerenciador.buscar(grupoCliente.getId());
		grupoCliente = gerenciador.buscar(id);
		return "/views/grupocliente/form";
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
