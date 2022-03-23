package com.example.trainingschedule.ui.calendar

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.FragmentCalendarBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.Training
import com.example.trainingschedule.ui.training.shortexercises.ShortExercisesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


class CalendarFragment : Fragment() {

    private val args by navArgs<CalendarFragmentArgs>()
    private val calendarViewModel by viewModel<CalendarViewModel> {
        parametersOf(args.list)
    }

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        var showDate: String = ""

        binding.root.findViewById<Button>(R.id.button_show_calender).setOnClickListener {
            val dpd = DatePickerDialog (this.requireContext(), DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                showDate = " $selectedDay / ${selectedMonth+1} / $selectedYear"
                binding.root.findViewById<TextView>(R.id.text_calendar_date).text = showDate
            }, year, month, day)
            dpd.show()
        }


        binding.recyclerViewCalendar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ShortExercisesListAdapter(
            ).also { adapter ->
                calendarViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }

        binding.root.findViewById<Button>(R.id.button_add_exercises).setOnClickListener {
            onAddClicked()
        }

        binding.root.findViewById<Button>(R.id.button_calendar_save).setOnClickListener {
            onSaveClicked(showDate)
        }

        val textView: TextView = binding.textCalendarDate
        textView.text=getString(R.string.text_choose_date)

        return root
    }

    private fun onAddClicked() {
        findNavController().navigate(
            CalendarFragmentDirections.actionNavigationCalendarToNavigationAdd(calendarViewModel.list)
        )
    }

    private fun onSaveClicked(date:String) {

        val training = Training(calendarViewModel.getNextId().toLong(),date,calendarViewModel.list.toList())

        calendarViewModel.sourceTraining.addTraining(training)
        findNavController().navigate(
            CalendarFragmentDirections.actionNavigationCalendarToNavigationHome()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}