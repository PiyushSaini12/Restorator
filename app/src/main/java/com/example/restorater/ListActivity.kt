package com.example.restorater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        restaurantsRecyclerView.layoutManager = LinearLayoutManager(this)

        val query = db.collection("restaurants").orderBy("name", Query.Direction.ASCENDING)

    }

    private inner class RestaurantviewHolder internal constructor(private val view: View) :
        RecyclerView.ViewHolder(view) {}

    private inner class RestaurantAdapter internal constructor(options: FirestoreRecyclerOptions<Restaurant>) :

            FirestoreRecyclerAdapter<Restaurant, RestaurantviewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantviewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(
            holder: RestaurantviewHolder,
            position: Int,
            model: Restaurant
        ) {
            TODO("Not yet implemented")
        }

    }

}