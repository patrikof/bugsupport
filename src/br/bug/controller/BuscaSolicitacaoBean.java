package br.bug.controller;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import br.bug.bean.security.CustomUserDetailsService;
import br.bug.jsf.FacesUtil;

@Component
@Scope("session")
public class BuscaSolicitacaoBean{
	
	private static final int ATENDIMENTO = 4;
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
	}

	public String pesquisar() {		
		return FacesUtil.redirect("/SolicitacaoGsonServlet");
	}
	
	public String listar() {
		limparDados();
		return FacesUtil.redirect("/view/solicitacao/lista.jsf");
	}
	
	public String listarEmAtendimento() {
		limparDados();
		
		int usuarioLogado = ((CustomUserDetailsService) ContextLoader
				.getCurrentWebApplicationContext().getBean(
						"customUserDetailsService")).getUsuarioLogado().getId();
		
		setResponsavel(usuarioLogado);
		setStatus(ATENDIMENTO);
		
		return FacesUtil.redirect("/view/solicitacao/lista.jsf");
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