package com.rjynndee.todolist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ToDoListManager {
	static final String prefFile = "TodoList";
	static final String tlKey = "todoList";
	Context context;
	
	public ToDoListManager(Context context) {
		this.context = context;
	}
	public TodoList loadTodoList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String todolistdata = settings.getString(tlKey,"");
		if (todolistdata.equals("")){
			return new TodoList();
		} else{
			return todoListFromString(todolistdata);
		}
	}
	private TodoList todoListFromString(String todolistdata) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(todolistdata.getBytes("utf-8"));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TodoList)oi.readObject();
	}

	private String todoListToString(TodoList tl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(tl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return new String(bytes, "utf-8");

	}
	public void saveTodoList(TodoList tl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString("tlKey", todoListToString(tl));
		editor.commit();
	}


}
