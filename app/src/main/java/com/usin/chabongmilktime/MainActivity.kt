package com.usin.chabongmilktime

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.installations.Utils
import com.google.firebase.ktx.Firebase
import com.usin.chabongmilktime.adapter.ItemAdapter
import com.usin.chabongmilktime.data.DataSource
import com.usin.chabongmilktime.databinding.ActivityMainBinding
import com.usin.chabongmilktime.utils.Const
import java.sql.Timestamp
import java.util.*


class MainActivity : AppCompatActivity() {

    // Binding object instance with access to the views in the activity_main.xml layout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FireBase authentication
        //TODO ("Create authentication? Does it necessary?")

        // Initialize data.
        val myDataSet = DataSource().loadMilkTime()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // You need to set layout manager either in the activity_main.xml
        // as  app:layoutManager="LinearLayoutManager" or in here with code
        binding.recyclerView.adapter = ItemAdapter(this, myDataSet)

        binding.imgBottleFeeding.setOnClickListener { feeding() }
        binding.imgChangingDiaper.setOnClickListener { changeDiaper() }
        binding.imgVitaminD.setOnClickListener {
            setVitaminD()
        }
    }

    private fun feeding() {
        showFullDialog()
    }

    private fun changeDiaper() {
//        val timestamp = Calendar.getInstance(TimeZone.getDefault()).timeInMillis
//
//        postFireBase(Const.PATH, timestamp, isDiaper = true)
//
//        Toast.makeText(this, getString(R.string.toast_add_diaper), Toast.LENGTH_SHORT)
//                .show()


    }

    private fun setVitaminD() {
        val timestamp = Calendar.getInstance(TimeZone.getDefault()).timeInMillis
        postFireBase(Const.PATH, timestamp, isVitamin = true)

        Toast.makeText(this, getString(R.string.toast_add_vitamin), Toast.LENGTH_SHORT)
                .show()
    }

    private fun showFullDialog() {
        //TODO ("Might be consider binding here as well, might not good?")
        //TODO ("Try this: https://developer.android.com/guide/topics/ui/dialogs#CustomLayout")

        //Inflate the dialog with custom view
        val dialogView = LayoutInflater.from(this)
                .inflate(R.layout.popup_window, null)

        val builder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle(getString(R.string.dialog_milk_record_title))

        val alertDialog = builder.show()
        val btnOk: Button = dialogView.findViewById(R.id.btnOk)
        val btnCancel: Button = dialogView.findViewById(R.id.btnCancel)
        val edtTime: EditText = dialogView.findViewById(R.id.edtTime)
        val edtAmount: EditText = dialogView.findViewById(R.id.edtMilkAmount)
        val chkDiaper: CheckBox = dialogView.findViewById(R.id.chkDiaperChange)
        val chkVitamin: CheckBox = dialogView.findViewById(R.id.chkVitaminD)

        val timestamp = Calendar.getInstance(TimeZone.getDefault()).timeInMillis

        edtTime.setText(Const.shortTimeFormat.format(timestamp))
        btnOk.setOnClickListener {
            val message: String = edtTime.text.toString()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val milkAmount = edtAmount.text.toString().toIntOrNull() ?: 0

            postFireBase(Const.PATH, timestamp, milkAmount, chkDiaper.isChecked,
                    chkVitamin.isChecked)

            alertDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun postFireBase(path: String, timestamp: Long, amount: Int = 0,
                             isDiaper: Boolean = false, isVitamin: Boolean = false) {

        val database = Firebase.database
        val myRef = database.getReference("""$path$timestamp""")

        val map: HashMap<String, Any> = hashMapOf(
                Const.TIME to timestamp,
                Const.AMOUNT to amount,
                Const.DIAPER to isDiaper,
                Const.VITAMIN to isVitamin
        )

        myRef.setValue(map)
    }
}







