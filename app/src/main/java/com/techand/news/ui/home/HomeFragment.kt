package com.techand.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techand.news.R
import com.techand.news.data.remote.ApiHelper
import com.techand.news.data.remote.RetrofitBuilder
import com.techand.news.data.model.Article
import com.techand.news.ui.base.ViewModelFactory
import com.techand.news.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_main, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())
        adapter = HomeAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        val newsKeyword: String = arguments?.getString("name").toString()
        viewModel.getNews(newsKeyword).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                            retrieveList(users)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<Article>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}