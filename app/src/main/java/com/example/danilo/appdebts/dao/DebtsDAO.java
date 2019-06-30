package com.example.danilo.appdebts.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.danilo.appdebts.classes.Category;
import com.example.danilo.appdebts.classes.Debts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 27/06/19.
 */

public class DebtsDAO {

    private SQLiteDatabase mConnection;

    public DebtsDAO(SQLiteDatabase conection){
        mConnection = conection;
    }

    public void insert(Debts deb){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",deb.getId());
        contentValues.put("cod_cat", deb.getCod_cat().getId());
        contentValues.put("valor", deb.getValor());
        contentValues.put("descricao", deb.getDescricao());
        contentValues.put("data_vencimento", deb.getData_vencimento());
        contentValues.put("data_pagamento", deb.getData_pagamento());
        mConnection.insertOrThrow("dividas",null,contentValues);
        Log.d("DebtsDAO","Inserção realizada com sucesso!");
    }

    public void remove(long id){
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        mConnection.delete("dividas","id = ?",params);
    }

    public void alter(Debts deb){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cod_cat", deb.getCod_cat().getId());
        contentValues.put("valor", deb.getValor());
        contentValues.put("descricao", deb.getDescricao());
        contentValues.put("data_vencimento", deb.getData_vencimento());
        contentValues.put("data_pagamento", deb.getData_pagamento());
        String[] params = new String[1];
        params[0] = String.valueOf(deb.getId());
        mConnection.update("dividas", contentValues, "id = ?", params);
        Log.d("DebtsDAO", "Divida ID: " + deb.getId() + " alterada com sucesso");
    }

    public List<Debts> listDebts(){
        List<Debts> debts = new ArrayList<Debts>();
        CategoryDAO categoryDAO = new CategoryDAO(mConnection);
        Cursor result = mConnection.rawQuery("Select id, cod_cat, valor, descricao, data_vencimento, data_pagamento from dividas",null);
        if(result.getCount()>0){
            result.moveToFirst();
            do{
                Debts deb = new Debts();
                Category cod_cate =  categoryDAO.getCategory(result.getColumnIndexOrThrow("cod_cat"));
                deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
                deb.setCod_cat(cod_cate);
                deb.setValor(result.getDouble(result.getColumnIndexOrThrow("valor")));
                deb.setDescricao(result.getString(result.getColumnIndexOrThrow("descricao")));
                deb.setData_vencimento(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
                deb.setData_pagamento(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
                debts.add(deb);
                Log.d("DebtsDAO", "Listando: "+ deb.getId()+" - "+ deb.getCod_cat()+" - "+ deb.getDescricao()+
                        " - "+ deb.getValor()+" - "+ deb.getData_vencimento()+" - " + deb.getData_pagamento());
            }while(result.moveToNext());
            result.close();
        }
        return null;
    }

    public Debts getDebts(int id){
        Debts deb = new Debts();
        CategoryDAO categoryDAO = new CategoryDAO(mConnection);
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        Cursor result = mConnection.rawQuery("Select * from dividas where id='?'",params);
        if(result.getCount()>0){
            result.moveToFirst();
            Category cod_cate =  categoryDAO.getCategory(result.getColumnIndexOrThrow("cod_cat"));
            deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
            deb.setCod_cat(cod_cate);
            deb.setValor(result.getDouble(result.getColumnIndexOrThrow("valor")));
            deb.setDescricao(result.getString(result.getColumnIndexOrThrow("descricao")));
            deb.setData_vencimento(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
            deb.setData_pagamento(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
            result.close();
            return deb;
        }
        return null;
    }
}
