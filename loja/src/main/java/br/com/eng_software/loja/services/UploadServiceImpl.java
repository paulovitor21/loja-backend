package br.com.eng_software.loja.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadServiceImpl implements IUploadService {
	@Override
	public String uploadFile(MultipartFile arquivo) {
		try {
			/*
			 * copiar o arquivo recebido via requisição para pasta definida
			 * e retornar o caminho desse arquivo. Se der qualquer erro,
			 * retorna NULL
			 */
			System.out.println("DEBUG - " + arquivo.getOriginalFilename());
			String caminho = "C:\\projeto-faculdade\\loja_admin\\src\\images";
			Path path = Paths.get(caminho + File.separator + arquivo.getOriginalFilename());
			Files.copy(arquivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("DEBUG - Arquivo copiado...");
			return arquivo.getOriginalFilename();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
