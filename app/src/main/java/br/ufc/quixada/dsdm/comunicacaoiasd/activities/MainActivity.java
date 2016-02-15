package br.ufc.quixada.dsdm.comunicacaoiasd.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.AgendaFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.ContatosFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.EnderecosFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.ItinerarioFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.MainFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.NoticiasFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.fragments.VideoFragment;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Endereco;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.Igreja;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.SearchResult;
import br.ufc.quixada.dsdm.comunicacaoiasd.utils.ConvertJsonObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LocationListener {

    private NavigationView navigationView = null;
    private Toolbar toolbar = null;
    private DrawerLayout drawer;
    private Location currentLocation;
    private LocationManager locationManager;
    private String locationProvider;

    private String dadosTeste;

    SearchResult searchResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("IASD Info");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        searchResult = intent.getParcelableExtra("RESULT");
        InitializeLocationManager();


        List<Igreja> igrejas = new ArrayList<Igreja>();
        for (int i = 0; i < 10; i++) {
            Endereco e = new Endereco();
            e.setBairro("Bairro " + i);
            e.setEndereco("Rua " + i);
            e.setId(i);
            double[] a1 = {i, i * 2};
            e.setLocation(a1);
            Igreja ig = new Igreja(e, "Igreja teste " + i);
            igrejas.add(ig);
        }


        dadosTeste = new Gson().toJson(igrejas).toString();


    }

    void InitializeLocationManager() {
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
        } else {
            //showGPSDisabledAlertToUser();
        }

        Criteria criteriaForLocationService = new Criteria();
        criteriaForLocationService.setAccuracy(Criteria.ACCURACY_COARSE);
        List<String> acceptableLocationProviders = this.locationManager.getProviders(criteriaForLocationService, true);

        if (!acceptableLocationProviders.isEmpty()) {
            this.locationProvider = acceptableLocationProviders.get(0);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            this.locationManager.requestLocationUpdates(this.locationProvider, 0, 0, this);
        } else {
            this.locationProvider = "";
        }
        Log.d("LOCATION", "Using " + this.locationProvider + ".");
    }

    void atualizaLocation()
    {

    }


    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_agenda) {
            AgendaFragment fragment = new AgendaFragment(this);
            toolbar.setTitle("Evento");
            transaction.replace(R.id.fragment_container, fragment);
            //transaction.commit();
        } else if (id == R.id.nav_itinerario) {
            ItinerarioFragment fragment = new ItinerarioFragment(this);
            toolbar.setTitle("Itinerario Pastoral");
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            //transaction.commit();
        } else if (id == R.id.nav_enderecos) {
            EnderecosFragment fragment = new EnderecosFragment(this);
            Bundle arg = new Bundle();

            double[] coordenadas = {this.currentLocation.getLatitude(), this.currentLocation.getLongitude()};
            arg.putDoubleArray("coordenadas", coordenadas);
            arg.putString("igrejas",this.dadosTeste);
            fragment.setArguments(arg);

            toolbar.setTitle("Endereços");
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            //transaction.commit();

        } else if (id == R.id.nav_videos) {
            VideoFragment fragment = new VideoFragment(this, searchResult);
            toolbar.setTitle("Videos Novo Tempo");
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            //transaction.commit();
        } else if (id == R.id.nav_contatos) {
            ContatosFragment fragment = new ContatosFragment(this);
            toolbar.setTitle("Contatos");
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
        }
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        transaction.commit();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.locationManager.requestLocationUpdates(this.locationProvider, 0, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        this.currentLocation = location;
        if (this.currentLocation == null)
        {
            Context context = getApplicationContext();
            CharSequence text = "Unable to determine your location. Try again in a short while";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else
        {
            Toast.makeText(getApplicationContext(), "POSICAO ATUALIZADA", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}