/*This Class controls and holds all the data that is transferred to and from different lists in the application. It deals
 * with any manipulation of the lists like add, delete, or moving from one list to another. It contains a statistics class that holds the metadata
 * of the class itself. The Lists have been implemented statically in order to keep the information
 * throughout the application, however issues will occur if another list is wanted for future development. May have to
 * figure out a way to implement without the static lists */

package com.rjynndee.todolist;

public class ListController {
	
	private static TodoList list = null;
	private static TodoList archive = null;
	private static Statistics stats = new Statistics();
	private static ListManager manager = ListManager.getManager();
	
	static public TodoList getTodoList(){		//returns stored todolist
		if (list == null){
			list = manager.loadTodoList();
			list.addListener(new Listener(){
				@Override
				public void update() {
					manager.saveTodoList(list);;
				}
			});
		}
		return list;
	}
	
	static public TodoList getTodoArchiveList(){		//returns stored archive list
		if (archive == null){
			archive = manager.loadArchiveTodoList();
			archive.addListener(new Listener(){
				@Override
				public void update() {
					manager.saveArchiveTodoList(archive);;
				}
			});
		}
		return archive;
	}
	
	public void addToDoArchive(Todos todos) {
		list.removetodo(todos);
		archive.addtodo(todos);
		recount();
	}
	

	public void addToDo(Todos todos) {
		list.addtodo(todos);
		recount();
	}
	
	public void removeToDo(Todos todos) {
		list.removetodo(todos);
		recount();
	}
	
	public void removeArchiveToDo(Todos todos) {
		archive.removetodo(todos);
		recount();
	}
	
	public void moveTodoDoFromArchive(Todos todos) {
		archive.removetodo(todos);
		list.addtodo(todos);
		recount();
	}
	
	public Todos getTodo(int position){
		return list.get(position);
		
	}
	
	public Todos getArchivedTodo(int position){
		return archive.get(position);
		
	}
	
	public static void recount(){		//this counts the elements in each list
		stats.resetStats();
		stats.TodoCount = getTodoList().size();
		stats.ArchiveCount = getTodoArchiveList().size();
		for(int count=0; count< stats.TodoCount; count++){
			if(getTodoList().get(count).getchecked() == true){	//loop to count checked items
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
