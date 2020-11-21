package com.example.datebase;

public class Products {
    private int _id;
    private String _productname;

    //Added this empty constructor in lesson 50 in case we ever want to create the object and assign it later.
    public Products(){

    }
    public Products(String productName) {
        this._productname = productName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }
}
