package org.doc.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// @EnableWebMvc
public class DocumentStoreApplication {
  public static void main(String[] args) {
    SpringApplication.run(DocumentStoreApplication.class, args);
  }

  // @Bean
  // public MultipartResolver multipartResolver() {
  // org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new
  // org.springframework.web.multipart.commons.CommonsMultipartResolver();
  // multipartResolver.setMaxUploadSize(1000000);
  // return multipartResolver;
  // }
}
