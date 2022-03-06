package com.bim.utp.pe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
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

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(),"Hola este es un toast", Toast.LENGTH_SHORT).show();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Este es la accion del boton", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(getApplicationContext(),"Hola este es un toast", Toast.LENGTH_SHORT).show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //getEntidades();
        //getDepositos(int in_idUsuario);


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
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }



    public void getDepositos(int in_idUsuario){
        final ParametroViewModel viewModel = ViewModelProviders.of(this).get(ParametroViewModel.class);
        viewModel.getReporteDepositos(in_idUsuario);
        viewModel.setListenerReporteDepositos(in_idUsuario).observe(this, baseResponse -> viewModel.verifyResponse(baseResponse));
        viewModel.setReporteDeposito().observe(this, new Observer<ArrayList<EntidadDeposito>>() {
            @Override
            public void onChanged(ArrayList<EntidadDeposito> entidadDepositos) {
                for(EntidadDeposito entidad: entidadDepositos){
                    Log.d("DMA_LECTOR", " entidad = " + entidad.getIdDeposito());
                    Log.d("DMA_LECTOR", " entidad = " + entidad.getMonto());
                    Log.d("DMA_LECTOR", " entidad = " + entidad.getFecha());
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(MainActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}