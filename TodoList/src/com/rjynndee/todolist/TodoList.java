package com.rjynndee.todolist;


import java.util.ArrayList;
import java.util.Collection;

public class TodoList {
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
}
