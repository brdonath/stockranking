package com.dontah.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompanyBalancePK implements Serializable {

    String codBolsa;
    String ano;

    public CompanyBalancePK() {
    }

    public CompanyBalancePK(String codBolsa, String ano) {

        this.codBolsa = codBolsa;
        this.ano = ano;
    }

    public String getCodBolsa() {
        return codBolsa;
    }

    public void setCodBolsa(String codBolsa) {
        this.codBolsa = codBolsa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "CompanyBalancePK{" +
                "codBolsa='" + codBolsa + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}
