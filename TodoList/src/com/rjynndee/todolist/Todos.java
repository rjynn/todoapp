package com.rjynndee.todolist;

import java.io.Serializable;

public class Todos implements Serializable{
	/**
	 * Todos serializable ID
	 */
	private static final long serialVersionUID = 7974133207631838292L;
	protected String TodoName;
	protected boolean TodoChecked;
	protected boolean TodoArchived;
	
	public Todos(String todoname) {
		this.TodoName = todoname;
		this.TodoChecked = false;
		this.TodoArchived = false;
	}

	public String getName() {
		return this.TodoName;
	}
	
	public void changeCheck(){
		boolean bool = TodoChecked == false;
		this.TodoChecked = bool;
		
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
	}
	
	public String toString(){
		return getName();
	}

}
