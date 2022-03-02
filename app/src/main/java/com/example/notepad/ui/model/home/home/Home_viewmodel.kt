package com.example.notepad.ui.model.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.room.Tododatasource
import com.example.notepad.data.todo
import com.example.notepad.graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

//view model should have the parameter for which we want to get the  datasource
class Home_viewmodel(private val tododatasource: Tododatasource = graph.todorepo) : ViewModel() {

    //creating our state with the help of helper class with the help of mutable state
    private val _state = MutableStateFlow(Home_viewstate()) //limiting to only this class
    val state: StateFlow<Home_viewstate> //exposing the state
        get() = _state //returning the state

    val todolist = tododatasource.selectAll
    //not using mutable state flow becoz this is going to return a flow which we can later observe


    val selected =
        MutableStateFlow(_state.value.isSelected) //to check whether it is selected or not

    //to initialize the variables
    init {
        //viewmodel is called here
        viewModelScope.launch {
            combine(todolist, selected) { todolist: List<todo>, selected: Boolean ->
                //homestate is called here
                Home_viewstate(todolist)
            }.collect { // to collect the recent values from the recent values
                // which has been provided by stateflow so that we can get the home view state
                _state.value =
                    it  //initialise the state here which has been emitted using these flows

            }
        }
    }

    //to update the state , as our state lies directly from the database -- update fun is used to update the database

    fun updatetodo(isDone: Boolean, id: Long) = viewModelScope.launch {
        tododatasource.updatetodo(isDone, id)
    }

    fun delete(todo: todo) = viewModelScope.launch {
        tododatasource.deletetodo(todo)
    }


}

data class Home_viewstate(
    // to combine all the states  in one class so that not too many arguments are needed to pass

    val todolist: List<todo> = emptyList(), //initialise to empty list if no data is available
    val isSelected: Boolean = false


)