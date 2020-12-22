package com.campus.smartcanteen.MenuRecycler;

public class MenuTodayClass {
    String Nama;
    int Gambar;

    public MenuTodayClass() {

    }

    public MenuTodayClass(String nama, int gambar) {
        Nama = nama;
        Gambar = gambar;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getGambar() {
        return Gambar;
    }

    public void setGambar(int gambar) {
        Gambar = gambar;
    }
}
