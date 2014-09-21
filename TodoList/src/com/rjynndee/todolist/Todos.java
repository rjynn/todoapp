package com.rjynndee.todolist;

public class Todos {
	protected String todoName;
	protected boolean checked;
	protected boolean archived;
	
	public Todos(String todo) {
		this.todoName = todo;
		this.checked = false;
		this.archived = false;
	}

	public String getName() {
		return this.todoName;
	}
	
	public void changeCheck(){
		boolean bool = checked == false;
		this.checked = bool;
		
	}

	public boolean getchecked() {
		return this.checked;
	}

	public boolean getarchived() {
		return this.archived;
	}

	public void changeArchived() {
		boolean bool = this.archived == false;
		this.archived = bool;
	}

}
