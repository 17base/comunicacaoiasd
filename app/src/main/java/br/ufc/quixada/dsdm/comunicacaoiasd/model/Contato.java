package br.ufc.quixada.dsdm.comunicacaoiasd.model;

/**
 * Created by jonas_000 on 12/01/2016.
 */
public class Contato {

    private long id;
    private String dep;
    private String resp;
    private String ass;
    private String email_resp;
    private String email_ass;
    private String telefone_resp;
    private String telefone_ass;
    private String igreja;

    public Contato(String dep, String resp, String ass, String email_resp, String email_ass, String telefone_resp, String telefone_ass, String igreja) {
        this.dep = dep;
        this.resp = resp;
        this.ass = ass;
        this.email_resp = email_resp;
        this.email_ass = email_ass;
        this.telefone_resp = telefone_resp;
        this.telefone_ass = telefone_ass;
        this.igreja = igreja;
    }

    public Contato() {

    }

    public String getIgreja() {
        return igreja;
    }

    public void setIgreja(String igreja) {
        this.igreja = igreja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getAss() {
        return ass;
    }

    public void setAss(String ass) {
        this.ass = ass;
    }

    public String getEmail_resp() {
        return email_resp;
    }

    public void setEmail_resp(String email_resp) {
        this.email_resp = email_resp;
    }

    public String getEmail_ass() {
        return email_ass;
    }

    public void setEmail_ass(String email_ass) {
        this.email_ass = email_ass;
    }

    public String getTelefone_resp() {
        return telefone_resp;
    }

    public void setTelefone_resp(String telefone_resp) {
        this.telefone_resp = telefone_resp;
    }

    public String getTelefone_ass() {
        return telefone_ass;
    }

    public void setTelefone_ass(String telefone_ass) {
        this.telefone_ass = telefone_ass;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", dep='" + dep + '\'' +
                ", resp='" + resp + '\'' +
                ", ass='" + ass + '\'' +
                ", email_resp='" + email_resp + '\'' +
                ", email_ass='" + email_ass + '\'' +
                ", telefone_resp='" + telefone_resp + '\'' +
                ", telefone_ass='" + telefone_ass + '\'' +
                '}';
    }
}
