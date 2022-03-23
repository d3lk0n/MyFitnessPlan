package com.example.trainingschedule.ui.training.shortexercises

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.ItemExerciseShortBinding
import com.example.trainingschedule.databinding.ItemTrainingBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.TrainingViewModel

class ShortExercisesListAdapter() : ListAdapter<Exercise, ShortExercisesListAdapter.ShortExerciseViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ShortExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ShortExerciseViewHolder(ItemExerciseShortBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ShortExerciseViewHolder, position: Int) {
        val item = getItem(position)
        val diff = "Difficulty: ${item.difficulty} / 5"
        with(holder.binding) {
            exnameTextView.text = item.name
            difficultyTextView.text = diff
            descriptionTextView.text = item.description
        }
    }

    class ShortExerciseViewHolder(
        val binding: ItemExerciseShortBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }
}