package org.doc.store.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.doc.store.dto.DocumentDto;
import org.doc.store.model.Document;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class SearchDocumentRepositoryImpl implements SearchDocumentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Document> searchByCriteria(DocumentDto documentDto) {
		StringBuilder criteriaQuery = new StringBuilder();
		criteriaQuery.append(criteriaListQuery()).append(criteriaWhereClause(documentDto));
		Query quer = entityManager.createQuery(criteriaQuery.toString());
		criteriaBindParameters(quer, documentDto);
		return quer.getResultList();
	}

	public String criteriaListQuery() {
		StringBuilder select = new StringBuilder();
		select.append(" SELECT d FROM Document d ");
		return select.toString();
	}

	public String criteriaWhereClause(DocumentDto documentDto) {
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1=1 ");
		if (!StringUtils.isEmpty(documentDto.getUuid())) {
			where.append(" AND (d.uuid =:uuid)");
		}
		if (!StringUtils.isEmpty(documentDto.getDocName())) {
			where.append(" AND (d.docName =:docName)");
		}
		if (!StringUtils.isEmpty(documentDto.getDocType())) {
			where.append(" AND (d.docType =:docType)");
		}

		return where.toString();

	}

	private Query criteriaBindParameters(Query query, DocumentDto documentDto) {
		if (!StringUtils.isEmpty(documentDto.getUuid())) {
			query.setParameter("uuid", documentDto.getUuid());
		}
		if (!StringUtils.isEmpty(documentDto.getDocName())) {
			query.setParameter("docName", documentDto.getDocName());
		}

		if (!StringUtils.isEmpty(documentDto.getDocType())) {
			query.setParameter("docType", documentDto.getDocType());
		}
		return query;
	}

}
