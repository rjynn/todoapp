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
	protected boolean TodoArchived;
	protected transient ArrayList<Listener> listeners = null; //dont need to save so transient
	
	
	public Todos(String todoname) {
		this.TodoName = todoname;
		this.TodoChecked = true;
		this.TodoArchived = true;
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

	public boolean getarchived() {
		return this.TodoArchived;
	}

	public void changeArchived() {
		boolean bool = this.TodoArchived == false;
		this.TodoArchived = bool;
		notifyListeners();
	}
	
	public String toString(){
		return getName();
	}
	public boolean equals(Object compareTodo){
		if (compareTodo != null && compareTodo.getClass()== this.getClass()){
			return this.equals((Todos)compareTodo);
		}
		else{
			return false;
		}
	}
	
	public boolean equals(Todos compareTodo){
		if(compareTodo == null){
			return false;}
		return getName().equals(compareTodo.getName());
	}
	
	public int hashCode(){
		return ("Todo:"+getName()).hashCode();
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
