package com.app.zenhabit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.zenhabit.R
import com.app.zenhabit.databinding.ActivityDataBaseTestingBinding


class DataBaseTesting : AppCompatActivity() {
    private lateinit var bin: ActivityDataBaseTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_testing)
        bin =   ActivityDataBaseTestingBinding.inflate(layoutInflater)



        val btTest = bin.btTest

        btTest.setOnClickListener{
            var testInput = bin.inputTestDatabase2.text.toString()
            var dateInput = bin.editTextDate2.text.toString()
            //DataBaseUtils.loadAllUserData(testInput,dateInput)
        }





    }
}