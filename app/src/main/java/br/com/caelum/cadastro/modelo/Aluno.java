package br.com.caelum.cadastro.modelo;

/**
 * Created by rodrigo on 22/03/16.
 */
public class Aluno {

    private Long id;
    private String nome;
    private String site;
    private String endereco;
    private String telefone;
    private Double nota;
    private String foto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getSite() {
        return site;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Double getNota() {
        return nota;
    }

    public String getFoto() {
        return foto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
