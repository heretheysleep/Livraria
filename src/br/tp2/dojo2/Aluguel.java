package br.tp2.dojo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluguel {
    private final String cpf;
    private final String tituloLivro;
    private final String autorLivro;
    private boolean emCurso;
    private final Date inicioAluguel;
    private Date terminoAluguel;

    public Aluguel() {
        this.cpf = null;
        this.tituloLivro = null;
        this.autorLivro = null;
        this.emCurso = false;
        this.inicioAluguel = converteDataString("01/01/1900");
        this.terminoAluguel = null;
    }

    public Aluguel(String cpf, String tituloLivro, String autorLivro, String inicioAluguel) {
        this.cpf = cpf;
        this.tituloLivro = tituloLivro;
        this.autorLivro = autorLivro;
        this.emCurso = true;
        this.inicioAluguel = converteDataString(inicioAluguel);
        this.terminoAluguel = null;
    }

    private Date converteDataString(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formato.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public boolean isEmCurso() {
        return emCurso;
    }

    public void setEmCurso(boolean emCurso) {
        this.emCurso = emCurso;
    }

    public Date getInicioAluguel() {
        return inicioAluguel;
    }

    public Date getTerminoAluguel() {
        return terminoAluguel;
    }

    public void setTerminoAluguel(Date terminoAluguel) {
        this.terminoAluguel = terminoAluguel;
    }
}
