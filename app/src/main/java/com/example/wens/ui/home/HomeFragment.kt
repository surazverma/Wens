package com.example.wens.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wens.R
import com.example.wens.model.objects.Articles
import com.example.wens.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mHomeViewModel: HomeViewModel by activityViewModels()
    private lateinit var mHomeFeedAdapter: HomeFeedAdapter
    private val mListOfArticles = mutableListOf<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObservers()
    }

    private fun setObservers() {
        mHomeViewModel.news.observe(viewLifecycleOwner, {
            when (it) {
                ResultWrapper.Loading -> {
                    viewVisibilityHandler(true)
                }
                is ResultWrapper.Success -> {
                    viewVisibilityHandler(false)
                    updateRecyclerView(it.value)
                }
                is ResultWrapper.GenericError -> showError("Something Went Wrong")
                is ResultWrapper.NetworkError -> showError("Network Issue")
                is ResultWrapper.ServerError -> showError("Server Issue")
            }
        })
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


    fun hideError() {
        tvError.visibility(false)
    }

    private fun updateRecyclerView(articles: List<Articles>) {
        mHomeFeedAdapter.setData(articles)
    }

    fun setupRecyclerView() {
        mHomeFeedAdapter = context?.let { HomeFeedAdapter(it, mListOfArticles) }
            ?: throw Exception("Context Is null")

        rvHomeFeed.apply {
            adapter = mHomeFeedAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun View.visibility(value: Boolean) {

    }

    private fun refresh() {
        hideError()
        mHomeViewModel.getTopHeadlinesFromC("us")
    }

}

