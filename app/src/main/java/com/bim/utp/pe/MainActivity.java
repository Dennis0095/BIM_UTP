package com.bim.utp.pe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.mvvm.view.enviarDinero.EnviarDineroActivity;
import com.bim.utp.pe.mvvm.view.registro.RegisterActivity;
import com.bim.utp.pe.mvvm.view.registro.RegisterActivity2;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bim.utp.pe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.botonseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
              startActivity(intent);
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.reporteSentinel)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

        //getEntidades();
        //get0peradores();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mandaPlata:
                Intent inte = new Intent(this , EnviarDineroActivity.class);
                startActivity(inte);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void getEntidades(){
        final ParametroViewModel viewModel = ViewModelProviders.of(this).get(ParametroViewModel.class);
        viewModel.getEntidadesFinancieras();
        viewModel.setListenerEntidadesFinancieras().observe(this, baseResponse -> viewModel.verifyResponse(baseResponse));
        viewModel.setEntidadFinanciera().observe(this, new Observer<ArrayList<EntidadFinanciera>>() {
            @Override
            public void onChanged(ArrayList<EntidadFinanciera> entidadFinancieras) {
                for(EntidadFinanciera entidad: entidadFinancieras){
                    Log.d("DMA_LECTOR", " entidad = " + entidad.getDescripcion());

                    Spinner spinnere = findViewById(R.id.spinnerEntidades);

                    List<String> list = new ArrayList<>();
                    list.add(entidad.getDescripcion());

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnere.setAdapter(dataAdapter);
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }

    public void get0peradores(){
        final ParametroViewModel viewModel = ViewModelProviders.of(this).get(ParametroViewModel.class);
        viewModel.getOperadoresMoviles();
        viewModel.setListenerOperadoresMoviles().observe(this, baseResponse -> viewModel.verifyResponse2(baseResponse));
        viewModel.setOperadorMovil().observe(this, new Observer<ArrayList<OperadorMovil>>() {
            @Override
            public void onChanged(ArrayList<OperadorMovil> operadoresMoviles) {
                for(OperadorMovil operador: operadoresMoviles){
                    Log.d("DMA_LECTOR", " operador = " + operador.getDescripcion());
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}