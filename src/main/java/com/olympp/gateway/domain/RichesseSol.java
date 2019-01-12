package com.olympp.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * RichesseSol enumeration.
 * @author Pierre TAQUET
 */
@ApiModel(description = "RichesseSol enumeration. @author Pierre TAQUET")
@Entity
@Table(name = "richesse_sol")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RichesseSol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "richesse_sol")
    private String richesseSol;

    @OneToOne(mappedBy = "richesseSol")
    @JsonIgnore
    private Plante plante;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRichesseSol() {
        return richesseSol;
    }

    public RichesseSol richesseSol(String richesseSol) {
        this.richesseSol = richesseSol;
        return this;
    }

    public void setRichesseSol(String richesseSol) {
        this.richesseSol = richesseSol;
    }

    public Plante getPlante() {
        return plante;
    }

    public RichesseSol plante(Plante plante) {
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
        RichesseSol richesseSol = (RichesseSol) o;
        if (richesseSol.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), richesseSol.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RichesseSol{" +
            "id=" + getId() +
            ", richesseSol='" + getRichesseSol() + "'" +
            "}";
    }
}
