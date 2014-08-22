package com.dontah.service.exame;

import com.dontah.domain.Company;
import com.dontah.repository.CompanyRepository;
import com.dontah.service.Extractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 17/07/14.
 */
@Component
public class ExameNamesExtractor implements Extractor<List<Company>> {

    public static String NAMES_URL = "http://exame.abril.com.br/mercados/cotacoes-bovespa/acoes?letter=%s";

    @Autowired
    CompanyRepository companyRepository;

    public  void extract() throws Exception {
        List<Company> companies = extractCompanies();
        persist(companies);
   }

    private List<Company> extractCompanies() throws IOException {
        List<Company> companies = new ArrayList<>();
        for(char i = 'A'; i<='Z';i++ ) {
            Document doc = Jsoup.connect(String.format(NAMES_URL, i)).get();

            Elements stocks = doc.select("#stocks tbody tr");

            for (Element stock : stocks) {
                Company company = new Company();
                company.setCodBolsa(stock.select("a").get(0).text());
                company.setNome(stock.select(".active-name").get(0).text());
                companies.add(company);
            }
            break;
        }
        return companies;
    }

    private void persist(List<Company> companies) {
        companies.forEach(companyRepository::saveOrUpdate);
    }



}
