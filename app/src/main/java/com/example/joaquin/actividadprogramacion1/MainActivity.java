package com.example.joaquin.actividadprogramacion1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    final int SUBACTIVITY_1=1;

    Button enviarDatos;
    EditText nombre;
    RadioGroup grupoSexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarDatos=(Button)findViewById(R.id.enviarDatos);
        nombre= (EditText)findViewById(R.id.ponerNombre);
        grupoSexo=(RadioGroup)findViewById(R.id.radioGrupo);

        //Añadimos listener a boton
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datosCorrectos()){
                    //Llamare al subactivity
                    Intent i = new Intent(getApplicationContext(),Activity2.class);
                    startActivityForResult(i,SUBACTIVITY_1);
                }else{
                    Toast.makeText(getApplicationContext(),"DATOS INCORRECTOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public boolean datosCorrectos(){
        if(nombre.getText().length()<=0){
            return false;
        }else{
            if(grupoSexo.getCheckedRadioButtonId()==-1){
                return false;
            }else{
                return true;
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
