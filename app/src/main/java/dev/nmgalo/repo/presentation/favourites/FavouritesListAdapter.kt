package dev.nmgalo.repo.presentation.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.nmgalo.repo.databinding.FavouriteRepoItemBinding

class FavouritesListAdapter :
    ListAdapter<FavouritesUIModel, FavouritesListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        FavouriteRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: FavouriteRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(repo: FavouritesUIModel) {
            binding.root.setOnClickListener { repo.onClick() }
            binding.authorProfileImage.load(repo.avatar)
            binding.authorName.text = repo.userName
            binding.repositoryName.text = repo.repositoryName
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<FavouritesUIModel>() {
        override fun areItemsTheSame(oldItem: FavouritesUIModel, newItem: FavouritesUIModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: FavouritesUIModel, newItem: FavouritesUIModel) =
            oldItem.id == newItem.id
    }
}
