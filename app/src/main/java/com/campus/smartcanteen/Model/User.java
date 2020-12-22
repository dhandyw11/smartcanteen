package com.campus.smartcanteen.Model;

public class User {
    private String Nama;
    private String Password;
    private String NomorHP;

    public User() {

    }

    public User(String nama, String password) {
        Nama = nama;
        Password = password;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNomorHP(){
        return NomorHP;
    }

    public void setNomorHP(String nomorHP){
        NomorHP = nomorHP;
    }
}

