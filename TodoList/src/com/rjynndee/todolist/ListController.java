/*this controls and manipulates the class todolist when app is running
it uses the ListManager in order to save and load the lists that have been statically
created to this controller so that it will always have those lists to access*/

package com.rjynndee.todolist;

public class ListController {
	
	private static TodoList list = null;
	private static TodoList archive = null;
	private static Statistics stats = new Statistics();
	
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
		recount();
	}
	
	public static void changedChecked(){
		recount();
	}

	public void addToDo(Todos todos) {
		getTodoList().addtodo(todos);
		recount();
	}
	
	public void removeToDo(Todos todos) {
		getTodoList().removetodo(todos);
		recount();
	}
	
	public void removeArchiveToDo(Todos todos) {
		getTodoArchiveList().removetodo(todos);
		recount();
	}
	
	public void moveTodoDoFromArchive(Todos todos) {
		getTodoArchiveList().removetodo(todos);
		getTodoList().addtodo(todos);
		recount();
	}
	
	public Todos getTodo(int position){
		return getTodoList().get(position);
		
	}
	
	public Todos getArchivedTodo(int position){
		return getTodoArchiveList().get(position);
		
	}
	
	public static void recount(){
		stats.TodoCount = getTodoList().size();
		stats.ArchiveCount = getTodoArchiveList().size();
		stats.Checked = 0;
		stats.Unchecked =0;
		stats.TodoChecked = 0;
		stats.TodoUnchecked =0;
		stats.ArchiveChecked =0;
		stats.ArchiveUnchecked =0;
		for(int count=0; count< stats.TodoCount; count++){
			if(getTodoList().get(count).getchecked() == true){
				stats.Checked++;
				stats.TodoChecked++;}
			else{
				stats.Unchecked++;
				stats.TodoUnchecked++;
			}
		}
		for(int count=0; count<stats.ArchiveCount; count++){
			if(getTodoArchiveList().get(count).getchecked() == true){
				stats.Checked++;
				stats.ArchiveChecked++;}
			else{
				stats.Unchecked++;
				stats.ArchiveUnchecked++;
			}
		}
		stats.Total = (stats.TodoCount + stats.ArchiveCount);
	}
	
	public Statistics getStats(){
		return stats;
	}
}
