package com.example.joaquin.actividadprogramacion1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    final int SUBACTIVITY_2=2;
    String sexoSeleccionado;
    Button enviarDatos;
    EditText nombre;
    RadioGroup grupoSexo;
    EditText edadTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarDatos=(Button)findViewById(R.id.enviarDatos);
        nombre= (EditText)findViewById(R.id.ponerNombre);
        grupoSexo=(RadioGroup)findViewById(R.id.radioGrupo);
        edadTexto=(EditText)findViewById(R.id.edadTexto);
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

    public void accionBotonEnviarDatos(View v){
        //Añadimos listener a boton
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionSexo();
                Toast.makeText(getApplicationContext(),nombre.getText().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),sexoSeleccionado, Toast.LENGTH_LONG).show();
                if(datosCorrectos()){
                    //Llamare al subactivity
                    Intent i = new Intent(getApplicationContext(),Activity2.class);
                     i.putExtra("nombre",nombre.getText().toString());
                     i.putExtra("sexo", sexoSeleccionado);
                    startActivityForResult(i, SUBACTIVITY_2);
                }else{
                    Toast.makeText(getApplicationContext(),"DATOS INCORRECTOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void gestionSub2(int resultCode, Intent data){
        if(resultCode== RESULT_OK){
            String resultado= data.getExtras().getString("resultado");
            edadTexto.setText("Edad: "+resultado);

            if(Integer.parseInt(resultado)<18){
                Toast.makeText(getApplicationContext(),"Ets un xiquet", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"VAS A MORIR PRONTO", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Error en el SUBACTIVITY 2", Toast.LENGTH_SHORT).show();
        }
    }

    public void seleccionSexo(){
        int idRadioSeleccionado= grupoSexo.getCheckedRadioButtonId();
        RadioButton radioSeleccionado =(RadioButton) findViewById(idRadioSeleccionado);

        if(radioSeleccionado.getText().equals("Varon")){
            sexoSeleccionado="Sr: ";
        }else if (radioSeleccionado.getText().equals("Hembra")){
            sexoSeleccionado= "Sra: ";
        }
        else{
            Toast.makeText(getApplicationContext(),"Sexo sin seleccionar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case SUBACTIVITY_2:
                gestionSub2(resultCode,data);
                break;
        }
    }
}
