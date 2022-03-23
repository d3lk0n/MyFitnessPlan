package com.example.trainingschedule.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.FragmentHomeBinding
import com.example.trainingschedule.ui.calendar.CalendarFragmentArgs
import com.example.trainingschedule.ui.calendar.CalendarViewModel
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.exercises.ExercisesFragmentDirections
import org.json.JSONObject.NULL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.root.findViewById<Button>(R.id.button_exercises).setOnClickListener {
            onExercisesClicked()
        }

        binding.root.findViewById<Button>(R.id.button_training).setOnClickListener {
            onTrainingClicked()
        }

        binding.root.findViewById<Button>(R.id.button_calendar).setOnClickListener {
            onCalendarClicked()
        }

        binding.root.findViewById<Button>(R.id.button_settings).setOnClickListener {
            onSettingsClicked()
        }

        loadSettings()

        return root
    }

    private fun onExercisesClicked() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationExercises()
        )
    }

    private fun onTrainingClicked() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationTraining()
        )
    }
    private fun onCalendarClicked() {
        val exercise = homeViewModel.source.createEmptyExercise(0)
        val list: List<Exercise> = mutableListOf(exercise)
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationCalendar(list.toTypedArray())
        )
    }

    private fun onSettingsClicked() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationSettings()
        )
    }

    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val nightmodeEnabled = sp.getBoolean("nightmode", true)
        if (nightmodeEnabled)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val textView: TextView = binding.root.findViewById(R.id.text_home)
        when (sp.getString("font", "lato")) {
            "lato" -> textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.lato_black)
            "bebos" -> textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.bebas_neue_regular)
            else -> textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.open_sans)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}