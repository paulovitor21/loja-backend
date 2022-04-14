package br.com.eng_software.loja.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.eng_software.loja.dto.PathDTO;
import br.com.eng_software.loja.model.Categoria;
import br.com.eng_software.loja.model.Produto;
import br.com.eng_software.loja.services.IProdutoService;
import br.com.eng_software.loja.services.IUploadService;

@RestController
@CrossOrigin("*")
public class ProdutoController {
	@Autowired
	private IProdutoService service;
	
	@Autowired
	private IUploadService upload;
	
	// Cadastrar novo produto
	@PostMapping("/produto")
	public ResponseEntity<Produto> novoProduto(@RequestBody Produto novo) {
		try {
			service.inserirNovoProduto(novo);
			return ResponseEntity.status(201).body(novo);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	
	// Enviar foto do produto
	@PostMapping("/produto/upload")
	public ResponseEntity<PathDTO> uploadFoto(@RequestParam(name = "arquivo") MultipartFile arquivo) {
		String path = upload.uploadFile(arquivo);
		if (path != null) {
			PathDTO pathDTO = new PathDTO();
			pathDTO.setPathToFile(path);
			return ResponseEntity.status(201).body(pathDTO);
		}
		return ResponseEntity.badRequest().build();
	}
	
	// listar produtos disponíveis e em destaque
	@GetMapping("/produto")
	public ResponseEntity<ArrayList<Produto>> recuperarTodos() {
		//return ResponseEntity.ok(service.listarDisponiveis()); // -> listar disponiveis
		return ResponseEntity.ok(service.listarDestaques());
	}
	
	// listar produtos por categoria
	@GetMapping("/produto/categoria/{id}")
	public ResponseEntity<ArrayList<Produto>> recuperarPorCategoria(@PathVariable(name = "id") int idCateg) {
		Categoria cat = new Categoria();
		cat.setId(idCateg);
		return ResponseEntity.ok(service.listarPorCategoria(cat));
	}
	
	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> recuperarPorId(@PathVariable(name = "id") int idProduto) {
		Produto prod = service.recuperarPorId(idProduto);
		if (prod != null) {
			return ResponseEntity.ok(prod);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/produto/busca")
	public ResponseEntity<ArrayList<Produto>> buscarPorPalavraChave(@RequestParam (name="key") String key) {
		System.out.println("key = " + key);
		if (key != null) {
			return ResponseEntity.ok(service.listarPorPalavraChave(key));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/produto/todos")
	public ResponseEntity<ArrayList<Produto>> buscarTodos() {
		return ResponseEntity.ok(service.listarTodos());
	}
	
}
