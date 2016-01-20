package com.example.clase.practicamusicacontentprovaider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.clase.practicamusicacontentprovaider.Adaptadores.Adaptador;
import com.example.clase.practicamusicacontentprovaider.Adaptadores.AdaptadorInterprete;
import com.example.clase.practicamusicacontentprovaider.Adaptadores.AdaptadorDiscos;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorCanciones;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorDiscos;
import com.example.clase.practicamusicacontentprovaider.Gestion.GestorInterpretes;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;
import com.example.clase.practicamusicacontentprovaider.tablas.Disco;
import com.example.clase.practicamusicacontentprovaider.tablas.Interprete;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //para guardar los datos de las tablas creamos arrays para meter nuestros objetos
    private List<Cancion> listaMusica = new ArrayList<Cancion>();
    private List<Disco> listaDiscos = new ArrayList<Disco>();
    private List<Interprete> listaInterpretes = new ArrayList<Interprete>();
    //declaramos los gestores de las BD
    private GestorInterpretes gestorInterpretes;
    private GestorCanciones gestorCanciones;
    private GestorDiscos gestorDiscos;

    private ListView lv;
    private Adaptador ad;
    private AdaptadorDiscos add;
    private AdaptadorInterprete adi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lvMostrar);

        //INICIAMOS los gestores de la bd
        gestorCanciones = new GestorCanciones(this);
        gestorDiscos = new GestorDiscos(this);
        gestorInterpretes = new GestorInterpretes(this);

        init();
    }

    @Override
    protected void onResume() {
        gestorCanciones.open();
        gestorDiscos.open();
        gestorInterpretes.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gestorCanciones.close();
        gestorDiscos.close();
        gestorInterpretes.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_mostrarMusica: {
                listaMusica.clear();
                listaMusica = gestorCanciones.selectCanciones();
                ad = new Adaptador(this, (ArrayList<Cancion>) listaMusica);
                lv.setAdapter(ad);
                return true;
            }
            case R.id.menu_mostrarDiscos: {
                listaDiscos.clear();
                listaDiscos = gestorDiscos.selectDiscos();
                add = new AdaptadorDiscos(this, (ArrayList<Disco>) listaDiscos);
                lv.setAdapter(add);
                return true;
            }
            case R.id.menu_mostraArtistas: {
                listaInterpretes.clear();
                listaInterpretes = gestorInterpretes.selectInterpretes();
                adi = new AdaptadorInterprete(this, (ArrayList<Interprete>) listaInterpretes);
                lv.setAdapter(adi);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void init() {
        try {
            Cursor cur = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if (cur == null) {
                //si esta nulo el cursor
                Log.e("MIAPP", "cursor nulo al leer las canciones(buscadorAudio)");

            } else if (!cur.moveToFirst()) {
                // la consulta esta mal en canciones
                Log.e("MIAPP", "conuslta errona canciones(buscadorAudio)");

            } else {
                Log.i("MIAPP", "lee canciones(buscadorAudio)");
                do {
                    int title = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int artist = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int album = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    Cancion cancion = new Cancion();
                    Disco disco = new Disco();
                    Interprete interprete = new Interprete();
                    /*creamos el interprete y guardamos*/
                    interprete.setNombre(cur.getString(artist));
                    if(!existeInterprete(interprete))
                        listaInterpretes.add(interprete);
                    /*creamos el disco y guardamos*/
                    disco.setTiulo(cur.getString(album));
                    disco.setIdInterprete(interprete.getIdInterprete());
                    if(!existeDisco(disco))
                        listaDiscos.add(disco);
                    /*creamos la cancion y la cuadarmos */
                    cancion.setNombreCancion(cur.getString(title));
                    cancion.setIdDisco(disco.getIdDisco());
                    if(!existeCancion(cancion))
                        listaMusica.add(cancion);
                } while (cur.moveToNext());
                gestorInterpretes.insertarInterpretes((ArrayList<Interprete>) listaInterpretes);
                gestorCanciones.insertarCanciones((ArrayList<Cancion>) listaMusica);
                gestorDiscos.insertarDiscos((ArrayList<Disco>) listaDiscos);
                listaMusica.clear();
                listaMusica = gestorCanciones.selectCanciones();
                ad = new Adaptador(this, (ArrayList<Cancion>) listaMusica);
                lv.setAdapter(ad);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private boolean existeDisco(Disco d){
        for (Disco dis : listaDiscos) {
            if(d.getIdDisco()==dis.getIdDisco()){
                return true;
            }
        }
        return false;
    }

    private boolean existeInterprete(Interprete i){
        for (Interprete in : listaInterpretes) {
            if(i.getIdInterprete()==in.getIdInterprete()){
                return true;
            }
        }
        return false;
    }

    private boolean existeCancion(Cancion c){
        for (Cancion ca : listaMusica) {
            if(c.getIdCancion()==ca.getIdCancion()){
                return true;
            }
        }
        return false;
    }

}
