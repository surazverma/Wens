package com.example.wens.ui.article

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.wens.R
import com.example.wens.databinding.FragmentArticleBinding
import com.example.wens.ui.home.HomeActivity

class ArticleFragment : Fragment() {

    private val args: ArticleFragmentArgs by navArgs()
    private var _binding: FragmentArticleBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        args.article.let {
            mBinding.apply {
                tvArticlePublicationName.apply {
                    text = it.source!!.name
                    transitionName = it.source.name
                }
                tvArticleTitle.apply {
                    text = it.title
                    transitionName = "${it.title}"
                }
                tvDescription.apply {
                    text = it.description
                    transitionName = it.description
                }
                ivArticleImage.apply {
                    load(it.urlToImage) {
                        error(R.drawable.ic_placeholder_)
                    }
                    transitionName = it.urlToImage
                }
            }
            toggleFullScreen(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        toggleFullScreen(false)
    }

    private fun toggleFullScreen(value: Boolean) {
        val activityHome = activity as HomeActivity
        val bottomNavView = activityHome.mBinding.bottomNav
        val topBar = activityHome.mBinding.appbarDashboard
        if (value) {
            bottomNavView.visibility = View.GONE
            topBar.mainAppBar.visibility = View.GONE
        } else {
            bottomNavView.visibility = View.VISIBLE
            topBar.mainAppBar.visibility = View.VISIBLE
        }
    }
}