package com.example.trainingschedule.ui.training

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.ItemTrainingBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.exercises.ExerciseListAdapter
import com.example.trainingschedule.ui.training.TrainingViewModel
import com.example.trainingschedule.ui.training.shortexercises.ShortExercisesListAdapter

class TrainingListAdapter(
    private val onShortExerciseClicked: (id: Long)-> Unit
): ListAdapter<Training, TrainingListAdapter.TrainingViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrainingViewHolder(ItemTrainingBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            dateAddedTextView.text = "${item.dateAdded}"
            root.findViewById<TextView>(R.id.choose_exercise_text_view).setOnClickListener {
                onShortExerciseClicked(item.trainingId)
            }
        }
    }


    class TrainingViewHolder(
        val binding: ItemTrainingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object Diff : DiffUtil.ItemCallback<Training>() {
        override fun areItemsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem.trainingId == newItem.trainingId
        }

        override fun areContentsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem == newItem
        }
    }
}