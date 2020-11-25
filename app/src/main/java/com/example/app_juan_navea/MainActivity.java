package com.example.app_juan_navea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText titular,apell,numtar,dd,mm,cod,direcedit,ciudad,estado,codpos;
    String n = titular.getText().toString();
    String a = apell.getText().toString();
    String t= numtar.getText().toString();
    String mes = dd.getText().toString();
    String anio = mm.getText().toString();
    String codig = cod.getText().toString();
    String d = direcedit.getText().toString();
    String c = ciudad.getText().toString();
    String e = estado.getText().toString();
    String codp = codpos.getText().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titular = (EditText)findViewById(R.id.titular);
        apell = (EditText)findViewById(R.id.apell);
        numtar = (EditText)findViewById(R.id.num_tar);
        dd = (EditText)findViewById(R.id.mes);
        mm = (EditText)findViewById(R.id.anio);
        cod = (EditText)findViewById(R.id.cod);
        direcedit = (EditText)findViewById(R.id.derecedit);
        ciudad = (EditText)findViewById(R.id.ciudad);
        estado = (EditText)findViewById(R.id.estado);
        codpos = (EditText)findViewById(R.id.codpos);
    }
    public void Registrar(View view){
        SQLiteOpenHelpers admin = new SQLiteOpenHelpers(this, "bdtarjeta", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String n = titular.getText().toString();
        String a = apell.getText().toString();
        String t= numtar.getText().toString();
        String mes = dd.getText().toString();
        String anio = mm.getText().toString();
        String codig = cod.getText().toString();
        String d = direcedit.getText().toString();
        String c = ciudad.getText().toString();
        String e = estado.getText().toString();
        String codp = codpos.getText().toString();

        if(!n.isEmpty() && !a.isEmpty() && !t.isEmpty() && !mes.isEmpty() && !anio.isEmpty() && !codig.isEmpty() &&
                !d.isEmpty() && !c.isEmpty() && !e.isEmpty() && !codp.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("name", n);
            registro.put("appe", a);
            registro.put("tar", t);
            registro.put("ms", mes);
            registro.put("an", anio);
            registro.put("co", codig);
            registro.put("dir", d);
            registro.put("ci", c);
            registro.put("cod", codp);

            BaseDeDatos.insert("tarjeta", null, registro);

            BaseDeDatos.close();
            titular.setText("");
            apell.setText("");
            numtar.setText("");
            dd.setText("");
            mm.setText("");
            cod.setText("");
            direcedit.setText("");
            ciudad.setText("");
            estado.setText("");
            codpos.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void Buscar(View view){
        SQLiteOpenHelpers admin = new SQLiteOpenHelpers(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String numtarjeta = numtar.getText().toString();

        if(!numtarjeta.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select * from tarjeta where num_tar =" + numtarjeta, null);

            if(fila.moveToFirst()){
                titular.setText(fila.getString(0));
                apell.setText(fila.getString(1));
                numtar.setText(fila.getString(2));
                dd.setText(fila.getString(3));
                mm.setText(fila.getString(4));
                cod.setText(fila.getString(5));
                direcedit.setText(fila.getString(6));
                ciudad.setText(fila.getString(7));
                estado.setText(fila.getString(8));
                codpos.setText(fila.getString(9));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe la tarjeta", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el numero de la tarjeta", Toast.LENGTH_SHORT).show();
        }
    }
}
