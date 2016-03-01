package com.dragonregnan.sistemasdinamicos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.location.Address;
import android.widget.Toast;

import com.dragonregnan.sistemasdinamicos.db.DataBaseSource;
import com.dragonregnan.sistemasdinamicos.model.comprasOperacionesModel;
import com.dragonregnan.sistemasdinamicos.model.pagosModel;

import java.util.ArrayList;

/**
 * Created by laura on 05/01/2016.
 */
public class pagosDAO {

    private Context context;
    private static DataBaseSource db;
    private ArrayList<Address> addresses;

    public static final String TABLE_PAGOS = "pagos";
    public static final String IDPAGO = "idpago";
    public static final String IDOPERACION = "idoperacion";
    public static final String CANTIDADPAGADA = "cantidadpagada";
    public static final String FECPAGO = "fecpago";

    public pagosDAO (Context context) {
        this.context = context;
        db = new DataBaseSource(context);
    }
    public int insertPago(pagosModel pago) {
        ContentValues cv = new ContentValues();

        cv.put(IDOPERACION, pago.getIdOperacion());
        cv.put(CANTIDADPAGADA, pago.getCantidadPagada());
        cv.put(FECPAGO,pago.getFecPago().getTime() );
        int id = (int) db.insert(TABLE_PAGOS, cv);

        Toast toast = Toast.makeText(context, "Pago registrado con Exito", Toast.LENGTH_LONG);
        toast.show();
        db.close();
        return id;

    }
}
