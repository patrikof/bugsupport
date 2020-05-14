package br.bug.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.pegdown.PegDownProcessor;

@Entity
public class Solicitacao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOLICITACAO")
	@SequenceGenerator(name = "SEQ_SOLICITACAO", sequenceName = "id_solicitacao_seq", allocationSize = 1)
	@Column(name = "id_solicitacao")
	private int id;
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;	
	@Column(name = "data_atualizacao")
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;	
	@Column(name = "hora_cadastro")
	@Temporal(TemporalType.TIME)
	private Date horaCadastro;	
	@Column(name = "hora_atualizacao")
	@Temporal(TemporalType.TIME)
	private Date horaAtualizacao;	
	@Column(nullable = false, length = 10000)
	private String problema;
	@Column(length = 100000)
	private String solucao;
	@Column(nullable = false, length = 50)
	private String pessoaContato;
	@Column(nullable = false, length = 80)
	private String assunto;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "id_prioridade")
	private Prioridade prioridade;
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;
	@ManyToOne
	@JoinColumn(name = "id_tipo_solicitacao")
	private TipoSolicitacao tipoSolicitacao;
	@ManyToOne
	@JoinColumn(name = "id_sistema_operacional")
	private SistemaOperacional sistemaOperacional;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "id_versao")
	private Versao versao;
	@ManyToOne
	@JoinColumn(name = "id_atendente")
	private Usuario atendente;
	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Usuario responsavel;
	
	public Solicitacao() {		
		this.setProduto(new Produto());
		this.setVersao(new Versao());
		this.setCliente(new Cliente());
		this.setTipoSolicitacao(new TipoSolicitacao());
		this.setSistemaOperacional(new SistemaOperacional());
		this.setStatus(new Status());
		this.setPrioridade(new Prioridade());
		this.setAtendente(new Usuario());
		this.setResponsavel(new Usuario());	
		this.setAssunto(null);
		this.setProblema(null);
		this.setSolucao(null);
		this.setId(0);
		this.setDataAtualizacao(null);
		this.setDataCadastro(null);
		this.setPessoaContato(null);
		this.setHoraAtualizacao(null);
		this.setHoraCadastro(null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getProblemaFormatado() {
		//Markdown4jProcessor mp=new Markdown4jProcessor();			
		//return mp.process(problema);
		PegDownProcessor pd=new PegDownProcessor();			
		return pd.markdownToHtml(problema);	
	}
	public String getProblema() {
		//problema = problema.replaceAll("\n", "<br />");
		return problema;		
			
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getSolucaoFormatado() {	
		PegDownProcessor pd=new PegDownProcessor();			
		return pd.markdownToHtml(solucao);
	}
	
	public String getSolucao() {
		//solucao = solucao.replaceAll("\n", "<br />");
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TipoSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public Usuario getAtendente() {
		return atendente;
	}

	public void setAtendente(Usuario atendente) {
		this.atendente = atendente;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}	

	public Date getHoraCadastro() {
		return horaCadastro;
	}

	public void setHoraCadastro(Date horaCadastro) {
		this.horaCadastro = horaCadastro;
	}

	public Date getHoraAtualizacao() {
		return horaAtualizacao;
	}

	public void setHoraAtualizacao(Date horaAtualizacao) {
		this.horaAtualizacao = horaAtualizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitacao other = (Solicitacao) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
}
