package com.axisInfoline.helpDesk.document.repository;

import java.util.List;

import com.axisInfoline.helpDesk.document.domain.entity.Documents;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("documentsRepository")
public interface DocumentsRepository extends JpaRepository<Documents, Long> {

	@Override
	List<Documents> findAll();
}
