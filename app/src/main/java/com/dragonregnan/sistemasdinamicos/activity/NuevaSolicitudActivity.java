package com.dragonregnan.sistemasdinamicos.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dragonregnan.sistemasdinamicos.R;
import com.dragonregnan.sistemasdinamicos.dao.empresasDAO;
import com.dragonregnan.sistemasdinamicos.dao.encadenamientosDAO;
import com.dragonregnan.sistemasdinamicos.dao.solicitudesDAO;
import com.dragonregnan.sistemasdinamicos.model.solicitudesModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by laura on 03/03/2016.
 */
public class NuevaSolicitudActivity extends Activity {

    private empresasDAO empDAO;
    private encadenamientosDAO encDAO;
    private solicitudesDAO solDAO;
    private int mayoralmacen;
    private int menoralmacen;
    private String letraMayoralmacen;
    private String letraMenoralmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_solicitud);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        final int idEmpresa = pref.getInt("idEmpresa", 0);

    // INSTANCIAR DAOS
        empDAO= new empresasDAO(this);
        encDAO = new encadenamientosDAO(this);
        solDAO = new solicitudesDAO(this);

    //OBTENER ID DE INDUSTRIA DE LA EMPRESA DEL USUARIO LOGEADO

        final int idIndustria = empDAO.getIndustriaEmpresa(idEmpresa);

        ArrayList<Integer> encadenamiento =  encDAO.getIndustriasVenden(idIndustria);

    //DEFINIR LA LETRA CORREPONDIENTE A SU ENCADENAMIENTO

        if(encadenamiento.get(0).intValue() < encadenamiento.get(1).intValue()){

            menoralmacen = encadenamiento.get(0).intValue();
            mayoralmacen = encadenamiento.get(1).intValue();
        }else {
            mayoralmacen = encadenamiento.get(0).intValue();
            menoralmacen = encadenamiento.get(1).intValue();
        }

        if(menoralmacen == 1){
            letraMenoralmacen="A";
        }
        if(menoralmacen == 2){
            letraMenoralmacen="B";
        }
        if(menoralmacen == 3){
            letraMenoralmacen="C";
        }
        if(menoralmacen == 4){
            letraMenoralmacen="D";
        }
        if(menoralmacen == 5){
            letraMenoralmacen="E";
        }
        if(menoralmacen == 6){
            letraMenoralmacen="F";
        }

        if(mayoralmacen == 1){
            letraMayoralmacen="A";
        }
        if(mayoralmacen == 2){
            letraMayoralmacen="B";
        }
        if(mayoralmacen == 3){
            letraMayoralmacen="c";
        }
        if(mayoralmacen == 4){
            letraMayoralmacen="D";
        }
        if(mayoralmacen == 5){
            letraMayoralmacen="E";
        }
        if(mayoralmacen == 6){
            letraMayoralmacen="F";
        }

        final EditText edtnuevacantidad = (EditText) findViewById(R.id.edtxt_nuevacantidad);
        final EditText edtnuevafecha = (EditText) findViewById(R.id.edtxt_nuevafecha);

        final RadioButton menor = (RadioButton)findViewById(R.id.mat1);
        final RadioButton mayor = (RadioButton)findViewById(R.id.mat2);

        menor.setText("Material "+ letraMenoralmacen);
        mayor.setText("Material " + letraMayoralmacen);
    //CONTROL DE RADIO BUTTON
        menor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menor.isChecked() == false) {
                    menor.setChecked(true);
                    mayor.setChecked(false);
                } else {
                    menor.setChecked(false);
                    mayor.setChecked(true);
                }

            }
        });
        mayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mayor.isChecked()== false ){
                    menor.setChecked(false);
                    mayor.setChecked(true);
                }else{
                    menor.setChecked(true);
                    mayor.setChecked(false);

                }

            }
        });
        Button envio = (Button)findViewById(R.id.enviar_soli);
    //GUARDAR LAS NUEVA SOLICITUD
        envio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudesModel solicitud = new solicitudesModel();

                solicitud.setIdEmpresaCompradora(idEmpresa);
                solicitud.setCantSolicitada(Integer.valueOf(edtnuevacantidad.getText().toString()));
                String dia = edtnuevafecha.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = new Date( format.parse(dia).getDate());
                    solicitud.setFecEntregaSol(date);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                if(menor.isChecked()){
                    solicitud.setIdIndustria(menoralmacen);}
                else if(mayor.isChecked()){
                    solicitud.setIdIndustria(menoralmacen);}

                solDAO.insertSolicitud(solicitud);
                finish();
            }
        });
    //CANCELAR LA NUEVA SOLICITUD
        Button cancelar = (Button)findViewById(R.id.cancel);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
