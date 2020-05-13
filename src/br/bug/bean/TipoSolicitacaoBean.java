package br.bug.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.TipoSolicitacao;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorTipoSolicitacao;

@Component
@Scope("request")
public class TipoSolicitacaoBean {

	
	@Autowired
	private GerenciadorTipoSolicitacao gerenciador;
	private TipoSolicitacao tipoSolicitacao;

	public TipoSolicitacaoBean() {
		limpaDados();
	}
	
	private void limpaDados() {
		tipoSolicitacao = new TipoSolicitacao();		
	}

	public String salvar() {
		try {
			gerenciador.salvar(tipoSolicitacao);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/tiposolicitacao/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String remover(int id) {
		try {
			tipoSolicitacao = gerenciador.buscar(id); 
			gerenciador.remover(tipoSolicitacao);
			FacesUtil.addInfo("Dado removido com sucesso!");
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}

	public String alterar(int id) {
		tipoSolicitacao = gerenciador.buscar(id);
		//tipoSolicitacao = gerenciador.buscar(tipoSolicitacao.getId());
		return "/views/tiposolicitacao/form";
	}

	public List<TipoSolicitacao> getListagem() {
		return gerenciador.buscarTodos();
	}

	public TipoSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
}
