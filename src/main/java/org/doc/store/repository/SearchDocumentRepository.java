package org.doc.store.repository;

import java.util.List;

import org.doc.store.dto.DocumentDto;
import org.doc.store.model.Document;

public interface SearchDocumentRepository {
	public List<Document> searchByCriteria(DocumentDto documentDto);
}
