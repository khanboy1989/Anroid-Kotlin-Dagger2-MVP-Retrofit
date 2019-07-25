package net.cocooncreations.dimvparchitecture.ui.modules.post

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_post.view.*
import net.cocooncreations.dimvparchitecture.R
import net.cocooncreations.dimvparchitecture.ui.models.Post

class PostListAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<Post>()

    fun updateData(newList:List<Post>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        when(holder){
            is ParentPostsListViewHolder -> holder.bind(items[p1])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ParentPostsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_post,parent,false))
    }

    inner class ParentPostsListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(item:Post){
            itemView.postTitleTV.text = item.title
            itemView.postBodyTV.text = item.body
        }

    }
}