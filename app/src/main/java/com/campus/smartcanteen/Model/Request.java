package com.campus.smartcanteen.Model;

import java.util.List;

public class Request {
    private String NomorHP;
    private String Nama;
    private String Alamat;
    private String Total;
    private String Status;
    private List<Order> Foods;

    public Request() {

    }

    public Request(String nomorHP, String nama,String alamat, String total, List<Order> foods) {
        this.NomorHP = nomorHP;
        this.Alamat = alamat;
        this.Nama = nama;
        this.Total = total;
        this.Status = "0"; //Default 0, 1 : Sedang Jalan, 2 : Telah Sampai
        this.Foods = foods;
    }

    public String getNomorHP() {
        return NomorHP;
    }

    public void setNomorHP(String nomorHP) {
        NomorHP = nomorHP;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Order> getFoods() {
        return Foods;
    }

    public void setFoods(List<Order> foods) {
        Foods = foods;
    }
}
