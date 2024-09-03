package com.example.webanttes.presentation.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webanttes.databinding.ItemPhotoBinding
import com.example.webanttes.domain.models.Photo
import com.example.webanttes.utils.base.BaseRecyclerViewAdapter

class PhotoAdapter(
    private val onClick: (Photo)->Unit
): PagingDataAdapter<Photo, PhotoAdapter.PhotoVH>(PhotoDiffUtilCallback()) {

    inner class PhotoVH(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: Photo){
            binding.root.setOnClickListener {
                onClick.invoke(photo)
            }
            Glide.with(binding.root)
                .load(photo.photo)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        return PhotoVH(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        getItem(position)?.let{
            holder.bind(it)
        }
    }

    class PhotoDiffUtilCallback: DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}