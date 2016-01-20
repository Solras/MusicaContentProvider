package com.example.clase.practicamusicacontentprovaider.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clase.practicamusicacontentprovaider.R;
import com.example.clase.practicamusicacontentprovaider.tablas.Disco;

import java.util.ArrayList;

public class AdaptadorDiscos extends ArrayAdapter<Disco>{

    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Disco> valores;
    private Context con;

    static class ViewHolder{
        public TextView tvTitulo;
        public ImageView imgFoto;
    }


    public AdaptadorDiscos(Context context, ArrayList<Disco> objects) {
        super(context, R.layout.item, objects);
        this.res= R.layout.item; // LAYOUT
        this.valores= objects; // LISTA DE VALORES
        this.con= context;
        lInflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv= new ViewHolder();
        if(convertView==null){
            convertView= lInflator.inflate(res, null);
            TextView tvTitulo= (TextView)convertView.findViewById(R.id.tvTitulo) ;
            gv.tvTitulo=tvTitulo;
//            ImageView imgFoto= (ImageView) convertView.findViewById(R.id.imgFoto);
//            gv.imgFoto= imgFoto;
            convertView.setTag(gv);
        }else{
            gv= (ViewHolder) convertView.getTag();
        }
        gv.tvTitulo.setText(valores.get(position).getTiulo());
//        gv.imgFoto.setImageResource(R.drawable.disco);


     //       String rutafoto =valores.get(position).getRutaImagen() ;
     //     Bitmap bit= this.stringToBitMap(rutafoto);
     //       gv.imgFoto.setImageBitmap(bit);


        return convertView;
    }


}
