package com.ifsp.Livros;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id_livro;
    private String Nome;
    private String Autor;
    private String Editora;

    public long getId_livro() {
        return Id_livro;
    }

    public void setId_livro(long Id_livro) {
        this.Id_livro = Id_livro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String Editora) {
        this.Editora = Editora;
    }
}
