//package com.upb.littlepaw
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.ViewModelProvider
//import com.upb.littlepaw.databinding.ActivityHomeBinding
//import com.upb.littlepaw.databinding.FragmentChabotBinding
//import com.upb.littlepaw.homescreen.HomeActivity
//import com.upb.littlepaw.homescreen.HomeViewModel
//import com.upb.littlepaw.utils.replaceFragment
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//class ChatbotFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//    private lateinit var binding: FragmentChabotBinding
//    private val viewModel by lazy { ViewModelProvider(this).get(DialogflowViewModel::class.java) }
//    private val homeViewModel: HomeViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentChabotBinding.inflate(inflater, container, false)
////        binding.lifecycleOwner = this
////        binding.viewModel = viewModel
//        return binding.root
//        }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.menuButton.setOnClickListener {
//            homeViewModel.onClickMenuButton()
//        }
//    }
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ChatbotFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}

package com.upb.littlepaw

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.applozic.mobicomkit.api.account.register.RegistrationResponse
import com.upb.littlepaw.databinding.FragmentChabotBinding
import io.kommunicate.Kommunicate
import io.kommunicate.callbacks.KMLoginHandler
import io.kommunicate.callbacks.KmCallback


class ChatbotFragment : Fragment() {
    private lateinit var binding: FragmentChabotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChabotBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Kommunicate.init(requireActivity().applicationContext, "1935ffe81ecf4c46cde2aecf3843dcb30")
        Kommunicate.loginAsVisitor(requireContext(), object : KMLoginHandler {
            override fun onSuccess(registrationResponse: RegistrationResponse?, context: Context?) {
                val progressDialog = ProgressDialog(requireContext())
                progressDialog.setTitle("Logging in..")
                progressDialog.setMessage("Please wait...")
                progressDialog.setCancelable(false)
                progressDialog.show()
                Kommunicate.launchConversationWithPreChat(
                    requireActivity(),
                    progressDialog,
                    object : KmCallback {
                        override fun onSuccess(message: Any) {
                            requireActivity().finish()
                            progressDialog.dismiss()
                        }

                        override fun onFailure(error: Any) {
                            progressDialog.dismiss()
                        }
                    })
            }

            override fun onFailure(registrationResponse: RegistrationResponse?, exception: Exception?) {
                // Handle failure
            }
        })
    }
}