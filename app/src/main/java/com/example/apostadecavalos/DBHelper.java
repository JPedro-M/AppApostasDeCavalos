package com.example.apostadecavalos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static String nome = "BancoDados.db";
    private static int versao = 1;

    public DBHelper(Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE dados(pontos TEXT, upgrade1 INTEGER);";
        db.execSQL(str);
        String str2 = "INSERT INTO dados (pontos, upgrade1) VALUES ('100', 0);";
        db.execSQL(str2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dados;");
        onCreate(db);
    }

    public void reset(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DROP TABLE dados;");
        String str = "CREATE TABLE dados(pontos INTEGER, upgrade1 INTEGER);";
        db.execSQL(str);
        start();
    }

    public void setPontos(int pontos) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "UPDATE dados SET pontos = " + pontos + ";";
        db.execSQL(str);
    }

    public int getPontos() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("dados", new String[]{"pontos"}, null, null, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getInt(0);
        }else{
            return c.getCount();
        }
    }

    public void setUpgrade1(int upgrade1) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "UPDATE dados SET upgrade1 = " + upgrade1 + ";";
        db.execSQL(str);
    }

    public int getUpgrade1() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT upgrade1 FROM dados;", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getInt(0);
        }else{
            return c.getCount();
        }
    }

    public int teste(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT upgrade1 FROM dados;", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getInt(0);
        }else{
            return c.getCount();
        }
    }
    public void start(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT upgrade1 FROM dados;", null);
        if (c.getCount() == 0){
            String str2 = "INSERT INTO dados (pontos, upgrade1) VALUES ('0', 0);";
            db.execSQL(str2);
        }

    }
}