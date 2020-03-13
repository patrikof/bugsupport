package br.bug.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import br.bug.bean.security.CustomUserDetailsService;
import br.bug.jsf.FacesUtil;

@Component
@Scope("session")
public class BuscaSolicitacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;
//	@Autowired
//	private GerenciadorSolicitacao gerenciador;
	private int id;
	private Date dataCadastroInicial;
	private Date dataCadastroFinal;
	private Date dataAtualizacaoInicial;
	private Date dataAtualizacaoFinal;
	private String pessoaContato;
	private String assunto;
	private int cliente;
	private int prioridade;
	private int status;
	private int tipoSolicitacao;
	private int sistemaOperacional;
	private int produto;
	private int versao;
	private int atendente;
	private int responsavel;
	// private List<Solicitacao> solicitacoes;
	//private LazyDataModel<Solicitacao> lazySolicitacoesDataModel;

	// private List<Solicitacao> solicitacoes;

	public BuscaSolicitacaoBean() {
		limparDados();
	}

	public void limparDados() {
		id = 0;
		dataCadastroInicial = null;
		dataCadastroFinal = null;
		dataAtualizacaoInicial = null;
		dataAtualizacaoFinal = null;
		pessoaContato = null;
		assunto = null;
		cliente = 0;
		prioridade = 0;
		status = 0;
		tipoSolicitacao = 0;
		sistemaOperacional = 0;
		produto = 0;
		versao = 0;
		atendente = 0;
		responsavel = 0;

	/*	if (lazySolicitacoesDataModel != null) {
			lazySolicitacoesDataModel.setWrappedData(null);
			lazySolicitacoesDataModel.setRowIndex(-1);
			lazySolicitacoesDataModel.setRowCount(0);
			lazySolicitacoesDataModel.setPageSize(10);
			lazySolicitacoesDataModel = null;
		}

		// solicitacoes = null;
		// lazySolicitacoesDataModel = null;
		// FacesUtil.redirect("/views/solicitacao/lista.jsf");
*/	}

	/*public LazyDataModel<Solicitacao> getLazySolicitacoesDataModel() 
	{		
		if (lazySolicitacoesDataModel == null) 
		{
			lazySolicitacoesDataModel = new LazyDataModel<Solicitacao>() 
			{
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				public Solicitacao getRowData(int rowKey) {
					return gerenciador.buscar(rowKey);					 
				}

				@SuppressWarnings("unused")				
				public Object getRowKey(Solicitacao solicitacao) {
					return solicitacao.getId();
				}
                @Override
				public void setRowIndex(int rowIndex) {
                	
                	try
                	{
                		
                		if  (getPageSize() <= 0)
                		{
                			setPageSize(10);
                		}
                		

                		if ((rowIndex == -1) || (getPageSize() == 0)) {
                			super.setRowIndex(-1);
                		} else
                			super.setRowIndex(rowIndex % getPageSize());
                	}
                	catch (Exception e) {
                		
                	String erro=e.getMessage()+">>PageSize:"+getPageSize()+"<<"
                			+">>RowIndex:"+getRowIndex()+"<<"
                			+">>RowCount:"+getRowCount()+"<<";
                              	
                	System.out.println(erro);
                		

                	}
				}
                
				@Override
				public List<Solicitacao> load(int first, int pageSize,String string, boolean bln, Map<String, String> map) {
                	Integer[] dataSizeA = {0};
                	System.out.println("LOAD Inicio 1 - RowIndex"+this.getRowIndex()+", PageSize = "+this.getPageSize()+
                			", RowCount="+this.getRowCount());                	
                	System.out.println("LOAD Inicio 2 - First = "+first+" PageSize="+pageSize);
            
					this.setPageSize(pageSize);
					//this.setRowIndex(first);
					
					//List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
					
					List<Solicitacao> solicitacoes = gerenciador.pesquisar(dataCadastroInicial,
							dataCadastroFinal, dataAtualizacaoInicial,
							dataAtualizacaoFinal, pessoaContato, assunto, id,
							cliente, prioridade, status, tipoSolicitacao,
							sistemaOperacional, produto, versao, atendente,
							responsavel, first, pageSize, dataSizeA);
					
					Integer dataSize = dataSizeA[0];
					
					//rowCount  
					this.setRowCount(dataSize);
													
					
					System.out.println("LOAD Fim 1 - RowIndex"+this.getRowIndex()+", PageSize = "+this.getPageSize()+
                			", RowCount="+this.getRowCount());                	
                	System.out.println("LOAD Fim 2 - First = "+first+" PageSize="+pageSize);
					
					//this.setWrappedData(solicitacoes);
					//retorno
					return solicitacoes;
					
				}
			};
		}
		//lazySolicitacoesDataModel.setPageSize(10);
		
		return lazySolicitacoesDataModel;

	}

	public String pesquisar() {

		try {

			if (lazySolicitacoesDataModel != null) {
				lazySolicitacoesDataModel.setWrappedData(null);
				lazySolicitacoesDataModel.setRowIndex(-1);
				lazySolicitacoesDataModel.setRowCount(10);
				lazySolicitacoesDataModel.setPageSize(10);
			}
			// se quiser evoluir no datamodel:
			// http://hrycan.com/2012/03/28/primefaces-lazy-loading-datatable-for-jsf2/

			return FacesUtil.redirect("/views/solicitacao/lista.jsf");

		} catch (Exception e) {
			FacesUtil
					.addError("Ocorreu um erro ao tentar realizar a pesquisa. Mensagem Técnica: "
							+ e.getMessage());
			return null;
		}
	}

	public String infoJvm() {
		String Msg = "###TimeZone Atual :" + TimeZone.getDefault()
				+ " ###Locale Atual :" + Locale.getDefault();
		FacesUtil.addInfo(Msg);
		return null;// FacesUtil.redirect("/views/solicitacao/lista.jsf");
	}

	@SuppressWarnings("unchecked")
	public void gerarPdf() throws JRException, IOException {

		
		gerenciador.gerarPdf((List<Solicitacao>) lazySolicitacoesDataModel
				.getWrappedData());

	}
*/
	public String pesquisar() {		
		return FacesUtil.redirect("/BugSupport/SolicitacaoGsonServlet");
	}
	
	public String listar() {
		limparDados();
		return FacesUtil.redirect("/views/solicitacao/lista.jsf");
	}
	
	public String listarEmAtendimento() {
		limparDados();
		
		int usuarioLogado = ((CustomUserDetailsService) ContextLoader
				.getCurrentWebApplicationContext().getBean(
						"customUserDetailsService")).getUsuarioLogado().getId();
		
		setResponsavel(usuarioLogado);
		setStatus(4);//em atendimento
		
		return FacesUtil.redirect("/views/solicitacao/lista.jsf");
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCadastroInicial() {
		return dataCadastroInicial;
	}

	public void setDataCadastroInicial(Date dataCadastroInicial) {
		this.dataCadastroInicial = dataCadastroInicial;
	}

	public Date getDataCadastroFinal() {
		return dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(int tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public int getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(int sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public int getAtendente() {
		return atendente;
	}

	public void setAtendente(int atendente) {
		this.atendente = atendente;
	}

	public int getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(int responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDataAtualizacaoInicial() {
		return dataAtualizacaoInicial;
	}

	public void setDataAtualizacaoInicial(Date dataAtualizacaoInicial) {
		this.dataAtualizacaoInicial = dataAtualizacaoInicial;
	}

	public Date getDataAtualizacaoFinal() {
		return dataAtualizacaoFinal;
	}

	public void setDataAtualizacaoFinal(Date dataAtualizacaoFinal) {
		this.dataAtualizacaoFinal = dataAtualizacaoFinal;
	}



}
