//this is the class of the list of to dos
package com.rjynndee.todolist;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TodoList implements Serializable{
	/**
	 * Todolist serialization ID
	 */
	private static final long serialVersionUID = -851483106886788193L;
	protected ArrayList<Todos> list;
	protected ArrayList<Listener> listeners;
	
	public TodoList(){
		list = new ArrayList<Todos>();
		listeners = new ArrayList<Listener>();
	}
	public Collection<Todos> getTodos() {
		return list;
	}

	public void addtodo(Todos Todo) {
		list.add(Todo);
		notifyListeners();
	}
	public void removetodo(Todos Todo){
		list.remove(Todo);
		notifyListeners();
	}
	
	public void notifyListeners(){
		for(Listener listener : listeners){
			listener.update();
		}
	}
	public void addListener(Listener l) {
		listeners.add(l);
		
	}
	public void removeListener(Listener l) {
		listeners.remove(l);
	}
	
	public boolean contains(Todos todo){
		return list.contains(todo);
	}
	public int size(){
		return list.size();
	}
}
