package br.bug.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Prioridade {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRIORIDADE")
	@SequenceGenerator(name="SEQ_PRIORIDADE", sequenceName="id_prioridade_seq", allocationSize=1)
    @Column(name="id_prioridade")
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
