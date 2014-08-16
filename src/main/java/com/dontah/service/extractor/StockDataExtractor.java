package com.dontah.service.extractor;

import com.dontah.domain.Balance;
import com.dontah.domain.Company;
import com.dontah.repository.BalanceRepository;
import com.dontah.repository.CompanyRepository;
import com.dontah.service.Extractor;
import com.dontah.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Bruno on 18/07/14.
 */
@Component
public class StockDataExtractor implements Extractor<List<Company>> {

    @Autowired
    BalanceRepository balanceRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<Company> extract() throws Exception {

        Collection<Company> companyList = companyRepository.getCompanyList();
        List<Company> companies = new ArrayList<>();

        for (Company item : companyList) {
            String s = String.format(Constants.HTTP_STOCK_DATA, item.getCodBolsa());
            Document doc = Jsoup.connect(s).get();
            Company company = new Company();
            company.setCodBolsa(item.getCodBolsa());

            try (FileOutputStream fileOutputStream = new FileOutputStream("stocksFailed.out", true)) {
                try {
                    Elements select = doc.select(".evanual.marcadagua tbody tr");
                    for (Element element : select) {
                        Elements tds = element.getElementsByTag("td");

                        Balance balance = extractBalance(tds);
                        balance.getPk().setCodBolsa(item.getCodBolsa());

                        company.getBalanceList().add(balance);
                        balanceRepository.saveOrUpdate(balance);
                    }

                    companies.add(company);
                    companyRepository.saveOrUpdate(company);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failed: " + item);
                    fileOutputStream.write((item.getCodBolsa() + "\n").getBytes());
                }
            } catch (Exception ignored) {}


        }

        return companies;
    }

    private Balance extractBalance(Elements tds) {
        if(tds.size() > 10 ){
            return new Balance.Builder()
                    .ano(tds.get(0).text())
                    .patrimonio(tds.get(1).text())
                    .receitaLiquida(tds.get(2).text())
                    .lucro(tds.get(3).text())
                    .margem(tds.get(4).text())
                    .roe(tds.get(5).text())
                    .caixa(tds.get(6).text())
                    .cxLiquido(tds.get(7).text())
                    .divida(tds.get(8).text())
                    .divDivPL(tds.get(9).text())
                    .divDivLL(tds.get(10).text()).build();
        }else{
            return new Balance.Builder()
                    .ano(tds.get(0).text())
                    .patrimonio(tds.get(1).text())
                    .receitaLiquida(tds.get(2).text())
                    .lucro(tds.get(3).text())
                    .margem(tds.get(4).text())
                    .roe(tds.get(5).text())
                    .Ie(tds.get(6).text())
                    .Ib(tds.get(7).text())
                    .Pdd(tds.get(8).text())
                    .PddDivLL(tds.get(9).text()).build();
        }
    }

}
