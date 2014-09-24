package com.rjynndee.todolist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ToDoListManager {
	static final String prefFile = "TodoList";
	static final String tlkey = "TodoList";
	Context context;
	
	public ToDoListManager(Context context2) {
		this.context = context2;
	}


	public TodoList loadTodoList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String todolistdata = settings.getString(tlkey, "");
		if (todolistdata.equals("")){
			return new TodoList();
		}
		else{
			return TodoListFromString(todolistdata);
		}
	}
	
	private TodoList TodoListFromString(String todolistdata) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(todolistdata.getBytes());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TodoList) oi.readObject();
	}
	
	private String TodoListToString(TodoList tl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(tl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return new String(bytes);
	}

	public void saveTodoList(TodoList tl) throws IOException{
	SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
	Editor editor = settings.edit();
	editor.putString(tlkey,TodoListToString(tl));
	editor.commit();
	}



	
}
