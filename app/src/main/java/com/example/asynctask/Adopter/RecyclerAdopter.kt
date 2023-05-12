package com.example.asynctask.Adopter

import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.asynctask.databinding.RecyclerLayoutBinding
import java.io.File

class RecyclerAdopter(var context: Context, val data:ArrayList<Images>): RecyclerView.Adapter<RecyclerAdopter.MyViewHolder>(){
    class MyViewHolder(var binding: RecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RecyclerLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data.get(position)
        holder.binding.title.text = data.title

        val path = File(data.url)

        Glide.with(context).load(path.absolutePath).thumbnail(0.1f).centerCrop().into(holder.binding.imageView)

        //Toast.makeText(context, "Path : ${data.url.toString()}", Toast.LENGTH_SHORT).show()
    }
}