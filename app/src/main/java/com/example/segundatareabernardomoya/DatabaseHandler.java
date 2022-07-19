package com.example.segundatareabernardomoya;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "carrosSeminuevos";

    // Contacts table name
    private static final String TABLE_CARROS= "carros";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IDENTIFICADOR = "identificador";
    private static final String KEY_MARCA = "marca";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_MODELO = "modelo";
    private static final String KEY_NUMERO_CILINDROS = "numero de cilindros";
    private static final String KEY_PRECIO = "precio";
    private static final String KEY_IMAGEN = "imagen";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CARROS="CREATE TABLE " + TABLE_CARROS + "("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_IDENTIFICADOR +" TEXT,"
                + KEY_MARCA +" TEXT,"
                + KEY_NOMBRE +" TEXT,"
                + KEY_MODELO +" TEXT,"
                + KEY_NUMERO_CILINDROS +" TEXT,"
                + KEY_PRECIO +" TEXT,"
                + KEY_IMAGEN  +" BLOB" + ")";
        db.execSQL(CREATE_TABLE_CARROS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARROS);

        // Create tables again
        onCreate(db);
    }

    //Insert values to the table contacts
    public void addCarros(Carro carro){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        /*values.put(KEY_FNAME, contact.getFName());
        values.put(KEY_POTO, contact.getImage() );*/

        values.put(KEY_IDENTIFICADOR, carro.get_identificador());
        values.put(KEY_MARCA, carro.get_marca());
        values.put(KEY_NOMBRE, carro.get_nombre());
        values.put(KEY_MODELO, carro.get_modelo());
        values.put(KEY_NUMERO_CILINDROS, carro.get_numero_cilindros());
        values.put(KEY_PRECIO, carro.get_precio());
        values.put(KEY_IMAGEN, carro.get_img());
        db.insert(TABLE_CARROS, null, values);
        db.close();
    }


    public List<Carro> getAllCarros() {
        List<Carro> carroList = new ArrayList<Carro>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CARROS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Carro carro = new Carro();
                carro.set_id(Integer.parseInt(cursor.getString(0)));
                carro.set_identificador(cursor.getString(1));
                carro.set_marca(cursor.getString(2));
                carro.set_nombre(cursor.getString(3));
                carro.set_modelo(cursor.getString(4));
                carro.set_numero_cilindros(cursor.getString(5));
                carro.set_precio(cursor.getString(6));
                carro.set_img(cursor.getBlob(7));
                // Adding contact to list
                carroList.add(carro);
            } while (cursor.moveToNext());
        }

        // return contact list
        return carroList;
    }


}
