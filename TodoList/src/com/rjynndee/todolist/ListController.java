//this controls and manipulates the class todolist when app is running

package com.rjynndee.todolist;

public class ListController {
	
	//lazy singleton
	private static TodoList list = null;
	private static TodoList archive = null;
	
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
	
	private static class Statistics{
		int TodoCount = 0;
		int ArchiveCount =0;
		int Checked = 0;
		int Unchecked =0;
		int TodoChecked = 0;
		int TodoUnchecked =0;
		int ArchiveChecked =0;
		int ArchiveUnchecked =0;
		int Total =0;
	}
	
	static public TodoList getTodoArchiveList(){
		if (archive == null){
			archive = ToDoListManager.getManager().loadArchiveTodoList();
			archive.addListener(new Listener(){
				@Override
				public void update() {
					saveArchiveToDoList();
				}
			});
		}
		return archive;
	}
	
	static public void saveArchiveToDoList(){
		ToDoListManager.getManager().saveArchiveTodoList(getTodoArchiveList());
	}
	static public void saveToDoList(){
		ToDoListManager.getManager().saveTodoList(getTodoList());
	}
	public void addToDoArchive(Todos todos) {
		getTodoList().removetodo(todos);
		getTodoArchiveList().addtodo(todos);
	}
	

	public void addToDo(Todos todos) {
		getTodoList().addtodo(todos);
	}
	
	public void removeToDo(Todos todos) {
		getTodoList().removetodo(todos);
	}
	
	public void removeArchiveToDo(Todos todos) {
		getTodoArchiveList().removetodo(todos);
	}
	
	public void moveTodoDoFromArchive(Todos todos) {
		getTodoArchiveList().removetodo(todos);
		getTodoList().addtodo(todos);
	}
	
	public Todos getTodo(int position){
		return getTodoList().get(position);
		
	}
	
	public Todos getArchivedTodo(int position){
		return getTodoArchiveList().get(position);
		
	}
	
	public void recount(){
		
	}
	
}
