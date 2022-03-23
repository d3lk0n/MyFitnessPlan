package com.example.trainingschedule.ui.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.ItemExerciseBinding
import kotlin.reflect.KFunction0
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintSet


class ExerciseListAdapter(
    private val onEditClicked: (exercise: Exercise) -> Unit,
    private val onDeleteClicked: (id: Long) -> Unit
) : ListAdapter<Exercise, ExerciseListAdapter.ExerciseViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExerciseViewHolder(ItemExerciseBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = getItem(position)
        val diff = "Difficulty: ${item.difficulty} / 5"

        with(holder.binding) {
                root.findViewById<Button>(R.id.button_edit).setOnClickListener {
                    onEditClicked(item)
                }
                root.findViewById<Button>(R.id.button_delete).setOnClickListener {
                    onDeleteClicked(item.id)
                }
                exnameTextView.text = item.name
                difficultyTextView.text = diff
                descriptionTextView.text = item.description
            }
        }


    class ExerciseViewHolder(
        val binding: ItemExerciseBinding
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