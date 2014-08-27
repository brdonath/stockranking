package com.dontah.service.extractor;

import com.dontah.domain.Company;
import com.dontah.domain.D;
import com.dontah.repository.CompanyRepository;
import com.dontah.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Bruno on 17/07/14.
 */
@Component
public class StockNamesExtractor implements Extractor<D> {

    @Autowired
    CompanyRepository companyRepository;

    public void extract() throws Exception {

        List<Company> items = new ArrayList<>();

        for(char i= 'A'; i<='Z'; i++){

            ObjectMapper objectMapper = new ObjectMapper();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(String.format("{'ativo':'%s', 'pagina':'0'}",i), httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            LinkedHashMap linkedHashMap = restTemplate.postForObject(Constants.HTTP_STOCK_NAMES, request, LinkedHashMap.class);

            D d = objectMapper.readValue(linkedHashMap.get("d").toString(), D.class);
            items.addAll(d.getItemList());
        }

        Collections.sort(items);
        items.forEach(companyRepository::saveOrUpdate);
    }


}