package com.dragonregnan.sistemasdinamicos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dragonregnan.sistemasdinamicos.R;
import com.dragonregnan.sistemasdinamicos.fragments.FragmentHistorial;
import com.dragonregnan.sistemasdinamicos.fragments.FragmentMisCotizaciones;
import com.dragonregnan.sistemasdinamicos.fragments.FragmentMisSolicitudes;
import com.dragonregnan.sistemasdinamicos.fragments.FragmentSolicitudes;

/**
 * Created by laura on 27/01/2016.
 */
public class MercadoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mercadoactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    // INSTANCIAR EL LAYOUT DE TABS PARA LOS FRAGMENTS

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

    // ASIGNAR UN NOMBRE A CADA TAB E INSERTAR SU FRAGMENT

        mTabHost.addTab(
                mTabHost.newTabSpec("solicitud").setIndicator(" Solicitudes", null),
                FragmentSolicitudes.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("misolicitud").setIndicator("Mis Solicitudes", null),
                FragmentMisSolicitudes.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("micotizacion").setIndicator("Mis Cotizaciones", null),
                FragmentMisCotizaciones.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("hitorial").setIndicator("Historial", null),
                FragmentHistorial.class, null);

        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {

            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_mercado) {

        } else if (id == R.id.nav_balance) {
            Intent i = new Intent(this,BalanceActivity.class);
            startActivity(i);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
