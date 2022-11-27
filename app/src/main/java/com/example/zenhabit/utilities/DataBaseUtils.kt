package com.example.zenhabit.utilities

import android.annotation.SuppressLint
import com.example.zenhabit.classes.DataBase.usersclass.UsersClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class DataBaseUtils {


    companion object {
        @SuppressLint("StaticFieldLeak")
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        var userData: UsersClass? = null


        fun loadNewUserTask(taskID: String, data: String) {


            //GET
            val docRef = checkTaskProfile(taskID)
            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                userData = documentSnapshot.toObject<UsersClass>()

                //SET
                if (userData != null) {
                    userData!!.data = data
                    val documentPath = checkUserTaskProfile(taskID)

                    documentPath?.collection(taskID)?.document(taskID)?.set(userData!!)


                }
            }
        }


        fun checkUserTaskProfile(taskID: String): DocumentReference? {
            var documentReference: DocumentReference? = null
            if (taskID.contains("TS")) {
                documentReference =
                    db.collection("Users").document(user!!.uid).collection("Tasques")
                        .document("Salut")
            } else if (taskID.contains("TP")) {
                documentReference =
                    db.collection("Users").document(user!!.uid).collection("Tasques")
                        .document("Productivitat")
            } else if (taskID.contains("TA")) {
                documentReference =
                    db.collection("Users").document(user!!.uid).collection("Tasques")
                        .document("Aprenentatge")
            }
            return documentReference
        }

        fun checkTaskProfile(taskID: String): DocumentReference? {
            var documentReference: DocumentReference? = null
            if (taskID.contains("TS")) {
                documentReference = db.collection("/Perfils/Salut/Tasques").document(taskID)
            } else if (taskID.contains("TP")) {
                documentReference = db.collection("/Perfils/Productivitat/Tasques").document(taskID)
            } else if (taskID.contains("TA")) {
                documentReference = db.collection("/Perfils/Aprenentatge/Tasques").document(taskID)
            }
            return documentReference
        }

    }


}