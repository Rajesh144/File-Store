package org.doc.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "document")
@NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d")
public class Document {

  @Id
  @Column(name = "id")
  private String uuid;

  @Column(name = "doc_name")
  private String docName;

  @Column(name = "doc_type")
  private String docType;

  @Column(name = "doc_tag")
  private String docTag;

  @Column(name = "doc_size")
  private long docSize;

  @Column(name = "doc_url")
  private String docUrl;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getDocName() {
    return docName;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }

  public String getDocType() {
    return docType;
  }

  public void setDocType(String docType) {
    this.docType = docType;
  }

  public String getDocTag() {
    return docTag;
  }

  public void setDocTag(String docTag) {
    this.docTag = docTag;
  }

  public long getDocSize() {
    return docSize;
  }

  public void setDocSize(long docSize) {
    this.docSize = docSize;
  }

  public String getDocUrl() {
    return docUrl;
  }

  public void setDocUrl(String docUrl) {
    this.docUrl = docUrl;
  }

}
