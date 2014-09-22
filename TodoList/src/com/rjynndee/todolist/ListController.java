package com.rjynndee.todolist;

public class ListController {
	
	//lazy singleton
	private static TodoList list = null;
	
	static public TodoList getTodoList(){
		if (list == null){
			list = new TodoList();
		}
		return list;
	}
	public void addToDo(Todos todos) {
		getTodoList().addtodo(todos);
	}
	
}
