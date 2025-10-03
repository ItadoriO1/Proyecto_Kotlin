package com.example.myapplication.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class NotificationViewModel: ViewModel() {

    private val _notifications = MutableStateFlow(emptyList<Notification>())

    val notification: StateFlow<List<Notification>> = _notifications.asStateFlow();

    init {
        loadNotifications();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadNotifications(){
        _notifications.value = listOf(
            Notification(
                id = "1",
                placeId = "1",
                comment = "Tu lugar esta a la espera de revision",
                date = LocalDate.now()
            )
        )
    }

    fun create(notification: Notification){
        _notifications.value = _notifications.value.toMutableList()
            .apply { add(notification) }
    }

    fun update(comment: String?, id: String){
        val index = _notifications.value.indexOfFirst { it.id == id }
        if(index != -1){
            val updateList = _notifications.value.toMutableList()
            updateList[index] = updateList[index].copy(comment = comment)
            _notifications.value = updateList
        }
    }

    fun delete(notification: Notification){
        _notifications.value = _notifications.value - notification
    }

    fun getByIdPlace(id: String): Notification? {
        return _notifications.value.find { it.id == id }
    }

    fun getAll(): List<Notification> {
        return _notifications.value
    }
}