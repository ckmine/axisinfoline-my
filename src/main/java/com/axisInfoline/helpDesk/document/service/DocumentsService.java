package com.axisInfoline.helpDesk.document.service;

import java.util.List;

import com.axisInfoline.helpDesk.document.domain.entity.Documents;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface DocumentsService {

	public Documents uploadDocument(Documents document);

	
	public void downloadDocument(HttpServletResponse response, String document);
	
	public byte[] viewDocument(String document); 
	
//	public ResponseEntity<Object> deleteDocumentById(Long id);
}
