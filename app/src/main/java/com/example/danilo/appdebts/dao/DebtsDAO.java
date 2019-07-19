package com.example.danilo.appdebts.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.danilo.appdebts.classes.Category;
import com.example.danilo.appdebts.classes.Debts;
import com.example.danilo.appdebts.database.ScriptDLL;

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
        contentValues.put("cod_cat", deb.getCategory().getId());
        contentValues.put("valor", deb.getValue());
        contentValues.put("descricao", deb.getDescription());
        contentValues.put("data_vencimento", deb.getPaymentDate());
        contentValues.put("data_pagamento", deb.getPayDate());
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
        contentValues.put("cod_cat", deb.getCategory().getId());
        contentValues.put("valor", deb.getValue());
        contentValues.put("descricao", deb.getDescription());
        contentValues.put("data_vencimento", deb.getPaymentDate());
        contentValues.put("data_pagamento", deb.getPayDate());

        String[] params = new String[1];
        params[0] = String.valueOf(deb.getId());
        mConnection.update("dividas", contentValues, "id = ?", params);
        Log.d("DebtsDAO", "Divida ID: " + deb.getId() + " alterada com sucesso");
    }

//    public List<Debts> listDebts(){
//        List<Debts> debts = new ArrayList<Debts>();
//        CategoryDAO categoryDAO = new CategoryDAO(mConnection);
//        Cursor result = mConnection.rawQuery(ScriptDLL.getDebts(),null);
//        if(result.getCount()>0){
//            result.moveToFirst();
//            do{
//                Debts deb = new Debts();
//                Category cod_cate =  categoryDAO.getCategory(result.getColumnIndexOrThrow("cod_cat"));
//                deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
//                deb.setCategory(cod_cate);
//                deb.setValue(result.getDouble(result.getColumnIndexOrThrow("valor")));
//                deb.setDescription(result.getString(result.getColumnIndexOrThrow("descricao")));
//                deb.setPaymentDate(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
//                deb.setPayDate(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
//                debts.add(deb);
//                Log.d("DebtsDAO", "Listando: "+ deb.getId()+" - "+ deb.getCategory()+" - "+ deb.getDescription()+
//                        " - "+ deb.getValue()+" - "+ deb.getPaymentDate()+" - " + deb.getPayDate());
//            }while(result.moveToNext());
//            result.close();
//        }
//        return null;
//    }

    public List<Debts> listDebts(){
        List<Debts> debts = new ArrayList<Debts>();
        Cursor result = mConnection.rawQuery(ScriptDLL.getDebts(),null);

        if(result.getCount()>0){
            Log.d("DebtsDAO","Possui dados!");

            result.moveToFirst();
            do{
                Debts deb = new Debts();
                deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
                deb.setCategory(new CategoryDAO(mConnection).getCategory(result.getInt(result.getColumnIndexOrThrow("cod_cat"))));
                deb.setDescription(result.getString(result.getColumnIndexOrThrow("descricao")));
                deb.setValue(result.getFloat(result.getColumnIndexOrThrow("valor")));
                deb.setPaymentDate(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
                deb.setPayDate(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
                debts.add(deb);
                Log.d("DebtsDAO","Listando: "+deb.getId()+" - "+deb.getDescription());
            }while(result.moveToNext());
            result.close();

        }
        return debts;
    }

//    public Debts getDebts(int id){
//        Debts deb = new Debts();
//        CategoryDAO categoryDAO = new CategoryDAO(mConnection);
//        String[] params = new String[1];
//        params[0] = String.valueOf(id);
//        Cursor result = mConnection.rawQuery("Select * from dividas where id='?'",params);
//        if(result.getCount()>0){
//            result.moveToFirst();
//            Category cod_cate =  categoryDAO.getCategory(result.getColumnIndexOrThrow("cod_cat"));
//            deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
//            deb.setCategory(cod_cate);
//            deb.setValue(result.getDouble(result.getColumnIndexOrThrow("valor")));
//            deb.setDescription(result.getString(result.getColumnIndexOrThrow("descricao")));
//            deb.setPaymentDate(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
//            deb.setPayDate(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
//            result.close();
//            return deb;
//        }
//        return null;
//    }

    public Debts getDebts(int id){
        Debts deb = new Debts();
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        Cursor result = mConnection.rawQuery(ScriptDLL.getDebt(),params);
        if(result.getCount()>0){
            result.moveToFirst();
            deb.setId(result.getInt(result.getColumnIndexOrThrow("id")));
            deb.setCategory(new CategoryDAO(mConnection).getCategory(result.getInt(result.getColumnIndexOrThrow("cod_cat"))));
            deb.setDescription(result.getString(result.getColumnIndexOrThrow("descricao")));
            deb.setValue(result.getFloat(result.getColumnIndexOrThrow("valor")));
            deb.setPaymentDate(result.getString(result.getColumnIndexOrThrow("data_vencimento")));
            deb.setPayDate(result.getString(result.getColumnIndexOrThrow("data_pagamento")));
            result.close();
            return deb;
        }
        return null;
    }
}
