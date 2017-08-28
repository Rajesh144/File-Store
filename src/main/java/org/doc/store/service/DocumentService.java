package org.doc.store.service;

import java.util.List;

import org.doc.store.dto.DocumentDto;

public interface DocumentService {

  DocumentDto save(DocumentDto docDto);

  DocumentDto getFile(String docUuid);

  List<DocumentDto> searchDocuments(DocumentDto documentDto);

  DocumentDto getFileStream(String docUuid);
}
