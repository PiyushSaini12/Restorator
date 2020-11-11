package com.example.restorater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_restaurant.view.*

class ListActivity : AppCompatActivity() {
    private var adapter: RestaurantAdapter? = null
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        restaurantsRecyclerView.layoutManager = LinearLayoutManager(this)

        val query = db.collection("restaurants").orderBy("name", Query.Direction.ASCENDING)

        val options = FirestoreRecyclerOptions.Builder<Restaurant>().setQuery(query, Restaurant::class.java).build()

        adapter = RestaurantAdapter(options)
        restaurantsRecyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if(adapter != null) {
            adapter!!.stopListening()
        }
    }

    private inner class RestaurantviewHolder internal constructor(private val view: View) :
        RecyclerView.ViewHolder(view) {}

    private inner class RestaurantAdapter internal constructor(options: FirestoreRecyclerOptions<Restaurant>) :

            FirestoreRecyclerAdapter<Restaurant, RestaurantviewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantviewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
            return RestaurantviewHolder(view)
        }

        override fun onBindViewHolder(
            holder: RestaurantviewHolder,
            position: Int,
            model: Restaurant
        ) {
            holder.itemView.nameTextView.text = model.name
            holder.itemView.ratingBar.rating = model.rating!!.toFloat()


        }

    }

}