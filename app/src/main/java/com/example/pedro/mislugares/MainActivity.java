package com.example.pedro.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Lugares lugares = new LugaresVector();
    Button acercadebtn;
    Button salirbtn;
    Button preferenciasbtn;
    Button mostrarbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        acercadebtn = (Button) findViewById(R.id.IDacercadebtn);
        acercadebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarAcercade();
            }
        });

        salirbtn = (Button) findViewById(R.id.IDsalirbtn);
        salirbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        preferenciasbtn = (Button) findViewById(R.id.IDprefsbtn);
        preferenciasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarPreferencias();
            }
        });

        mostrarbtn = (Button) findViewById(R.id.IDmostrarbtn);
        mostrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPreferencias();
            }
        });

    }

    private void lanzarAcercade() {
        Intent intent = new Intent(this, AcercadeActivity.class);
        startActivity(intent);
    }

    private void lanzarPreferencias() {
        Intent intent = new Intent(this, PreferenciasActivity.class);
        startActivity(intent);
    }

    public void mostrarPreferencias() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String s = "Notificaciones: " + preferences.getBoolean("notificaciones", true) +
                ", máximo a listar: " + preferences.getString("maximo", "?") +
                " orden: " + preferences.getString("orden", "?");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }


    public void lanzarVistaLugar(View view) {
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        long id = Long.parseLong(entrada.getText().toString());
                        Intent i = new Intent(MainActivity.this,
                                VistaLugarActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
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
        if (id == R.id.preferencias) {
            lanzarPreferencias();
            return true;
        }

        if (id == R.id.acercaDe) {
            lanzarAcercade();
        }

        if (id == R.id.menu_buscar) {
            lanzarVistaLugar(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
