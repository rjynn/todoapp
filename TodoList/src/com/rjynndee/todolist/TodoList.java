package com.rjynndee.todolist;


import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
	protected ArrayList<Todos> todolist;
	public TodoList(){
		todolist = new ArrayList<Todos>();
	}
	public Collection<Todos> getToDos() {
		return todolist;
	}

	public void addtodo(Todos testToDo) {
		todolist.add(testToDo);
		
	}

}
