package com.techand.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.techand.news.R
import com.techand.news.data.model.Article
import com.techand.news.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
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
        setupUI()
        setupObservers()
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())
        adapter = HomeAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        val newsKeyword: String = arguments?.getString("name").toString()
        viewModel.getNews(newsKeyword).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        it.data?.let { users ->
                            retrieveList(users)
                        }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progressBar.visibility = View.VISIBLE
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