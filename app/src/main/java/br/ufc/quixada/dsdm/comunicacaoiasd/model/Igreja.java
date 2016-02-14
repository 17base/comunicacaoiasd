package br.ufc.quixada.dsdm.comunicacaoiasd.model;

/**
 * Created by snowden on 14/02/16.
 */
public class Igreja {
    private Endereco endereco;
    private String nomeString;


    public Igreja(Endereco endereco, String nomeString) {
        this.endereco = endereco;
        this.nomeString = nomeString;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNomeString() {
        return nomeString;
    }

    public void setNomeString(String nomeString) {
        this.nomeString = nomeString;
    }
}
