//this controls and manipulates the class todolist when app is running

package com.rjynndee.todolist;

import java.io.IOException;

public class ListController {
	
	//lazy singleton
	private static TodoList list = null;
	
	static public TodoList getTodoList(){
		if (list == null){
			list = ToDoListManager.getManager().loadTodoList();
			list.addListener(new Listener(){
				@Override
				public void update() {
					saveToDoList();
				}
			});
		}
		return list;
	}
	
	
	static public void saveToDoList(){
		ToDoListManager.getManager().saveTodoList(getTodoList());
	}
	
	
	public void addToDo(Todos todos) {
		getTodoList().addtodo(todos);
	}
}
