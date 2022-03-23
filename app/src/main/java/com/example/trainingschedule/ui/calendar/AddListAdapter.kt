package com.example.trainingschedule.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.ItemExerciseAddBinding
import com.example.trainingschedule.databinding.ItemExerciseShortBinding
import com.example.trainingschedule.databinding.ItemTrainingBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.TrainingViewModel

class AddListAdapter(
    private val onAddClicked: (exercise: Exercise) -> Unit
) : ListAdapter<Exercise, AddListAdapter.AddExerciseViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AddExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AddExerciseViewHolder(ItemExerciseAddBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AddExerciseViewHolder, position: Int) {
        val item = getItem(position)
        val diff = "Difficulty: ${item.difficulty} / 5"
        with(holder.binding) {
            root.findViewById<Button>(R.id.button_choose_exercise).setOnClickListener {
                onAddClicked(item)
            }
            exnameTextView.text = item.name
            difficultyTextView.text = diff
            descriptionTextView.text = item.description
        }
    }

    class AddExerciseViewHolder(
        val binding: ItemExerciseAddBinding
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