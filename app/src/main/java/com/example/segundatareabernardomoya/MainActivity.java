package com.example.segundatareabernardomoya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText identificador;
    private EditText marca;
    private EditText nombre;
    private EditText modelo;
    private EditText numeroCilindros;
    private EditText precioCarro;
    private ImageView pic;
    private DatabaseHandler db;
    private String identificador_coche;
    private String marca_coche;
    private String nombre_coche;
    private String modelo_coche;
    private String numero_cilindros;
    private String precio;
    private ListView lv;
    private dataAdapter data;
    private Carro dataModel;
    private Bitmap bp;
    private byte[] photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        lv = (ListView) findViewById(R.id.list1);
        pic = (ImageView) findViewById(R.id.pic);
        identificador = (EditText) findViewById(R.id.txt1);
        marca = (EditText) findViewById(R.id.txt2);
        nombre = (EditText) findViewById(R.id.txt3);
        modelo = (EditText) findViewById(R.id.txt4);
        numeroCilindros = (EditText) findViewById(R.id.txt5);
        precioCarro = (EditText) findViewById(R.id.txt6);
    }

    public void buttonClicked(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.save:

                if (identificador.getText().toString().trim().equals("") || marca.getText().toString().trim().equals("")
                        || nombre.getText().toString().trim().equals("") || modelo.getText().toString().trim().equals("")
                        || numeroCilindros.getText().toString().trim().equals("") || precioCarro.getText().toString().trim().equals("")
                ) {
                    Toast.makeText(getApplicationContext(), "Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                } else {
                    addCarro();
                }

                break;

            case R.id.display:

                ShowRecords();
                break;
            case R.id.pic:
                selectImage();
                break;
        }
    }

    public void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri choosenImage = data.getData();

                    if (choosenImage != null) {

                        bp = decodeUri(choosenImage, 400);
                        pic.setImageBitmap(bp);
                    }
                }
        }
    }

    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }

    // function to get values from the Edittext and image
    private void getValues() {
        identificador_coche = identificador.getText().toString();
        marca_coche = marca.getText().toString();
        nombre_coche = nombre.getText().toString();
        modelo_coche = modelo.getText().toString();
        numero_cilindros = modelo.getText().toString();
        precio = modelo.getText().toString();
        photo = profileImage(bp);
    }

    //Insert data to the database
    private void addCarro() {
        getValues();

        db.addCarros(new Carro(identificador_coche, marca_coche, nombre_coche, modelo_coche, numero_cilindros, precio, photo));
        Toast.makeText(getApplicationContext(), "Guardado exitosamente", Toast.LENGTH_LONG).show();
    }

    //Retrieve data from the database and set to the list view
    private void ShowRecords() {
        final ArrayList<Carro> carros = new ArrayList<>(db.getAllCarros());
        data = new dataAdapter(this, carros);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = carros.get(position);
                Toast.makeText(getApplicationContext(), String.valueOf(dataModel.get_id()), Toast.LENGTH_SHORT).show();
            }
        });


    }
}