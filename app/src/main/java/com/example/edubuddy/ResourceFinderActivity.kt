import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edubuddy.Resources
import com.google.firebase.database.*
import com.example.edubuddy.R

class ResourceFinderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var resourceAdapter: ResourceAdapter
    private val resourceList: MutableList<Resources> = mutableListOf()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource_finder)

        val etSubject = findViewById<EditText>(R.id.etSubject)
        val spinnerDifficulty = findViewById<Spinner>(R.id.spinnerDifficulty)
        val btnFindResources = findViewById<Button>(R.id.btnFindResources)
        recyclerView = findViewById(R.id.recyclerViewResources)

        // Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("resources")

        // Spinner setup
        val difficulties = arrayOf("Beginner", "Intermediate", "Advanced")
        spinnerDifficulty.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            difficulties
        )

        // RecyclerView setup
        resourceAdapter = ResourceAdapter(resourceList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = resourceAdapter

        btnFindResources.setOnClickListener {
            val subject = etSubject.text.toString()
            val difficulty = spinnerDifficulty.selectedItem.toString()

            if (subject.isNotEmpty()) {
                fetchResources(subject, difficulty)
            } else {
                Toast.makeText(this, "Please enter a subject", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchResources(subject: String, difficulty: String) {
        resourceList.clear()

        // Query Firebase Database
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (resourceSnapshot in snapshot.children) {
                    var resource = resourceSnapshot.getValue(Resources::class.java)
                    if (resource != null &&
                        resource.subject.equals(subject, ignoreCase = true) &&
                        resource.difficulty.equals(difficulty, ignoreCase = true)) {
                        resourceList.add(resource)

                    }
                }
                resourceAdapter.notifyDataSetChanged()

                if (resourceList.isEmpty()) {
                    Toast.makeText(
                        this@ResourceFinderActivity,
                        "No resources found for the selected criteria.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ResourceFinderActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
