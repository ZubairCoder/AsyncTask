package com.example.asynctask.Fragments

import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asynctask.Adopter.Images
import com.example.asynctask.Adopter.RecyclerAdopter
import com.example.asynctask.databinding.FragmentAsyncBinding


class AsyncFragment : Fragment() {
    private var binding : FragmentAsyncBinding? = null
    private var recyclerView: RecyclerView? = null
    val images = mutableListOf<Images>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAsyncBinding.inflate(LayoutInflater.from(context),container,false)
        // Inflate the layout for this fragment

        recyclerView = binding?.recyclerView
        recyclerView?.layoutManager= LinearLayoutManager(context)
        recyclerView?.adapter = RecyclerAdopter(requireContext(), images as ArrayList<Images>)

        getImages().execute()

        return binding?.root
    }

    inner class getImages: AsyncTask<Void, Void, ArrayList<Images>>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg p0: Void?):  ArrayList<Images> {


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


        override fun onPostExecute(result: ArrayList<Images>) {
            super.onPostExecute(result)
        }

    }

}