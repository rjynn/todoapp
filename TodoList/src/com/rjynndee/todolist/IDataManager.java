package com.rjynndee.todolist;

public interface IDataManager {
	public void saveTodoList(TodoList tl);
	public TodoList loadTodoList();
}
