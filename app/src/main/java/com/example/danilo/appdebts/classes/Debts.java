package com.example.danilo.appdebts.classes;

/**
 * Created by aluno on 27/06/19.
 */

public class Debts {
    private int mId;
    private Category cod_cat;
    private double valor;
    private String descricao;
    private String data_vencimento;
    private String data_pagamento;

    public Debts() {

    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Category getCod_cat() {
        return cod_cat;
    }

    public void setCod_cat(Category cod_cat) {
        this.cod_cat = cod_cat;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    @Override
    public String toString() {
        return "Debts{" +
                "mId=" + mId +
                ", cod_cat=" + cod_cat +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", data_vencimento='" + data_vencimento + '\'' +
                ", data_pagamento='" + data_pagamento + '\'' +
                '}';
    }
}
