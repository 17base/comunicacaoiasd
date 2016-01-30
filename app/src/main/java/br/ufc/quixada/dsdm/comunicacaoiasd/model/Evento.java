package br.ufc.quixada.dsdm.comunicacaoiasd.model;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class Evento {

    private long id;
    private String mes;
    private String data;
    private String titulo;
    private String descricao;
    private String organizacao;

    public Evento(String mes, String data, String titulo, String descricao, String organizacao) {
        this.mes = mes;
        this.data = data;
        this.titulo = titulo;
        this.descricao = descricao;
        this.organizacao = organizacao;
    }

    public Evento() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "mes='" + mes + '\'' +
                ", data='" + data + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", organizacao='" + organizacao + '\'' +
                ", id=" + id +
                '}';
    }
}
