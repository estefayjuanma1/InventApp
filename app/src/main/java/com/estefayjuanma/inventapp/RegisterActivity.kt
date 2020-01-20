package com.estefayjuanma.inventapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.estefayjuanma.inventapp.utils.Constantes.Companion.EMPTY
import com.estefayjuanma.inventapp.utils.Constantes.Companion.SPACE
import com.estefayjuanma.inventapp.utils.Constantes.Companion.interlineado
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var cal= Calendar.getInstance()
    private lateinit var fecha : String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var sexo = "Masculino"

        rb_femenino.setOnClickListener {
            sexo = "Masculino"
        }
        rb_masculino.setOnClickListener {
            sexo = "Femenino"
        }

        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale. US)
                fecha = sdf.format(cal.time).toString()
                Log.d("Fecha", fecha)
                bt_showPicker.text= fecha //para poner nombre en el boton

            }

        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.Ciudades,
            android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_ciudad.adapter = adapter

        bt_showPicker.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }


        bt_registrar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val contra = et_contra.text.toString()
            val concontra = et_repcontra.text.toString()
            var pasatiempos = EMPTY


            if (cb_cine.isChecked) pasatiempos = pasatiempos + SPACE + "cine" //cb_cine.text
            if (cb_gimnasio.isChecked) pasatiempos =
                pasatiempos + SPACE + "gimansio" //getString(R.string.gimnasio)
            if (cb_leer.isChecked) pasatiempos = pasatiempos + SPACE + "lectura"
            if (cb_series.isChecked) pasatiempos = pasatiempos + SPACE + "series"


            /*   if (rb_masculino.isChecked) sexo= "Masculino"
               else sexo= "Femenino" */



            if(contra!=concontra){
                Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show()
            }else{


                if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contra.isEmpty() || concontra.isEmpty()) {
                    Toast.makeText(this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()

                } else {
                    tv_resultado.text =
                        getString(R.string.Nombre_lb) + SPACE + nombre + interlineado + "Correo: " + correo +
                                "\nTelefono: " + telefono + "\nContraseña" + contra + "\nSexo: " + sexo +
                                "\nPasatiempos: " + pasatiempos + "\nFecha:" + fecha + "\nCiudad de nacimiento: " + sp_ciudad.selectedItem.toString()
                }
            }
        }
    }
}


