package dev.nmgalo.repo.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dev.nmgalo.repo.databinding.SearchRepoItemBinding
import dev.nmgalo.repo.presentation.repo.Repo


class RepoListAdapter : ListAdapter<Repo, RepoListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        SearchRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        private val binding: SearchRepoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(repo: Repo) {
            binding.root.setOnClickListener { repo.onClick() }
            binding.repositoryName.text = repo.repositoryName
            binding.avatar.load(repo.avatar) {
                transformations(CircleCropTransformation())
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
    }
}
