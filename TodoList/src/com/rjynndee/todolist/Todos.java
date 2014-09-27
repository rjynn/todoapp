/*This class describes the todo list items that are the elements that comprises the 
TodoList class. It has a listener in order to know when the item has been checked off or not
and it has only one setter class called changeCheck() which will change the boolean value when an item is either checked or not.
These are typically not to be edited since there is no editing in this application so there are no setters for the name attribute in this class*/

package com.rjynndee.todolist;

import java.io.Serializable;
import java.util.ArrayList;

public class Todos implements Serializable{
	/**
	 * Todos serializable ID
	 */
	private static final long serialVersionUID = 7974133207631838292L;
	protected String TodoName;
	protected boolean TodoChecked;
	protected transient ArrayList<Listener> listeners = null; //dont need to save so transient
	
	
	public Todos(String todoname) {
		this.TodoName = todoname;
		this.TodoChecked = false;
	}

	public String getName() {
		return this.TodoName;
	}
	
	public void changeCheck(){
		boolean bool = TodoChecked == false;
		this.TodoChecked = bool;
		notifyListeners();
	}
	public boolean getchecked() {
		return this.TodoChecked;
	}

	public String toString(){
		return getName();
	}
	public boolean equals(Object compareTodo){	//this is used to compare object with this object 
		if (compareTodo != null && compareTodo.getClass()== this.getClass()){
			return this.equals((Todos)compareTodo);
		}
		else{
			return false;
		}
	}
	
	public boolean equals(Todos compareTodo){	//different compare to comare 2 to-dos
		if(compareTodo == null){
			return false;}
		return getName().equals(compareTodo.getName());
	}
	

	private ArrayList<Listener> getListeners(){
		if (listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
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
	
	

}
