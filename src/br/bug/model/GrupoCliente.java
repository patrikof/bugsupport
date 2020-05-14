package br.bug.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
public class GrupoCliente {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GRUPO_CLIENTE")
	@SequenceGenerator(name="SEQ_GRUPO_CLIENTE", sequenceName="id_grupo_cliente_seq", allocationSize=1)
	@Column(name="id_grupo_cliente")
	private int id;
	@Column(nullable=false, length=50)
	private String descricao;
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
	@Override
	public String toString() {
		return descricao;
	}	
	
}
