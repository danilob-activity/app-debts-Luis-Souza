package com.example.danilo.appdebts.classes;

/**
 * Created by aluno on 27/06/19.
 */

public class Category {
    private long mId;
    private String mType;

    public Category() {

    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setId(int id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }
}
