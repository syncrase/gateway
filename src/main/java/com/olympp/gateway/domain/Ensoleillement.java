package com.olympp.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Ensoleillement enumeration.
 * @author Pierre TAQUET
 */
@ApiModel(description = "Ensoleillement enumeration. @author Pierre TAQUET")
@Entity
@Table(name = "ensoleillement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ensoleillement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ensoleillement")
    private String ensoleillement;

    @OneToOne(mappedBy = "ensoleillement")
    @JsonIgnore
    private Plante plante;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnsoleillement() {
        return ensoleillement;
    }

    public Ensoleillement ensoleillement(String ensoleillement) {
        this.ensoleillement = ensoleillement;
        return this;
    }

    public void setEnsoleillement(String ensoleillement) {
        this.ensoleillement = ensoleillement;
    }

    public Plante getPlante() {
        return plante;
    }

    public Ensoleillement plante(Plante plante) {
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
        Ensoleillement ensoleillement = (Ensoleillement) o;
        if (ensoleillement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ensoleillement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ensoleillement{" +
            "id=" + getId() +
            ", ensoleillement='" + getEnsoleillement() + "'" +
            "}";
    }
}
