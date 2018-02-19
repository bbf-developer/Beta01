package com.project.ignacio_rvf_bbf.bbf_reporter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_planta) {
            setTitle("Proxima Planta");

            //SE DESPLAZA PARA NUEVO SUB MENU
            ProximaPlantaFragment rpf = new ProximaPlantaFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();

        } else if (id == R.id.nav_caldera) {
            setTitle("Seleccionar Cliente");
            ShowClienteFragment rpf = new ShowClienteFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();
        } else if (id == R.id.nav_piping) {
            setTitle("Reportar Piping");
            RepipingFrag rpf = new RepipingFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();

        } else if (id == R.id.nav_turbina) {
            setTitle("Reportar Turbina");
            RepturbinaFrag rpf = new RepturbinaFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();

        } else if (id == R.id.nav_estanque) {
            setTitle("Reportar Estanque");
            RepestanqueFrag rpf = new RepestanqueFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();

        } else if (id == R.id.nav_continue) {
            //DIRIGE A UN LISTVIEW CON LA TABLA QUE SE ESTA TRABAJANDO
            setTitle("Continuar Matriz");
            ContinueFragment rpf = new ContinueFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();

        } else if (id == R.id.nav_planificacion) {


        } else if (id == R.id.nav_actividad) {

        } else if (id == R.id.nav_consulta) {

        } else if (id == R.id.nav_logout) {
            //SESION DESTROY FIREBASE LOGOUT CODE HERE

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
