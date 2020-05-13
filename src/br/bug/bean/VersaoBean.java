package br.bug.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.bug.dominio.Versao;
import br.bug.jsf.FacesUtil;
import br.bug.negocio.GerenciadorVersao;

@Component
@Scope("request")
public class VersaoBean {

	//https://datatables.net/reference/api/row().data()
	
	
	@Autowired
	private GerenciadorVersao gerenciador;
	private Versao versao;

	/*private ListDataModel<Versao> dataModel;*/
	
		
	//http://cagataycivici.wordpress.com/2006/07/10/jsf_datatable_with_custom_paging/
	// https://java.net/projects/mgjug/lists/users/archive/2007-09/message/148
	
	
	// ver: http://www.codeproject.com/Articles/359750/jQuery-DataTables-in-Java-Web-Applications
	//e exemplo de fontes baixado JQueryDataTablesSource.zip
	
	
	// Dica de pagina��o sem depend�ncias : http://marcusmazzo.wordpress.com/category/paginacao/
	
	public VersaoBean() {
		limpaDados();//versao = new Versao();
	}
	
	
	public void carregar() {
		if (versao.getId()>0)
			versao = gerenciador.buscar(versao.getId());		
	}
	
	public void limpaDados() {
		versao = new Versao();
	}
	
	
	 /* // Este m�todo deve ser chamado na 
	   // p�gina de listagem ...
	   public String montarLista() {
	      List<Versao> lista = gerenciador.buscarTodos();
	      dataModel = new ListDataModel();
	      dataModel.setWrappedData(lista); 
	      return "listagemRegistros";
	   }*/
	

	public String salvar() {
		try {
			gerenciador.salvar(versao);
			FacesUtil.addInfo("Dados salvos com sucesso!");
			limpaDados();
			return "/views/versao/form";
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar salvar o cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}
	
	/*@RequestMapping(value="/views/versao/removerVersao/id")
	public String remover(@PathVariable String id) {
		try {
			
			versao = gerenciador.buscar( Integer.parseInt(id) );
			gerenciador.remover(versao);
			FacesUtil.addInfo("Dado removido com sucesso!");
			
			return null;
		} catch (Exception e) {
			FacesUtil.addError("Ocorreu um erro ao tentar remover o dado escolhido, possivelmente por estar sendo utilizado em outro cadastro. Mensagem T�cnica: "+e.getMessage());
			return null;
		}
	}*/

	public String alterar(int id) {
		versao = gerenciador.buscar(id);
		//versao = gerenciador.buscar(versao.getId());
		return "/views/versao/form";
	}

	public List<Versao> getListagem() {
		
	/*	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();  
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
		
        String tamPagina = (String) request.getAttribute("tamPagina");
        String paginaCorrente =  (String) request.getAttribute("paginaCorrente");*/
        
		
		return gerenciador.buscarTodos();
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

/*	public ListDataModel<Versao> getDataModel() {		
		return dataModel;
	}

	public void setDataModel(ListDataModel<Versao> dataModel) {
		this.dataModel = dataModel;
	}
	*/
	
}