package com.example.wens.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wens.databinding.FragmentHomeBinding
import com.example.wens.databinding.HomeListItemBinding
import com.example.wens.model.objects.Articles
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mHomeViewModel: HomeViewModel by activityViewModels()
    private lateinit var mHomeFeedAdapter: HomeFeedAdapter
    private var _binding: FragmentHomeBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun handleTheWindow() {
        activity?.window?.setDecorFitsSystemWindows(true)
        val controller = view?.windowInsetsController
        controller?.setSystemBarsAppearance(
            APPEARANCE_LIGHT_STATUS_BARS,
            APPEARANCE_LIGHT_STATUS_BARS
        )
    }

    override fun onStart() {
        super.onStart()
        handleTheWindow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding.rvHomeFeed.adapter = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleTheWindow()
        setupRecyclerView()
        setObservers()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            mHomeViewModel.pagingNews.collectLatest {
                mHomeFeedAdapter.submitData(it)
            }
        }
    }

    private fun viewVisibilityHandler(isLoading: Boolean) {
        if (isLoading) {
            pbLoader.visibility = View.VISIBLE
            rvHomeFeed.visibility = View.GONE
        } else {
            pbLoader.visibility = View.GONE
            rvHomeFeed.visibility = View.VISIBLE
        }
        // TODO: 18/1/21 Handle Empty list state as well.

    }

    private fun showError(error: String) {
        pbLoader.visibility = View.GONE
        rvHomeFeed.visibility = View.GONE
        tvError.visibility = View.VISIBLE
        tvError.text = error
    }

    private fun setupRecyclerView() {
        mHomeFeedAdapter = HomeFeedAdapter { articles, listItemBinding ->
            feedItemClickListener(
                articles,
                listItemBinding
            )
        }
        rvHomeFeed.apply {
            adapter = mHomeFeedAdapter
            layoutManager = LinearLayoutManager(context)
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mHomeFeedAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        viewVisibilityHandler(true)
                    }
                    is LoadState.NotLoading -> {
                        viewVisibilityHandler(false)
                    }
                    is LoadState.Error -> {
                        viewVisibilityHandler(false)
                        val errorState = it.source.append as? LoadState.Error
                            ?: it.source.prepend as? LoadState.Error
                            ?: it.append as? LoadState.Error
                            ?: it.prepend as? LoadState.Error

                        errorState?.let { error ->
                            showError(error.error.localizedMessage!!)
                        } ?: showError("Something Went Wrong") // TODO: 16/2/21 handle it properly
                    }
                }
            }
        }



    }

    private fun feedItemClickListener(articles: Articles, binding: HomeListItemBinding) {
        val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment(articles)
        val extra = FragmentNavigatorExtras(
            binding.ivNewsFeedImage to articles.urlToImage!!,
            binding.tvArticleSnippet to articles.description!!,
            binding.tvFeedHeadline to articles.title!!,
            binding.tvPublicationName to articles.source!!.name
        )
        findNavController().navigate(action, extra)

    }

}

