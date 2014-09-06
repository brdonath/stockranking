package com.dontah.controller.impl;

import com.dontah.controller.IRankingAPIController;
import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by Bruno on 04/09/14.
 */
@Controller
public class RankingAPIController implements IRankingAPIController {

    private static final int BUFFER_SIZE = 4096;

    @Autowired ResultsRepository resultsRepository;
    @Autowired CompanyRepository companyRepository;

    @Override
    public List<ResultEntity> getData(
            @RequestParam(defaultValue = "0") int whereAmI,
            @RequestParam(defaultValue = "30")  int offset){
        return resultsRepository.list(whereAmI,offset, false);
    }

    @Override
    public List<ResultEntity> getResultDetails(
            @PathVariable String codBolsa){
        return resultsRepository.get(codBolsa, true);
    }

    @Override
    public List<ResultEntity> getResultDetailsOnly(
            @PathVariable String codBolsa) {
        return resultsRepository.getOnly(codBolsa);
    }

    @Override
    public Collection<Company> getCompanyNames() {
        return companyRepository.getCompanyNames();
    }

    @Override
    public List<ResultEntity> search(
            @RequestParam(value="q") String query) {
        return resultsRepository.get(query, false);
    }

//    @RequestMapping("/json")
//    @ResponseBody
//    public List<ResultEntity> doDownload(){
//        return resultsRepository.list(0,500, true);
//    }
//
//    @RequestMapping(value ="/zip", method = RequestMethod.GET)
//    public void doDownlod(HttpServletResponse response) throws IOException {
//
//        // get MIME type of the file
//        String mimeType = "application/zip";
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        GZIPOutputStream gzip = new GZIPOutputStream(out);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(gzip, resultsRepository.list(0, 5000, true));
//
//        // set content attributes for the response
//        response.setContentType(mimeType);
////        response.setContentLength((int) out.);
//
//        // set headers for the response
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"","fulldata.zip");
//        response.setHeader(headerKey, headerValue);
//
//        // get output stream of the response
//        ServletOutputStream outputStream = response.getOutputStream();
//        outputStream.write(out.toByteArray());
//
//        outputStream.close();
//    }
}
