package com.smart.smartcanteen.model;

import java.sql.Struct;

public class KantinDepanFood {

    String nama;
    String harga;
    Integer imageUrl;

    public KantinDepanFood(String nama, String harga, Integer imageUrl, String rating, String namakantin) {
        this.nama = nama;
        this.harga = harga;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.namakantin = namakantin;
    }

    String rating;
    String namakantin;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNamakantin() {
        return namakantin;
    }

    public void setNamakantin(String namakantin) {
        this.namakantin = namakantin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
