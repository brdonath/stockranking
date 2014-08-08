package com.dontah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Bruno on 17/07/14.
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Company implements Serializable, Comparable<Company> {

    @Id
    @JsonProperty("CodBolsa")
    private String codBolsa;

    @JsonProperty("Nome")
    private String nome;

    @OneToMany(mappedBy = "pk.codBolsa", fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @SortNatural
    private Set<Balance> balanceList;

    public Company() {
    }

    public Company(Set<Balance> balanceList) {
        this.balanceList = balanceList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodBolsa() {
        return codBolsa;
    }

    public void setCodBolsa(String codBolsa) {
        this.codBolsa = codBolsa;
    }


    public Set<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(Set<Balance> balanceList) {
        this.balanceList = balanceList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "balanceList=" + balanceList +
                ", codBolsa='" + codBolsa + '\'' +
                '}';
    }

    @Override
    public int compareTo(Company o) {
        return this.getCodBolsa().compareTo(o.getCodBolsa());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!codBolsa.equals(company.codBolsa)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codBolsa.hashCode();
    }
}
