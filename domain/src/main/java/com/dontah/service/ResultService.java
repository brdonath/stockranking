package com.dontah.service;

import com.dontah.domain.ResultEntity;
import com.dontah.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cin_bdonath on 05/04/2017.
 */
@Service
public class ResultService {

    @Autowired
    private ResultsRepository resultsRepository;


    @Cacheable(cacheNames = "results")
    public List<ResultEntity> findAll(int page, int size){
        List<ResultEntity> results = resultsRepository.findAll(
                new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.ASC, "position")))).getContent();
        return results;
    }

    @Cacheable(cacheNames = "results")
    public List<ResultEntity> findAll(List<String> codList){
        List<ResultEntity> results = new ArrayList<>();
        codList.forEach(codBolsa -> results.add(resultsRepository.findOneByCodBolsa(codBolsa)));
        return results;
    }

    @Cacheable(cacheNames = "results")
    public ResultEntity findByCodBolsa(String codBolsa){
        return resultsRepository.findOneByCodBolsa(codBolsa);
    }
}
