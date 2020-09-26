package com.samuel.gokapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.samuel.gokapp.databinding.FragmentHomeBinding
import com.samuel.gokapp.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: DataViewModel by viewModel()
    private val spotlightListAdapter = SpotlightListAdapter(arrayListOf())
    private val productListAdapter = ProductListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFromRemote()

        spotlightList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = spotlightListAdapter
        }

        productsList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = productListAdapter
        }

        refreshLayout.setOnRefreshListener {
            spotlightList.visibility = View.GONE
            productsList.visibility = View.GONE
            cashCardView.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.fetchFromRemote()
            refreshLayout.isRefreshing = false
        }
        observeVielModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    fun observeVielModel() {

        viewModel.data.observe(this, Observer {
            it?.let {
                spotlightList.visibility = View.VISIBLE
                productsList.visibility = View.VISIBLE
                cashCardView.visibility = View.VISIBLE
                spotlightListAdapter.updateSpotlightList(it.spotlight)
                spotlightList.smoothScrollToPosition(0)
                productListAdapter.updateProductList(it.products)
                productsList.smoothScrollToPosition(0)
                binding.cash = it.cash
            }
        })

        viewModel.dataLoadError.observe(this, Observer {
            it?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer {
            it?.let {
                if (it) {
                    loadingView.visibility = View.VISIBLE
                    listError.visibility = View.GONE
                    spotlightList.visibility = View.GONE
                } else {
                    loadingView.visibility = View.GONE
                }
            }
        })
    }

    fun removeObservers() {
        viewModel.loading.removeObservers(this)
        viewModel.dataLoadError.removeObservers(this)
        viewModel.data.removeObservers(this)
    }
}