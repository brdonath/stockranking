package com.dontah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 10/08/14.
 */
@Entity
@Table(name = "Result")
public class ResultEntity {

    @Id
    @GeneratedValue
    @Column(name = "\"order\"")
    private Long order;

    @Column(name = "codBolsa", insertable = false, updatable = false)
    private String codBolsa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codBolsa")
    private Company company;

    @Type(type = "com.dontah.dialect.BigDecimalToStringType")
    @Column
    private BigDecimal lucro;

    @Type(type = "com.dontah.dialect.BigDecimalToStringType")
    @Column
    private BigDecimal divida;

    @Type(type = "com.dontah.dialect.BigDecimalToStringType")
    @Column
    private BigDecimal roe;

    @Type(type = "com.dontah.dialect.BigDecimalToStringType")
    @Column
    private BigDecimal finalResult;

    @Column
    private Long position;

    @Column(name = "lastPosition")
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

    public List<Balance> getBalanceListInReverseOrder() {
        return getCompany() == null ? null :
                getCompany()
                        .getBalanceList()
                        .stream()
                        .sorted(Comparator.<Balance>reverseOrder())
                        .collect(Collectors.toList());
    }
}
