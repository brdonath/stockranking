package com.dontah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Bruno on 17/07/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Comparable<Item>{
    @JsonProperty("CodBolsa")
    private String codBolsa;

    public Item() {
    }

    public Item(String codBolsa) {
        this.codBolsa = codBolsa;
    }

    public String getCodBolsa() {
        return codBolsa;
    }

    public void setCodBolsa(String codBolsa) {
        this.codBolsa = codBolsa;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codBolsa='" + codBolsa + '\'' +
                '}';
    }


    @Override
    public int compareTo(Item o) {
        return this.getCodBolsa().compareTo(o.getCodBolsa());
    }
}
