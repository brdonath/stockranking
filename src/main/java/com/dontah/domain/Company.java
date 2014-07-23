package com.dontah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.SortedSet;

/**
 * Created by Bruno on 17/07/14.
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable, Comparable<Company> {

    @Id
    @JsonProperty("CodBolsa")
    private String codBolsa;

    @JsonProperty("Nome")
    private String nome;

    @Sort(type = SortType.NATURAL)
    @OneToMany(mappedBy = "company")
    private SortedSet<Balance> balanceList;

    public Company() {
    }

    public Company(SortedSet<Balance> balanceList) {
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


    public SortedSet<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(SortedSet<Balance> balanceList) {
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
}
