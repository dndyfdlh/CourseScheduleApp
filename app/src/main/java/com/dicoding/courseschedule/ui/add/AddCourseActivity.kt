package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.databinding.ActivityAddCourseBinding
import com.dicoding.courseschedule.ui.list.ListViewModelFactory
import com.dicoding.courseschedule.util.DayName
import com.dicoding.courseschedule.util.TimePickerFragment

class AddCourseActivity : AppCompatActivity() {
    enum class AllButton{
        TimeStartBtn, TimeEndBtn
    }
    private var checkBtn = AllButton.TimeStartBtn
    private var startTime = ""
    private var endTime = ""

    lateinit var binding: ActivityAddCourseBinding

    private lateinit var viewModel: AddCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openDialogStart.setOnClickListener {
            showDatePicker(AllButton.TimeStartBtn)
        }
        binding.openDialogEnd.setOnClickListener {
            showDatePicker(AllButton.TimeEndBtn)
        }

        val factory = ListViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory).get(AddCourseViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_insert -> {
                viewModel.insertCourse(binding.courseName.text.toString(), DayName.getByName(binding.day.selectedItem.toString()), startTime, endTime, binding.Lecture.text.toString(), binding.note.text.toString())
                this.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDatePicker(btn: AllButton) {
        checkBtn = btn
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")

        dialogFragment.setListener(object : TimePickerFragment.DialogTimeListener {
            override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
                if(checkBtn == AllButton.TimeStartBtn){
                    startTime = String.format("%02d:%02d",hour, minute)
                    binding.startTime.text = startTime
                }
                else{
                    endTime = String.format("%02d:%02d",hour, minute)
                    binding.endTime.text = endTime
                }
            }
        })
    }
}