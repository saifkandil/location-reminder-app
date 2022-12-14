package com.udacity.project4.locationreminders.data.local

import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemindersLocalDataSourceTest {
    private val reminder1 = ReminderDTO("Title1", "Description1", "cairo", 30.0, 30.0)
    private val reminder2 = ReminderDTO("Title2", "Description2", "cairo", 30.0, 30.0)
    private val reminder3 = ReminderDTO("Title3", "Description3", "cairo", 30.0, 30.0)
    private val localReminders = listOf(reminder1, reminder2, reminder3)

    // Class under test
    private lateinit var remindersDataSource: FakeDataSource

    @Before
    fun createDataSource() {
        remindersDataSource = FakeDataSource(localReminders.toMutableList())
    }


    @Test
    fun getReminders_requestsAllRemindersFromLocalDataSource() = runBlockingTest {
        val reminders = remindersDataSource.getReminders() as Result.Success
        assertThat(reminders.data.toList(), IsEqual(localReminders))
    }
}