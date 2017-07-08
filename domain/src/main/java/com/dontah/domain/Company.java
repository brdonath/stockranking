package com.dontah.domain;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Bruno on 17/07/14.
 */
@Entity
@Table
public class Company implements Serializable, Comparable<Company> {

    @Id
    @Column(name = "codBolsa")
    private String codBolsa;

    @Column
    private String nome;

    @OneToMany(mappedBy = "pk.codBolsa", fetch = FetchType.LAZY)
    @SortNatural
    private Set<Balance> balanceList = new TreeSet<>();

    public Company() {
    }

    public Company(String codBolsa, String nome) {
        this.codBolsa = codBolsa;
        this.nome = nome;
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
