package com.example.zenhabit.fragments

import android.os.Bundle
import android.os.FileUtils
import android.provider.ContactsContract.Data
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.zenhabit.R
import com.example.zenhabit.classes.DataBase.UsersClass
import com.example.zenhabit.databinding.FragmentHomeBinding
import com.example.zenhabit.utilities.DataBaseUtils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btDiari.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_diaryScreen)
        }

        binding.btJardi.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_jardi)
        }

        binding.btTasques.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_tasksAndHabits_Fragment3)
        }


        loadAllUserData()


        return view
    }


    fun loadAllUserData() {
        if (DataBaseUtils.user!!.uid == null){
            Log.d("Home.kt UID", "Es null")
        }else{
            Log.d("Home.kt UID", DataBaseUtils.user!!.uid)
        }

        val docRef = DataBaseUtils.db.collection("Users").document(DataBaseUtils.user!!.uid)
        docRef.get().addOnSuccessListener { documentSnapshot ->

            DataBaseUtils.userData = documentSnapshot.toObject<UsersClass>()
            binding.btJardi.setText(DataBaseUtils.userData!!.test)
        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}