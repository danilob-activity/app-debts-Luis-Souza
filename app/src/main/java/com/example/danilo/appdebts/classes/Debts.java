package com.example.danilo.appdebts.classes;

/**
 * Created by aluno on 27/06/19.
 */

public class Debts {
    private int mId;
    private Category mCategory;
    private double mValue;
    private String mDescription;
    private String mPaymentDate;
    private String mPayDate;

    public Debts() {

    }

    public Debts(Category category, float value, String description, String paymentDate, String payDate) {
        mCategory = category;
        mValue = value;
        mDescription = description;
        mPaymentDate = paymentDate;
        mPayDate = payDate;
    }

    public Debts(Category category, float value, String paymentDate) {
        mCategory = category;
        mValue = value;
        mPaymentDate = paymentDate;
    }

    public Debts(Category category, float value, String paymentDate, String payDate) {
        mCategory = category;
        mValue = value;
        mPaymentDate = paymentDate;
        mPayDate = payDate;
    }


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        this.mCategory = category;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        this.mValue = value;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getPaymentDate() {
        return mPaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.mPaymentDate = paymentDate;
    }

    public String getPayDate() {
        return mPayDate;
    }

    public void setPayDate(String payDate) {
        this.mPayDate = payDate;
    }

    @Override
    public String toString() {
        return "Debts{" +
                "mId=" + mId +
                ", mCategory=" + mCategory +
                ", mValue=" + mValue +
                ", mDescription='" + mDescription + '\'' +
                ", mPaymentDate='" + mPaymentDate + '\'' +
                ", mPayDate='" + mPayDate + '\'' +
                '}';
    }
}
