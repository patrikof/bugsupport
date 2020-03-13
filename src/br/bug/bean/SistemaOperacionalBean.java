package br.bug.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.SistemaOperacional;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorSistemaOperacional;

@Component
@Scope("request")
public class SistemaOperacionalBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private GerenciadorSistemaOperacional gerenciador;
	private SistemaOperacional sistemaOperacional;

	public SistemaOperacionalBean() {
		limpaDados();
	}
	
	private void limpaDados() {
		sistemaOperacional = new SistemaOperacional();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(sistemaOperacional);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/sistemaoperacional/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem Técnica: "+e.getMessage());
			return null;
		}
	}
	
	public String remover(int id) {
		try {
			sistemaOperacional = gerenciador.buscar(id);
			gerenciador.remover(sistemaOperacional);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem Técnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		sistemaOperacional = gerenciador.buscar(id);
		//sistemaOperacional = gerenciador.buscar(sistemaOperacional.getId());
		return "/views/sistemaoperacional/form";
	}

	public List<SistemaOperacional> getListagem() {
		return gerenciador.buscarTodos();
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}
}
