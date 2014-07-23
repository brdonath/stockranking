package com.dontah.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Bruno on 17/07/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class D {
    @JsonProperty("Count")
    private int count;

    @JsonProperty("Items")
    private List<Company> itemList;

    public List<Company> getItemList() {
        return itemList;
    }

    public void setItemList(List<Company> itemList) {
        this.itemList = itemList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "D{" +
                "count=" + count +
                ", itemList=" + itemList +
                '}';
    }
}
