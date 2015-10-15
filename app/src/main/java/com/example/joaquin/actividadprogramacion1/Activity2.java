package com.example.joaquin.actividadprogramacion1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends Activity {
    final int SUBACTIVITY_2=2;
    private Button botonContinuar;
    private TextView textoSaludo;
    private EditText cajaEdad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //Definición de objetos
        textoSaludo = (TextView) findViewById(R.id.textoSaludo);
        cajaEdad = (EditText) findViewById(R.id.cajaEdad);
        botonContinuar = (Button) findViewById(R.id.botonContinuar);
        gestionSaludo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void accionBotonContinuar(View v){
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= getIntent();
                String resultadoEdad=cajaEdad.getText().toString();
                intent.putExtra("resultado", resultadoEdad);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    public void gestionSaludo(){
        Bundle bundle = getIntent().getExtras();
        textoSaludo.setText("Hola "+ bundle.getString("sexo")+bundle.getString("nombre")+". Indica tu edad:");

    }

}
