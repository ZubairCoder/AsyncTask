package com.example.asynctask.Fragments

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asynctask.Adopter.Images
import com.example.asynctask.Adopter.RecyclerAdopter
import com.example.asynctask.databinding.FragmentSimpleBinding


class SimpleFragment : Fragment() {
    private var binding: FragmentSimpleBinding? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSimpleBinding.inflate(LayoutInflater.from(context), container, false)
        // Inflate the layout for this fragment
        recyclerView = binding?.recyclerView
        recyclerView?.layoutManager= LinearLayoutManager(context)
        recyclerView?.adapter = RecyclerAdopter(requireContext(),getImages())

        //getImages()
        return binding?.root
    }

    private fun getImages(): ArrayList<Images> {
        val images = mutableListOf<Images>()

    /*    val fil = File(Environment.getExternalStorageDirectory() , "/Android/media/com.gbwhatsapp/GBWhatsapp/Media/GBWhatsapp Images")
        if (fil.isDirectory()) {
            recyclerView?.layoutManager =
                LinearLayoutManager(ApplicationProvider.getApplicationContext<Context>())
        } else {
            Toast.makeText(
                ApplicationProvider.getApplicationContext<Context>(),
                "Nothing to show",
                Toast.LENGTH_LONG
            ).show()
        }
        return images*/


         val projection = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA)
        val selection = "${MediaStore.Images.Media.BUCKET_DISPLAY_NAME} = ?"
        val selcetionArgs = arrayOf("pics")
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val cursor = requireActivity().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selcetionArgs,
            sortOrder
        )
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val image = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                images.add(Images(title,image))
            } while (cursor.moveToNext())
            cursor.close()
        }
        return ArrayList(images)
    }
    }
