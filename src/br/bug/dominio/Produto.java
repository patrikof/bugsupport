package br.bug.dominio;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
public class Produto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
	@SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "id_produto_seq", allocationSize = 1)
	@Column(name = "id_produto")
	private int id;
	@Column(nullable = false, length = 50)
	private String descricao;
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@ManyToOne
	@JoinColumn(name = "id_tipo_produto")
	private TipoProduto tipoProduto;
	@Expose(serialize = false)
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
	@OrderBy("descricao ASC")
	private Set<Versao> versoes;
	@Expose(serialize = false)
	@ManyToMany
	@JoinTable(name = "cliente_produto", joinColumns = @JoinColumn(name = "id_produto"),
			                      inverseJoinColumns = @JoinColumn(name = "id_cliente"))
	@OrderBy("nomeFantasia ASC")
	private Collection<Cliente> clientes;

	public Produto() {
		this.setTipoProduto(new TipoProduto());
		this.setVersoes(new TreeSet<Versao>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Set<Versao> getVersoes() {
		return versoes;
	}

	public void setVersoes(Set<Versao> versoes) {
		this.versoes = versoes;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return descricao;
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
