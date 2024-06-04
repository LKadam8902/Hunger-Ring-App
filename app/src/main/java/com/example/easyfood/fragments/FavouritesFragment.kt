package com.example.easyfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easyfood.activities.MainActivity
import com.example.easyfood.adapters.MealsAdapter
import com.example.easyfood.databinding.FragmentFavouritesBinding
import com.example.easyfood.videoModel.HomeViewModel


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favouritesAdapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //prepareRecyclerView()
        //observeFavourites()

        /*val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favouritesAdapter.differ.currentList[position])
                Snackbar.make(requireView(), "Meal deleted", Snackbar.LENGTH_SHORT).setAction(
                    "Undo"
                ) {
                    viewModel.insertMeal(favouritesAdapter.differ.currentList[position])
                }.show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavourites)
    }*/

        /* private fun prepareRecyclerView() {
             favouritesAdapter = MealsAdapter()
             binding.rvFavourites.apply {
                 layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                 adapter = favouritesAdapter
             }
         }


         private fun observeFavourites() {
             viewModel.observeFavouritesMealsLiveData().observe(requireActivity()) { meal ->
                 favouritesAdapter.differ.submitList(meal)
             }*/
    }
}