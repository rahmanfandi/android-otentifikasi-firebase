package com.example.tesoten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.text.Editable
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {


    lateinit  var editTextKey: EditText
    lateinit var editTextValue :EditText
    lateinit var editTextDebug: EditText
    lateinit var texviewValue : TextView


    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText
    lateinit var textViewUID : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextKey = findViewById(R.id.editText_key)
        editTextValue = findViewById(R.id.editText_value)
        editTextDebug = findViewById(R.id.editText_debug)
        texviewValue = findViewById(R.id.textView_readResult)


        editTextUsername = findViewById(R.id.editText_userName)
        editTextPassword = findViewById(R.id.editText_password)
        textViewUID = findViewById(R.id.textView_UID)

    button_sigOut.isClickable = false
    button_sigOut.visibility = View.INVISIBLE





    }

    fun button_read(view: View) {
        var key : Editable? = editTextKey.text
        val database = FirebaseDatabase.getInstance()

        val myRef = database.getReference("$key")
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)

                texviewValue.setText(value)
                editTextDebug.setText("value is $value")
                //Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

                editTextDebug.setText("Failed to read value.  $error.toException()")
                //Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }





    fun button_write(view: View) {

        var key : Editable? = editTextKey.text
        var value :String = editTextValue.text.toString()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("$key")

        myRef.setValue("$value")
    }
}
