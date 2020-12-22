package com.campus.smartcanteen.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.campus.smartcanteen.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static String DB_NAME = "SmartCanteenDB.db";
    private static int DB_VER = 1;

    public Database(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }

    public List<Order> getCarts(){
        SQLiteDatabase database = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductId","ProductName","Jumlah","Harga"};
        String sqlTable = "OrderDetail";
        queryBuilder.setTables(sqlTable);
        Cursor cursor = queryBuilder.query(database,sqlSelect,null,null,null,null,null);

        List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(new Order(cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Jumlah")),
                        cursor.getString(cursor.getColumnIndex("Harga"))
                        ));
            }while (cursor.moveToNext());
        }
        return result;
    }

    //Add Value To Cart
    public void addToCart(Order order) {
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Jumlah,Harga) VALUES('%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getJumlah(),
                order.getHarga());
        database.execSQL(query);
    }

    //Clear Value From Cart
    public void cleanCart(){
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        database.execSQL(query);
    }

}
