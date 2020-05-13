package br.bug.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
public class Cliente{

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "id_cliente_seq", allocationSize = 1)
	@Column(name = "id_cliente")
	private int id;
	@Column(length = 100)
	private String razaoSocial;
	private long cnpj;
	@Column(nullable = false, length = 100)
	private String nomeFantasia;
	@Column(length = 100)
	private String endereco;
	@Column(length = 50)
	private String bairro;
	@Column(length = 50)
	private String cidade;
	@Column(length = 2)
	private String uf;
	@Column(length = 50)
	private String pais;
	@Column(length = 15)
	private String telefone;
	@Column(length = 15)
	private String telefone2;
	@Column(length = 15)
	private String telefone3;
	@Column(length = 15)
	private String celular;
	@Column(length = 15)
	private String fax;
	@Column(length = 100)
	private String email;
	@Column(length = 100)
	private String site;
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@ManyToOne
	@JoinColumn(name = "id_grupo_cliente")
	private GrupoCliente grupoCliente;
	@Expose(serialize = false)
	@ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)	
	@JoinTable(name = "cliente_produto", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private Collection<Produto> produtos;
	
	public Cliente(){
		this.setGrupoCliente(new GrupoCliente());
		this.setProdutos(new ArrayList<Produto>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public GrupoCliente getGrupoCliente() {
		return grupoCliente;
	}

	public void setGrupoCliente(GrupoCliente grupoCliente) {
		this.grupoCliente = grupoCliente;
	}

	
	
	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
	

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return nomeFantasia;
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
