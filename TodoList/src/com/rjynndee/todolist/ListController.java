//this controls and manipulates the class todolist when app is running

package com.rjynndee.todolist;

import java.io.IOException;

public class ListController {
	
	//lazy singleton
	private static TodoList list = null;
	
	static public TodoList getTodoList(){
		if (list == null){
			try {
				list = ToDoListManager.getManager().loadTodoList();
				list.addListener(new Listener(){
					@Override
					public void update() {
						saveToDoList();
					}
				});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize todolist from todolist manager");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize todolist from todolist manager");
			}
		}
		return list;
	}
	
	
	static public void saveToDoList(){
		try {
			ToDoListManager.getManager().saveTodoList(getTodoList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize todolist from todolist manager");
		}
	}
	
	
	public void addToDo(Todos todos) {
		getTodoList().addtodo(todos);
	}
}
