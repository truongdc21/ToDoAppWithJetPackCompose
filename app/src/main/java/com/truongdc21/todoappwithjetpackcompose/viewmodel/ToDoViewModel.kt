package com.truongdc21.todoappwithjetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.truongdc21.todoappwithjetpackcompose.model.ToDo
import java.lang.Exception

class ToDoViewModel : ViewModel() {

    private var mListToDo = mutableListOf<ToDo>()
    private var _mListToDoObserve = MutableLiveData<List<ToDo>>()
    val mListToDoObserve: MutableLiveData<List<ToDo>>
        get() = _mListToDoObserve

    init {
        mListToDo.add(ToDo(1, "Task 1", true))
        mListToDo.add(ToDo(2, "Task 2", false))
        mListToDo.add(ToDo(3, "Task 3", true))
        _mListToDoObserve.postValue(mListToDo.toMutableList())
    }

    fun addToDo(task: String): Boolean {
        var result = false
        try {
            var max = 0
            for (i in mListToDo) {
                if (max < i.id) {
                    max = i.id
                }
            }
            val todo = ToDo(max + 1, task, false)
            mListToDo.add(todo)
            _mListToDoObserve.postValue(mListToDo.toMutableList())
            result = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun updateTask(todo: ToDo) {
        for (i in mListToDo) {
            if (i.id == todo.id) {
                val index = mListToDo.indexOf(i)
                mListToDo[index] = ToDo(i.id, i.title, !i.isCompleted)
                _mListToDoObserve.postValue(mListToDo.toMutableList())
                break
            }
        }
    }
}