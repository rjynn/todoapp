package com.rjynndee.todolist;

public class Todos {
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
