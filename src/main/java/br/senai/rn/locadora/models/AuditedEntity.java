package br.senai.rn.locadora.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.senai.rn.locadora.repositories.PersistableEntity;

@MappedSuperclass
public abstract class AuditedEntity implements PersistableEntity<Long>, Comparable<AuditedEntity> {

	@JsonIgnore
	@CreatedDate
	@Column(name = "data_criacao", nullable = false, updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao = new Date();
	
	@JsonIgnore
	@LastModifiedDate
	@Column(name = "data_modificacao", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;
	
	@JsonIgnore
	@Column(name = "ativo", nullable = false)
	private Boolean ativo = true;

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.dataModificacao = new Date();
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int compareTo(AuditedEntity entity) {
		return this.getId().compareTo(entity.getId());
	}
		
}
