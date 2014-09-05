package com.dontah.controller;

import com.dontah.domain.ResultEntity;
import com.dontah.repository.ResultsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Bruno on 04/09/14.
 */
@Controller
@RequestMapping("/data" )
public class DataDownloaderController {

    private static final int BUFFER_SIZE = 4096;

    @Autowired
    ResultsRepository resultsRepository;

    @RequestMapping("/json")
    @ResponseBody
    public List<ResultEntity> doDownload(){
        return resultsRepository.list(0,500);
    }

    @RequestMapping(value ="/zip", method = RequestMethod.GET)
    public void doDownlod(HttpServletResponse response) throws IOException {

        // get MIME type of the file
        String mimeType = "application/zip";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(gzip, resultsRepository.list(0, 5000));

        // set content attributes for the response
        response.setContentType(mimeType);
//        response.setContentLength((int) out.);

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"","fulldata.zip");
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(out.toByteArray());

        outputStream.close();
    }
}
