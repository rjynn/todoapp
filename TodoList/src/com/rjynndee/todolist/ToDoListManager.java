package com.rjynndee.todolist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.util.Log;


public class ToDoListManager implements IDataManager {
	private static final String FILENAME = "file.sav";
	private static final String FILENAME2 = "file2.sav";
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

	public TodoList loadTodoList(){
		TodoList list = new TodoList();
		try{
		FileInputStream fis = context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (TodoList) ois.readObject();
		
		}
		catch(Exception e){
			Log.i("TODOLIST","ERROR CASTING");
			e.printStackTrace();
		}
		return list;
	}
	
	public TodoList loadArchiveTodoList(){
		TodoList list = new TodoList();
		try{
		FileInputStream fis = context.openFileInput(FILENAME2);
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (TodoList) ois.readObject();
		
		}
		catch(Exception e){
			Log.i("TODOLIST","ERROR CASTING");
			e.printStackTrace();
		}
		return list;
	}

	public void saveTodoList(TodoList tl){
		try{ FileOutputStream fos = context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(tl);
		fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
		
		}
	}
		
		public void saveArchiveTodoList(TodoList tl){
			try{ FileOutputStream fos = context.openFileOutput(FILENAME2,Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tl);
			fos.close();
			}
			catch(Exception e){
				e.printStackTrace();
			
			
			}
	}




	
}
