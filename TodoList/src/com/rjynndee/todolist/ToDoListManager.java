package com.rjynndee.todolist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;


public class ToDoListManager {
	static final String prefFile = "TodoList";
	static final String tlkey = "TodoList";
	Context context;
	
	static private ToDoListManager TodoListManager = null;
	
	public static void initManager(Context context){
		if (TodoListManager == null){
			if (context ==null){
				throw new RuntimeException("missing context for TodoListManager");
			}
			TodoListManager = new ToDoListManager(context);
		}
	}
	
	public static ToDoListManager getManager(){
		if (TodoListManager ==null){
			throw new RuntimeException("Did not initialize Manager");
		}
		return TodoListManager;
	}
	
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
	
	static public TodoList TodoListFromString(String todolistdata) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(todolistdata, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TodoList) oi.readObject();
	}
	
	static public String TodoListToString(TodoList tl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(tl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}

	public void saveTodoList(TodoList tl) throws IOException{
	SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
	Editor editor = settings.edit();
	editor.putString(tlkey,TodoListToString(tl));
	editor.commit();
	}



	
}
