package hack.er.news.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hack.er.news.R
import hack.er.news.models.Article

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.item_title)
        val userName: TextView = view.findViewById(R.id.user_name)
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
        holder.userName.text = item.user
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = articles.size
}
