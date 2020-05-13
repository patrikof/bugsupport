package br.bug.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class SistemaOperacional {

	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SISTEMA_OPERACIONAL")
	@SequenceGenerator(name="SEQ_SISTEMA_OPERACIONAL", sequenceName="id_sistema_operacional_seq", allocationSize=1)
	@Column(name="id_sistema_operacional")
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
