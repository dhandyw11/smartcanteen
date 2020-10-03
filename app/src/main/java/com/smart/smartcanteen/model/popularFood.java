package com.smart.smartcanteen.model;

public class popularFood {

    String nama;
    String harga;
    Integer imageUrl;

    public popularFood(String nama, String harga, Integer imageUrl) {
        this.nama = nama;
        this.harga = harga;
        this.imageUrl = imageUrl;
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
