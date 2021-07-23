package com.ifsp.Livros;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ifsp.Livros.Livro;

public interface LivrosRepositorio extends CrudRepository <Livro, Long>{
	
//	List<Livro> findByNome(String Nome);
//	List<Livro> findByAutor(String Autor);
//	List<Livro> findByEditora(String Editora);
}
