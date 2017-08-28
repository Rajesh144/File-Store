package org.doc.store.repository;

import org.doc.store.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends BaseRepository<Document, String> {

}
