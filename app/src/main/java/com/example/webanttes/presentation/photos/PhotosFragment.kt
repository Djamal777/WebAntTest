package com.example.webanttes.presentation.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.webanttes.R
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.databinding.FragmentPhotosBinding
import com.example.webanttes.presentation.MainActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PhotoAdapter
    private val viewModel by viewModels<PhotosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        setObservers()
        lifecycleScope.launch {
            viewModel.orderBy.emit(OrderBy.latest)
            viewModel.query.emit("")
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.photos.collectLatest {
                    it?.let { photoData ->
                        adapter.submitData(photoData)
                    }
                }
            }
            launch {
                adapter.loadStateFlow.collectLatest {
                    binding.llLoading.isVisible =
                        it.refresh is LoadState.Loading
                    binding.rvPhotos.isVisible = it.refresh is LoadState.NotLoading
                    binding.llLErrorEmpty.isVisible =
                        it.refresh is LoadState.Error || (it.source.refresh is LoadState.NotLoading &&
                                it.append.endOfPaginationReached &&
                                adapter.itemCount < 1)
                    binding.progressBottom.isVisible = it.append is LoadState.Loading
                }
            }
        }
    }

    private fun setUI() {
        binding.apply {
            (activity as? MainActivity)?.setBottomNavVisibility(true)
            adapter = PhotoAdapter {
                val action = PhotosFragmentDirections.actionNavigationHomeToPhotoDetailsFragment(it)
                findNavController().navigate(action)
            }
            rvPhotos.adapter = adapter
            rvPhotos.itemAnimator = null
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.query.emit(query.toString())
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
            tlContent.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.text) {
                        "New" -> {
                            lifecycleScope.launch { viewModel.orderBy.emit(OrderBy.latest) }
                        }

                        "Popular" -> {
                            lifecycleScope.launch { viewModel.orderBy.emit(OrderBy.popular) }
                        }
                    }
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {}

                override fun onTabReselected(p0: TabLayout.Tab?) {}
            })
            swipeRefresh.setOnRefreshListener {
                lifecycleScope.launch {
                    viewModel.query.emit(searchView.query.toString())
                    swipeRefresh.isRefreshing = false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}