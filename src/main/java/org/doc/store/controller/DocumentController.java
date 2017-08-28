package org.doc.store.controller;

import java.io.IOException;
import java.util.List;

import org.doc.store.dto.DocumentDto;
import org.doc.store.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/documents")
@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	// consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> greeting() {
		return new ResponseEntity<String>("Welcom to document store", HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<DocumentDto> create(
			@RequestParam(name = "docName", required = false) String docName,
			@RequestParam(name = "docType", required = false) String docType,
			@RequestParam(name = "docTag", required = false) String docTag,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		DocumentDto docDto = new DocumentDto();
		docDto.setDocName(docName);
		docDto.setDocTag(docTag);
		docDto.setDocType(docType);
		docDto.setMultiPartfile(file);
		DocumentDto savedDoc = documentService.save(docDto);
		return new ResponseEntity<DocumentDto>(savedDoc, HttpStatus.OK);
	}

	@RequestMapping(value = "/getfile/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<DocumentDto> getFile(@PathVariable(value = "id") String id) {
		DocumentDto documentDto = documentService.getFile(id);
		if ((documentDto == null)) {
			return new ResponseEntity<DocumentDto>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<DocumentDto>(documentDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<List<DocumentDto>> searchDocuments(@RequestBody DocumentDto documentDto) {
		List<DocumentDto> documentDtoList = documentService.searchDocuments(documentDto);
		if (documentDtoList == null)
			return new ResponseEntity<List<DocumentDto>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<DocumentDto>>(documentDtoList, HttpStatus.OK);
	}

	@RequestMapping(value = "/download/{docUuid}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Resource> downloadFile(@PathVariable(value = "docUuid") String docUuid)
			throws IOException {
		DocumentDto documentDto = documentService.getFileStream(docUuid);
		ByteArrayResource resource = new ByteArrayResource(documentDto.getContent());
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.parseMediaType("application/octet-stream"));
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documentDto.getDocName());
		header.setContentLength(documentDto.getContent().length);
		return ResponseEntity.ok().headers(header).body(resource);
	}
}
