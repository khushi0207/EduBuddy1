import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edubuddy.R
import com.example.edubuddy.Resources


data class Resource(val title: String, val description: String, val url: String)

class ResourceAdapter(private val resourceList: MutableList<Resources>) :
    RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resource, parent, false)
        return ResourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val resource = resourceList[position]
        holder.tvTitle.text = resource.title
        holder.tvDescription.text = resource.description
        holder.btnOpen.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resource.url))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = resourceList.size

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvResourceTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvResourceDescription)
        val btnOpen: Button = itemView.findViewById(R.id.btnOpenResource)
    }
}
