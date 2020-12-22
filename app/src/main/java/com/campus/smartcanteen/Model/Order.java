package com.campus.smartcanteen.Model;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Jumlah;
    private String Harga;

    public Order() {

    }

    public Order(String productId, String productName, String jumlah, String harga) {
        ProductId = productId;
        ProductName = productName;
        Jumlah = jumlah;
        Harga = harga;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        Jumlah = jumlah;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }
}
