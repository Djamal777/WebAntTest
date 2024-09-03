package com.example.webanttes.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.webanttes.databinding.FragmentPhotoDetailsBinding
import com.example.webanttes.domain.models.Photo
import com.example.webanttes.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            (activity as? MainActivity)?.setBottomNavVisibility(false)
            toolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
            (arguments?.getSerializable(KEY_PHOTO) as? Photo)?.let {photo->
                Glide.with(requireContext())
                    .load(photo.photo)
                    .into(ivPhoto)
                if(photo.name?.isNotEmpty() == true) {
                    tvName.text = photo.name
                }
                tvUserName.text = photo.userName
                photo.createdAt?.let { createdAt ->
                    tvDate.text = "${createdAt.get(Calendar.DAY_OF_MONTH)}.${
                        createdAt.get(Calendar.MONTH) + 1
                    }.${createdAt.get(Calendar.YEAR)}"
                }
                if(photo.description?.isNotEmpty() == true) {
                    tvDescription.text = photo.description
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_PHOTO = "KEY_PHOTO"
    }
}