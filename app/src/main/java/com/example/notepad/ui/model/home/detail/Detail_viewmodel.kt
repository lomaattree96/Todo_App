package com.example.notepad.ui.model.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.example.notepad.data.room.Tododatasource
import com.example.notepad.data.todo
import com.example.notepad.graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class Detail_viewmodel(
    private val tododatasource: Tododatasource = graph.todorepo,
    private val id: Long
) : ViewModel() //inherit from viewmodel
{
    //create a stage and call for required text field
    private val todoText = MutableStateFlow("")
    private val todoTime = MutableStateFlow("")
    private val selectId = MutableStateFlow(-1L)

    //create state
    private val _state = MutableStateFlow(Detailviewscreen())
    val state: StateFlow<Detailviewscreen>
        get() = _state


    //initialise to get our states
    init {
        viewModelScope.launch { //to call the suspend function
            combine(todoText, todoTime, selectId) { text, time, id ->
                Detailviewscreen(text, time, id)

            }.collect {
                _state.value = it
            }

        }

    }

    //selected index has been matched to that database which we have in our database so that we can return the database -- to update the selected index
    init {
        viewModelScope.launch {
            tododatasource.selectAll.collect { todo ->
                //to provide a value to match with this
                todo.find {
                    it.id == selectId.value
                }.also {
                    //to access this todo_and reinitialise the selected
                    selectId.value = it?.id ?: -1
                    if (selectId.value != -1L) {
                        //not equal to -1 so our values can be updated
                        todoText.value = it?.todo ?: ""
                        todoTime.value = it?.time ?: ""

                    }

                }
            }
        }
    }


//to create the events we can be called inside the composable function for updation

    fun onTextChange(newText: String) {
        todoText.value = newText
    }

    fun onTimeChange(newTime: String) {
        todoTime.value = newTime
    }

    fun insert(todo: todo) = viewModelScope.launch {
        tododatasource.inserttodo(todo)
    }

    fun delete(todo: todo) = viewModelScope.launch {
        tododatasource.deletetodo(todo)
    }

}
//to access the id --- factory method is used which takes the data and pass it to viewmodel


//to create detail screen

data class Detailviewscreen(
    val todo: String = "",
    val time: String = "",
    val selectId: Long = -1L
)


@Suppress("UNCHEKCED_NAMES")
class DetailViewModelFactory(private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Detail_viewmodel::class.java)) {
            return Detail_viewmodel(id = id) as T
        } else {
            throw IllegalArgumentException("unKnown view model class")
        }
    }
}

