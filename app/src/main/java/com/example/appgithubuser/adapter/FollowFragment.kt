package com.example.appgithubuser.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithubuser.databinding.FragmentFollowBinding
import com.example.appgithubuser.ui.detail.DetailViewModel

class FollowFragment : Fragment(){
    var position: Int = 0
    var username: String = " "

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        if (position == 0){

            detailViewModel.listFollower.observe(requireActivity()){
                binding.rvFollow.adapter = UserAdapter(it)
                Log.d("text", "linear: ${it.toString()}")
            }
        } else {
            detailViewModel.listFollowing.observe(requireActivity()){
                binding.rvFollow.adapter = UserAdapter(it)
                Log.d("text", "linear: ${it.toString()}")
            }
        }

        detailViewModel.isLoadingFollow.observe(requireActivity()){
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.pbFollow.visibility = View.VISIBLE
        }else{
            binding.pbFollow.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}