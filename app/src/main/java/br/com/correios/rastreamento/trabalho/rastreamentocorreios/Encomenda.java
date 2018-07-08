package br.com.correios.rastreamento.trabalho.rastreamentocorreios;

import java.util.List;

public class Encomenda {

    private String codigo;
    private List<Historico> historico;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Historico> gethistorico() {
        return historico;
    }

    public void setListaHistorico(List<Historico> historico) {
        this.historico = historico;
    }
}