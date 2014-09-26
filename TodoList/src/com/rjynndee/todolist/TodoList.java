/*this is the class of the list of to dos that stores a list of each todo element in a list
it has its own observer and notifies that for any changes made in the list*/
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
	protected transient ArrayList<Listener> listeners = null; //dont need to save so transient
	
	public TodoList(){
		list = new ArrayList<Todos>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if (listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
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
		for(Listener listener : getListeners()){
			listener.update();
		}
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
		
	}
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	
	public boolean contains(Todos todo){
		return list.contains(todo);
	}
	public int size(){
		return list.size();
	}

	public void addAll(Collection<Todos> list2) {
		list.addAll(list2);
		
	}

	public Todos get(int position) {
		return list.get(position);
	}
	
}
