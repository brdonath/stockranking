package com.dontah.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Bruno on 17/07/14.
 */
@Entity
@Table
public class Balance implements Serializable, Comparable<Balance> {

    @Embeddable
    public static  class CompanyBalancePK implements Serializable {

        @Column( name = "codBolsa")
        private String codBolsa;

        @Column
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CompanyBalancePK that = (CompanyBalancePK) o;

            return codBolsa.equals(that.codBolsa) && ano.equals(that.ano);

        }

        @Override
        public int hashCode() {
            int result = codBolsa.hashCode();
            result = 31 * result + ano.hashCode();
            return result;
        }
    }

    @Override
    public int compareTo(Balance o) {
        return this.pk.getAno().compareTo(o.pk.getAno());
    }

    @EmbeddedId
    private CompanyBalancePK pk;

    @Column
    private String patrimonio;

    @Column(name = "receitaLiquida")
    private String receitaLiquida;

    @Column
    private String lucro;

    @Column
    private String margem;

    @Column
    private String roe;

    @Column
    private String caixa;

    @Column(name = "cxLiquido")
    private String cxLiquido;

    @Column
    private String divida;

    @Column(name = "divDivPL")
    private String divDivPL;

    @Column(name = "divDivLL")
    private String divDivLL;

    @Column
    private String ie;

    @Column
    private String ib;

    @Column
    private String pdd;

    @Column(name = "pddDivLL")
    private String pddDivLL;


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