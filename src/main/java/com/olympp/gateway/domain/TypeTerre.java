package com.olympp.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * TypeTerre enumeration.
 * @author Pierre TAQUET
 */
@ApiModel(description = "TypeTerre enumeration. @author Pierre TAQUET")
@Entity
@Table(name = "type_terre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeTerre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_terre")
    private String typeTerre;

    @OneToOne(mappedBy = "typeTerre")
    @JsonIgnore
    private Plante plante;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeTerre() {
        return typeTerre;
    }

    public TypeTerre typeTerre(String typeTerre) {
        this.typeTerre = typeTerre;
        return this;
    }

    public void setTypeTerre(String typeTerre) {
        this.typeTerre = typeTerre;
    }

    public Plante getPlante() {
        return plante;
    }

    public TypeTerre plante(Plante plante) {
        this.plante = plante;
        return this;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeTerre typeTerre = (TypeTerre) o;
        if (typeTerre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeTerre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeTerre{" +
            "id=" + getId() +
            ", typeTerre='" + getTypeTerre() + "'" +
            "}";
    }
}
