package br.com.caelum.cadastro.modelo;

/**
 * Created by rodrigo on 22/03/16.
 */
public class Aluno {

    private String nome;
    private String site;
    private String endereco;
    private String telefone;
    private Double nota;
    private String foto;


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

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", site='" + site + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nota=" + nota +
                ", foto='" + foto + '\'' +
                '}';
    }
}
