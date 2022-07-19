package com.example.segundatareabernardomoya;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class dataAdapter extends ArrayAdapter<Carro> {
    Context context;
    ArrayList<Carro> mcarro;

    public dataAdapter(Context context, ArrayList<Carro> carro){
        super(context, R.layout.listcars, carro);
        this.context=context;
        this.mcarro=carro;
    }

    public class Holder{
        ImageView pic;
        TextView nombreCarro;
        TextView identificadorCoche;
        TextView marcaCoche;
        TextView modeloCoche;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Carro carro = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listcars, parent, false);

            viewHolder.pic = (ImageView) convertView.findViewById(R.id.imageView_flag);
            viewHolder.nombreCarro = (TextView) convertView.findViewById(R.id.textView_nombreCarro);
            viewHolder.identificadorCoche = (TextView) convertView.findViewById(R.id.textView_identificacion_coche);
            viewHolder.marcaCoche = (TextView) convertView.findViewById(R.id.textView_marca_coche);
            viewHolder.modeloCoche = (TextView) convertView.findViewById(R.id.textView_modelo_coche);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }


        viewHolder.pic.setImageBitmap(convertToBitmap(carro.get_img()));
        viewHolder.nombreCarro.setText("Nombre: "+carro.get_nombre());
        viewHolder.identificadorCoche.setText("Identificador: "+carro.get_identificador());
        viewHolder.marcaCoche.setText("Marca: "+carro.get_marca());
        viewHolder.modeloCoche.setText("Modelo: "+carro.get_modelo());


        // Return the completed view to render on screen
        return convertView;
    }
    //get bitmap image from byte array

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);

    }
}
