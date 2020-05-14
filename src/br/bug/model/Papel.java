package br.bug.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

import com.google.gson.annotations.Expose;

@Entity
public class Papel implements Serializable, GrantedAuthority{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAPEL")
	@SequenceGenerator(name="SEQ_PAPEL", sequenceName="id_papel_seq", allocationSize=1)
	@Column(name="id_papel")
	private int id;
	@Column(nullable=false, length=50)
	private String descricao;	
	@Expose(serialize = false)
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name="usuario_papel",
			joinColumns=@JoinColumn(name="id_papel"),
			inverseJoinColumns=@JoinColumn(name="id_usuario"))
	protected Set<Usuario> usuarios;
	
	public Papel(){
		this.setUsuarios(new HashSet<Usuario>());
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
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public String getAuthority() {		
		return this.descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Papel other = (Papel) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descricao;
	}	
}