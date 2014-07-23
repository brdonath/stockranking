package com.dontah.service;

/**
 * Created by Bruno on 18/07/14.
 */
public interface Extractor<T> {
    T extract() throws Exception;
}
