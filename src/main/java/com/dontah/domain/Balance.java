package com.dontah.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Bruno on 17/07/14.
 */
@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Balance implements Serializable, Comparable<Balance> {

    @Embeddable
    public static  class CompanyBalancePK implements Serializable {

        private String codBolsa;
        private String ano;

        public CompanyBalancePK() {}

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

    @Override
    public int compareTo(Balance o) {
        return this.pk.getAno().compareTo(o.pk.getAno());
    }

    @EmbeddedId
    private CompanyBalancePK pk;

    private String patrimonio;
    private String receitaLiquida;
    private String lucro;
    private String margem;
    private String roe;
    private String caixa;
    private String cxLiquido;
    private String divida;
    private String divDivPL;
    private String divDivLL;
    private String ie;
    private String ib;
    private String pdd;
    private String pddDivLL;


//    @ManyToOne
//    @JoinColumn(name = "codBolsa", insertable = false, updatable = false)
//    private Company company;

    public Balance(){
        pk = new CompanyBalancePK();
    }

    private Balance(Builder builder) {
        this();
        getPk().setCodBolsa(builder.codBolsa);
        getPk().setAno(builder.ano);
        setPatrimonio(builder.patrimonio);
        setReceitaLiquida(builder.receitaLiquida);
        setLucro(builder.lucro);
        setMargem(builder.margem);
        setRoe(builder.roe);
        setCaixa(builder.caixa);
        setCxLiquido(builder.cxLiquido);
        setDivida(builder.divida);
        setDivDivPL(builder.divDivPL);
        setDivDivLL(builder.divDivLL);
        setIe(builder.ie);
        setIb(builder.ib);
        setPdd(builder.pdd);
        setPddDivLL(builder.pddDivLL);
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIb() {
        return ib;
    }

    public void setIb(String ib) {
        this.ib = ib;
    }

    public String getPdd() {
        return pdd;
    }

    public void setPdd(String pdd) {
        this.pdd = pdd;
    }

    public String getPddDivLL() {
        return pddDivLL;
    }

    public void setPddDivLL(String pddDivLL) {
        this.pddDivLL = pddDivLL;
    }

    public CompanyBalancePK getPk() {
        return pk;
    }

    public void setPk(CompanyBalancePK pk) {
        this.pk = pk;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getReceitaLiquida() {
        return receitaLiquida;
    }

    public void setReceitaLiquida(String receitaLiquida) {
        this.receitaLiquida = receitaLiquida;
    }

    public String getLucro() {
        return lucro;
    }

    public void setLucro(String lucro) {
        this.lucro = lucro;
    }

    public String getMargem() {
        return margem;
    }

    public void setMargem(String margem) {
        this.margem = margem;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getCxLiquido() {
        return cxLiquido;
    }

    public void setCxLiquido(String cxLiquido) {
        this.cxLiquido = cxLiquido;
    }

    public String getDivida() {
        return divida;
    }

    public void setDivida(String divida) {
        this.divida = divida;
    }

    public String getDivDivPL() {
        return divDivPL;
    }

    public void setDivDivPL(String divDivPL) {
        this.divDivPL = divDivPL;
    }

    public String getDivDivLL() {
        return divDivLL;
    }

    public void setDivDivLL(String divDivLL) {
        this.divDivLL = divDivLL;
    }


    public static final class Builder {
        private String codBolsa;
        private String id;
        private String ano;
        private String patrimonio;
        private String receitaLiquida;
        private String lucro;
        private String margem;
        private String roe;
        private String caixa;
        private String cxLiquido;
        private String divida;
        private String divDivPL;
        private String divDivLL;
        private String ie;
        private String ib;
        private String pdd;
        private String pddDivLL;

        public Builder() {
        }

        public Builder Ie(String ie) {
            this.ie = ie;
            return this;
        }

        public Builder Ib(String ib) {
            this.ib = ib;
            return this;
        }

        public Builder Pdd(String pdd) {
            this.pdd = pdd;
            return this;
        }

        public Builder PddDivLL(String pddDivLL) {
            this.pddDivLL = pddDivLL;
            return this;
        }

        public Builder codBolsa(String codBolsa) {
            this.codBolsa = codBolsa;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder ano(String ano) {
            this.ano = ano;
            return this;
        }

        public Builder patrimonio(String patrimonio) {
            this.patrimonio = patrimonio;
            return this;
        }

        public Builder receitaLiquida(String receitaLiquida) {
            this.receitaLiquida = receitaLiquida;
            return this;
        }

        public Builder lucro(String lucro) {
            this.lucro = lucro;
            return this;
        }

        public Builder margem(String margem) {
            this.margem = margem;
            return this;
        }

        public Builder roe(String roe) {
            this.roe = roe;
            return this;
        }

        public Builder caixa(String caixa) {
            this.caixa = caixa;
            return this;
        }

        public Builder cxLiquido(String cxLiquido) {
            this.cxLiquido = cxLiquido;
            return this;
        }

        public Builder divida(String divida) {
            this.divida = divida;
            return this;
        }

        public Builder divDivPL(String divDivPL) {
            this.divDivPL = divDivPL;
            return this;
        }

        public Builder divDivLL(String divDivLL) {
            this.divDivLL = divDivLL;
            return this;
        }

        public Balance build() {
            return new Balance(this);
        }
    }

    @Override
    public String toString() {
        return "Balance{" +
                "pk=" + pk +
                ", patrimonio='" + patrimonio + '\'' +
                ", receitaLiquida='" + receitaLiquida + '\'' +
                ", lucro='" + lucro + '\'' +
                ", margem='" + margem + '\'' +
                ", roe='" + roe + '\'' +
                ", caixa='" + caixa + '\'' +
                ", cxLiquido='" + cxLiquido + '\'' +
                ", divida='" + divida + '\'' +
                ", divDivPL='" + divDivPL + '\'' +
                ", divDivLL='" + divDivLL + '\'' +
                ", ie='" + ie + '\'' +
                ", ib='" + ib + '\'' +
                ", pdd='" + pdd + '\'' +
                ", pddDivLL='" + pddDivLL + '\'' +
                '}';
    }
}