package com.demo.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.application.R
import com.demo.application.models.Recipe
import com.demo.application.utils.loadImage
import kotlinx.android.synthetic.main.layout_recipe_list_item.view.*
import java.util.ArrayList
import kotlin.math.roundToInt

class RecipeRecyclerAdapter(var recipes : ArrayList<Recipe>)  : RecyclerView.Adapter<RecipeRecyclerAdapter.UserViewHolder>() {
    fun updateRecipies(newUsers: List<Recipe>) {
        recipes.clear()
        recipes.addAll(newUsers)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_list_item, parent, false)
    )
    override fun getItemCount() = recipes.size
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(recipes[position])
    }
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.recipe_image
        private val title = view.recipe_title
        private val publisher = view.recipe_publisher
        private val score = view.recipe_social_score
        fun bind(recipe: Recipe) {
            title.text = recipe.title
            publisher.text = recipe.publisher
            score.text = recipe.social_rank.toString()
            image.loadImage(recipe.image_url)
        }
    }
}