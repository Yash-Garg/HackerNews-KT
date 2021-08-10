package hack.er.news.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hack.er.news.R
import hack.er.news.models.Article
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

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
        val item = articles[position]
        holder.titleView.text = item.title
        holder.userName.text = item.user ?: "Unknown"

        ("Posted on " + SimpleDateFormat(
            "dd MMMM yyyy - hh:mm a",
            Locale.ENGLISH
        ).format(item.time * 1000L)).also { holder.timeAgo.text = it }

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            holder.itemView.context.startActivity(intent)
        }

        holder.commentsButton.setOnClickListener {
            val commentsURL = "https://news.ycombinator.com/item?id=${item.id}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(commentsURL))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = articles.size
}
