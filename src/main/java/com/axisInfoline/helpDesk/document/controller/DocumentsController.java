package com.axisInfoline.helpDesk.document.controller;

import com.axisInfoline.helpDesk.document.domain.dto.DocumentsDto;
import com.axisInfoline.helpDesk.document.domain.entity.Documents;
import com.axisInfoline.helpDesk.document.service.DocumentsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"https://portal.axisinfoline.com","http://localhost:3000"})
@RestController
public class DocumentsController {


	@Value("${asset.dir}")
	private String dir;
	@Autowired
	DocumentsService documentsService;



	@GetMapping(value = "/document/download/{documentName}")
	private void downloadDocument(HttpServletResponse response, @PathVariable("documentName") String documentName)
	{
		documentsService.downloadDocument(response, documentName);
	}

	@GetMapping(value = "/document/view/{documentName}")
	private ResponseEntity<byte[]> viewDocument(@PathVariable("documentName") String documentName)
	{

		HttpHeaders headers = new HttpHeaders();
		byte[] media = documentsService.viewDocument(documentName);

		String extension = "";

		int i = documentName.lastIndexOf('.');
		if (i > 0) {
			extension = documentName.substring(i+1);
		}

		if (extension.equalsIgnoreCase("pdf"))
		{
			headers.setContentType(MediaType.APPLICATION_PDF);
		} else if (extension.equalsIgnoreCase("png")) {
			headers.setContentType(MediaType.IMAGE_PNG);
		} else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		} else if (extension.equalsIgnoreCase("txt")) {
			headers.setContentType(MediaType.TEXT_PLAIN);
		} else if (extension.equalsIgnoreCase("xlsx")) {
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		} else if (extension.equalsIgnoreCase("docx")) {
			headers.setContentType(MediaType
					.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
		}

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

		return responseEntity;
	}

	@PostMapping(value = { "/document" }, consumes = { "multipart/form-data" })
	private Object uploadDocument(@RequestParam("userId") String userId, @RequestParam("documentFile") MultipartFile uploadDocument) {
		Documents document = new Documents();
		String name = "";

		Map<String,Object> response = new HashMap<String, Object>();

		if (!uploadDocument.isEmpty()) {
			try {
				byte[] bytes = uploadDocument.getBytes();


				name = "dcmt_" + userId + "_" +  uploadDocument.getOriginalFilename().split("\\.")[0] + "_" + System.currentTimeMillis();
//				System.out.println("Filename: " + uploadDocument.getOriginalFilename().split("\\.")[1]);

				String extension = "." + uploadDocument.getOriginalFilename().split("\\.")[1];
				name = name + extension;
				String path = dir + name ;

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
				stream.write(bytes);
				stream.close();

				document.setOwnerId(userId);
				document.setName(name);

				Documents savedDocument = documentsService.uploadDocument(document);
				DocumentsDto documentsDto = new DocumentsDto();
				documentsDto.setId(savedDocument.getId());
				documentsDto.setOwnerId(savedDocument.getOwnerId());
				documentsDto.setName(savedDocument.getName());


				response.put("status","200");
				response.put("message","Document uploaded successfully");
				response.put("data",documentsDto);
				return new ResponseEntity<>(response, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				response.put("status","500");
				response.put("message","You failed to upload " + name);

				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} else {
			response.put("status","404");
			response.put("message", "Document is null");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
}
	