package com.dontah.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Bruno on 10/08/14.
 */
@Entity
@Table(name = "Result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResultEntity {

    @Id
    @GeneratedValue
    private Long order;

    private String codBolsa;

    @Transient
    private Company company;

    @Type(type="com.dontah.dialect.BigDecimalToStringType")
    private BigDecimal lucro;

    @Type(type="com.dontah.dialect.BigDecimalToStringType")
    private BigDecimal divida;

    @Type(type="com.dontah.dialect.BigDecimalToStringType")
    private BigDecimal roe;

    @Type(type="com.dontah.dialect.BigDecimalToStringType")
    private BigDecimal finalResult;

    private Long position;
    private Long lastPosition;

    public String getCodBolsa() {
        return codBolsa;
    }

    public void setCodBolsa(String codBolsa) {
        this.codBolsa = codBolsa;
    }

    public BigDecimal getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(BigDecimal finalResult) {
        this.finalResult = finalResult;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public BigDecimal getDivida() {
        return divida;
    }

    public void setDivida(BigDecimal divida) {
        this.divida = divida;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Long lastPosition) {
        this.lastPosition = lastPosition;
    }
}
