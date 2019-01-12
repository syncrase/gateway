package com.olympp.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Pierre TAQUET
 */
@ApiModel(description = "@author Pierre TAQUET")
@Entity
@Table(name = "plante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Plante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$")
    @Column(name = "ph_min")
    private String phMin;

    @Pattern(regexp = "^\\d{0,1}(,\\d){0,1}$")
    @Column(name = "ph_max")
    private String phMax;

    @Column(name = "temp_min")
    private Integer tempMin;

    @Column(name = "temp_max")
    private Integer tempMax;

    @OneToOne(optional = false)    @NotNull
    @JoinColumn(unique = true)
    private ClassificationCronquist classificationCronquist;

    @OneToOne    @JoinColumn(unique = true)
    private Strate strate;

    @OneToOne    @JoinColumn(unique = true)
    private VitesseCroissance vitesseCroissance;

    @OneToOne    @JoinColumn(unique = true)
    private Ensoleillement ensoleillement;

    @OneToOne    @JoinColumn(unique = true)
    private RichesseSol richesseSol;

    @OneToOne    @JoinColumn(unique = true)
    private TypeTerre typeTerre;

    @OneToOne    @JoinColumn(unique = true)
    private TypeFeuillage typeFeuillage;

    @OneToOne    @JoinColumn(unique = true)
    private TypeRacine typeRacine;

    @OneToOne(mappedBy = "plante")
    @JsonIgnore
    private Recolte recolte;

    @OneToOne(mappedBy = "plante")
    @JsonIgnore
    private Floraison floraison;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhMin() {
        return phMin;
    }

    public Plante phMin(String phMin) {
        this.phMin = phMin;
        return this;
    }

    public void setPhMin(String phMin) {
        this.phMin = phMin;
    }

    public String getPhMax() {
        return phMax;
    }

    public Plante phMax(String phMax) {
        this.phMax = phMax;
        return this;
    }

    public void setPhMax(String phMax) {
        this.phMax = phMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public Plante tempMin(Integer tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public Plante tempMax(Integer tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public ClassificationCronquist getClassificationCronquist() {
        return classificationCronquist;
    }

    public Plante classificationCronquist(ClassificationCronquist classificationCronquist) {
        this.classificationCronquist = classificationCronquist;
        return this;
    }

    public void setClassificationCronquist(ClassificationCronquist classificationCronquist) {
        this.classificationCronquist = classificationCronquist;
    }

    public Strate getStrate() {
        return strate;
    }

    public Plante strate(Strate strate) {
        this.strate = strate;
        return this;
    }

    public void setStrate(Strate strate) {
        this.strate = strate;
    }

    public VitesseCroissance getVitesseCroissance() {
        return vitesseCroissance;
    }

    public Plante vitesseCroissance(VitesseCroissance vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
        return this;
    }

    public void setVitesseCroissance(VitesseCroissance vitesseCroissance) {
        this.vitesseCroissance = vitesseCroissance;
    }

    public Ensoleillement getEnsoleillement() {
        return ensoleillement;
    }

    public Plante ensoleillement(Ensoleillement ensoleillement) {
        this.ensoleillement = ensoleillement;
        return this;
    }

    public void setEnsoleillement(Ensoleillement ensoleillement) {
        this.ensoleillement = ensoleillement;
    }

    public RichesseSol getRichesseSol() {
        return richesseSol;
    }

    public Plante richesseSol(RichesseSol richesseSol) {
        this.richesseSol = richesseSol;
        return this;
    }

    public void setRichesseSol(RichesseSol richesseSol) {
        this.richesseSol = richesseSol;
    }

    public TypeTerre getTypeTerre() {
        return typeTerre;
    }

    public Plante typeTerre(TypeTerre typeTerre) {
        this.typeTerre = typeTerre;
        return this;
    }

    public void setTypeTerre(TypeTerre typeTerre) {
        this.typeTerre = typeTerre;
    }

    public TypeFeuillage getTypeFeuillage() {
        return typeFeuillage;
    }

    public Plante typeFeuillage(TypeFeuillage typeFeuillage) {
        this.typeFeuillage = typeFeuillage;
        return this;
    }

    public void setTypeFeuillage(TypeFeuillage typeFeuillage) {
        this.typeFeuillage = typeFeuillage;
    }

    public TypeRacine getTypeRacine() {
        return typeRacine;
    }

    public Plante typeRacine(TypeRacine typeRacine) {
        this.typeRacine = typeRacine;
        return this;
    }

    public void setTypeRacine(TypeRacine typeRacine) {
        this.typeRacine = typeRacine;
    }

    public Recolte getRecolte() {
        return recolte;
    }

    public Plante recolte(Recolte recolte) {
        this.recolte = recolte;
        return this;
    }

    public void setRecolte(Recolte recolte) {
        this.recolte = recolte;
    }

    public Floraison getFloraison() {
        return floraison;
    }

    public Plante floraison(Floraison floraison) {
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
        Plante plante = (Plante) o;
        if (plante.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plante.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plante{" +
            "id=" + getId() +
            ", phMin='" + getPhMin() + "'" +
            ", phMax='" + getPhMax() + "'" +
            ", tempMin=" + getTempMin() +
            ", tempMax=" + getTempMax() +
            "}";
    }
}
