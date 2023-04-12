package com.axisInfoline.helpDesk.document.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axisInfoline.helpDesk.document.domain.entity.Documents;
import com.axisInfoline.helpDesk.document.repository.DocumentsRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


@Service("documentsService")
public class DocumentsServiceImpl implements DocumentsService {

	@Value("${asset.dir}")
	private String dir;
	@Autowired
	DocumentsRepository documentsRepository;


	@Override
	public Documents uploadDocument(Documents document) {

		return documentsRepository.save(document);
	}

	@Override
	public void downloadDocument(HttpServletResponse response, String document)
	{
		try {
			String documentPath = dir + document;

			File file = new File(documentPath);

			if (file.exists()) 
			{
				FileInputStream fis = new FileInputStream(file);
				response.setContentType(Files.probeContentType(file.toPath()));
				response.setContentLength((int) file.length());
				int len = file.getName().split("\\.").length;
				response.addHeader("Content-disposition",
						"attachment;filename=\"" + "document." + file.getName().split("\\.")[len - 1] + "\"");
				IOUtils.copy(fis, response.getOutputStream());
				response.flushBuffer();
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public @ResponseBody byte[] viewDocument(String document) {
		try {
			String documentPath = dir + document;

			File file = new File(documentPath);
			
			if (file.exists()) {
				InputStream in = new FileInputStream(file);
				return IOUtils.toByteArray(in);
			}
			
			return "File not found".getBytes();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


}
