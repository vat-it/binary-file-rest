package com.vatit.example;

import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class DemoController {

	@RequestMapping(method = RequestMethod.POST, path = "/file")
	public void putFile(@RequestBody byte[] body, @RequestHeader("Content-type") String contentType, @RequestParam("externalId") String externalId, @RequestParam(value = "filename", required = false) String filename) throws IOException, MimeTypeException {
		FileOutputStream out = new FileOutputStream(String.format("c:/temp/%s%s", filename == null ? UUID.randomUUID() : filename, MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension()));
		out.write(body);
		out.close();
	}
}
