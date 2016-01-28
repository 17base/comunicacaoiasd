package br.ufc.quixada.dsdm.comunicacaoiasd.model;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class Endereco {

    private long id;
    private String endereco;
    private String bairro;

    public Endereco(String endereco, String bairro) {
        this.endereco = endereco;
        this.bairro = bairro;
    }

    public Endereco() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Endereço{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", bairro='" + bairro + '\'' +
                '}';
    }
}
