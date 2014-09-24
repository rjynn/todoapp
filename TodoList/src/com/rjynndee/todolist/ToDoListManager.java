package com.rjynndee.todolist;

import android.content.Context;
import android.content.SharedPreferences;

public class ToDoListManager {
	static String prefFile = "TodoList";
	Context context;
	
	public ToDoListManager(Context context2) {
		this.context = context2;
	}


	public TodoList loadTodoList(){
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String todolistdata = settings.getString("TodoList", "");
		if (todolistdata.equals("")){
			return new TodoList();
		}
		else{
			return TodoListFromString(todolistdata);
		}
	
	}
	
	
	private TodoList TodoListFromString(String todolistdata) {
		// TODO Auto-generated method stub
		return null;
	}


	public void saveTodoList(TodoList tl){
	}
	
}
