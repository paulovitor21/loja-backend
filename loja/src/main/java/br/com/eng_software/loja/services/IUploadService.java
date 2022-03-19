package br.com.eng_software.loja.services;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	public String uploadFile(MultipartFile arquivo);
}
