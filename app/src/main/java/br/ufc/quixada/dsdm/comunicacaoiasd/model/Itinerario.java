package br.ufc.quixada.dsdm.comunicacaoiasd.model;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class Itinerario {

    private long id;
    private String endereco;
    private String bairro;
    private String dia;
    private String mes;

    public Itinerario(String endereco, String bairro, String dia, String mes) {
        this.endereco = endereco;
        this.bairro = bairro;
        this.dia = dia;
        this.mes = mes;
    }

    public Itinerario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                "id=" + id +
                ", endereco='" + endereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", dia='" + dia + '\'' +
                ", mes='" + mes + '\'' +
                '}';
    }
}
