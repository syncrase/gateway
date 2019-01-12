package com.olympp.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Mois enumeration.
 * TODO contraindre nombre lignes
 * @author Pierre TAQUET
 */
@ApiModel(description = "Mois enumeration. TODO contraindre nombre lignes @author Pierre TAQUET")
@Entity
@Table(name = "mois")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mois implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mois")
    private String mois;

    @OneToOne(mappedBy = "mois")
    @JsonIgnore
    private Recolte recolte;

    @OneToOne(mappedBy = "mois")
    @JsonIgnore
    private Floraison floraison;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMois() {
        return mois;
    }

    public Mois mois(String mois) {
        this.mois = mois;
        return this;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public Recolte getRecolte() {
        return recolte;
    }

    public Mois recolte(Recolte recolte) {
        this.recolte = recolte;
        return this;
    }

    public void setRecolte(Recolte recolte) {
        this.recolte = recolte;
    }

    public Floraison getFloraison() {
        return floraison;
    }

    public Mois floraison(Floraison floraison) {
        this.floraison = floraison;
        return this;
    }

    public void setFloraison(Floraison floraison) {
        this.floraison = floraison;
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
        Mois mois = (Mois) o;
        if (mois.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mois.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mois{" +
            "id=" + getId() +
            ", mois='" + getMois() + "'" +
            "}";
    }
}
