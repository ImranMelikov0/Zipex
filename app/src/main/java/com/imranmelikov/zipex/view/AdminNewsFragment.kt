package com.imranmelikov.zipex.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding
import com.imranmelikov.zipex.mvvm.NewsViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminNewsFragment : Fragment() {
    private lateinit var binding:FragmentAdminNewsBinding
    private lateinit var viewModel:NewsViewModel
    var selectedimage: Uri?=null
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var permissionLauncher:ActivityResultLauncher<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentAdminNewsBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(AdminNewsFragmentDirections.actionAdminNewsFragmentToAdminFragment())
            viewModel.resetErrorMessage()
            selectedimage=null
        }
        imageClick()
        registerLauncher()
        observeNews()
        binding.button2.setOnClickListener {
            viewModel.makeNews(binding.editTextText.text.toString()
                ,binding.editTextText2.text.toString()
                ,selectedimage)
        }

        arguments?.let {
            if (AdminNewsFragmentArgs.fromBundle(it).admin.equals("string")){
                findNavController().navigate(AdminNewsFragmentDirections.actionAdminNewsFragmentToAdminFragment())
            }else{

            }
        }
        return binding.root
    }
    private fun imageClick(){
        binding.imageView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_MEDIA_IMAGES)!=PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(binding.root,"Permission needed",Snackbar.LENGTH_INDEFINITE).setAction("Permission needed",View.OnClickListener {
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }).show()
                }else{
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }else{
                var intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }
    private fun registerLauncher(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
                if (result.resultCode==RESULT_OK){
                    var intentFromResult=result.data
                    if (intentFromResult!=null){
                        selectedimage= intentFromResult.data
                        selectedimage.let {
                            binding.imageView.setImageURI(it)
                        }
                    }
                }
            }
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){
                if(it){
                    var intentToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                }else{
                    Toast.makeText(requireContext(),"Permission needed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun observeNews(){
        viewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                    Toast.makeText(requireContext(),it.message?:"Enter Values",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    viewModel.resetErrorMessage()
                    selectedimage=null
                    findNavController().popBackStack()
                }
            }
        })
    }

}