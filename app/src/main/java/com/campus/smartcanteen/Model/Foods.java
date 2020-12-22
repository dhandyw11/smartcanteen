package com.campus.smartcanteen.Model;

public class Foods {
    private String Nama;
    private String Gambar;
    private String Deskripsi;
    private String Harga;
    private String MenuId;

    public Foods() {

    }

    public Foods(String nama, String gambar, String deskripsi, String harga, String menuId) {
        Nama = nama;
        Gambar = gambar;
        Deskripsi = deskripsi;
        Harga = harga;
        MenuId = menuId;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
