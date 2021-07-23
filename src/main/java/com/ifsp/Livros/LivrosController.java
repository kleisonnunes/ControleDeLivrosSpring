package com.ifsp.Livros;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/livro")
public class LivrosController{
    @Autowired
    private LivrosRepositorio repositorio;

    @PostMapping(path="/add")
    public @ResponseBody String novoLivro(@RequestParam String nome, @RequestParam String autor, @RequestParam String editora){
        Livro book = new Livro();
        book.setNome(nome);
        book.setAutor(autor);
        book.setEditora(editora);
        repositorio.save(book);
        return "Valores salvos com sucesso";
    }
    
    @PostMapping(path = "/addlivro")
    public @ResponseBody String novoLivro2(@RequestBody Livro newBook){
    	repositorio.save(newBook);
    	return "Livro inserido com sucesso";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Livro> retornaTodos(){
        return repositorio.findAll();
    }
    
    @PutMapping(path="update_livro/{id}")
    public @ResponseBody Optional<Livro> updateLivro(@PathVariable(required = true, name = "id") long id,
    	   @RequestBody Livro livro){
    	Optional<Livro> livroo = repositorio.findById(id);
    	if(livroo.isPresent()) {
    		livroo.get().setNome(livro.getNome());
    		livroo.get().setAutor(livro.getAutor());
    		livroo.get().setEditora(livro.getEditora());
    		repositorio.save(livroo.get());
    		return livroo;
    	}
    return null;	
    }
    
    @PutMapping(path = "/livro/{id}")
    public @ResponseBody ResponseEntity<Livro> alteraBook(@PathVariable(required = true, name = "id") long id,
    	   @RequestBody Livro book){
    	Optional<Livro> livroo = repositorio.findById(id);
    	if(livroo.isPresent()) {
    		livroo.get().setNome(book.getNome());
    		livroo.get().setAutor(book.getAutor());
    		livroo.get().setEditora(book.getEditora());
		return ResponseEntity.ok(repositorio.save(livroo.get()));    	
    	}
    return ResponseEntity.status(404).build();
    }
    
    @DeleteMapping(path="delete_livro/{id}")
    public @ResponseBody String deletarLivro(@PathVariable(required = true, name = "id") long id) {
    	Optional<Livro> livroo = repositorio.findById(id);
    	if(livroo.isPresent()) {
    		repositorio.delete(livroo.get());
    		return "Livro deletado com sucesso";
    	}
    	return "Livro Não encontrado";
    }

    @GetMapping(path="/book")
    public @ResponseBody Optional<Livro> retornaBook(@RequestParam String id){
        return repositorio.findById(Long.parseLong(id));
    }
    
    @GetMapping(path="/locate_book/{id}")
    public @ResponseBody Optional<Livro> retornaBook2(@PathVariable (required=true, name="id") long id){
    	return repositorio.findById(id);
    }
    
//    @GetMapping(path="locate_by_autor/{Autor}")
//    public @ResponseBody List<Livro> locateByAutor(@PathVariable (required=true, name="Autor") String Autor){
//    	return repositorio.findByAutor(Autor);
//    }
    
//    @GetMapping(path="locate_by_editora/{Editora}")
//    public @ResponseBody List<Livro> locateByEditora(@PathVariable (required=true, name="Editora") String Editora){
//    	return repositorio.findByEditora(Editora);
//    }
}