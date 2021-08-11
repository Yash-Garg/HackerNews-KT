package hack.er.news.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hack.er.news.R
import hack.er.news.model.Article
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter :
    PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleComparator) {

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.item_title)
        val userName: TextView = view.findViewById(R.id.user_name)
        val timeAgo: TextView = view.findViewById(R.id.time_ago)
        val commentsButton: Button = view.findViewById(R.id.comments_button)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_article, parent, false)
        return ArticleViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.titleView.text = article.title
            holder.userName.text = article.user ?: "Unknown"

            ("Posted on " + SimpleDateFormat(
                "dd MMMM yyyy - hh:mm a",
                Locale.ENGLISH
            ).format(article.time * 1000L)).also { holder.timeAgo.text = it }

            holder.itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                holder.itemView.context.startActivity(intent)
            }

            holder.commentsButton.setOnClickListener {
                val commentsURL = "https://news.ycombinator.com/item?id=${article.id}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(commentsURL))
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
