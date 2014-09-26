package com.rjynndee.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SelectMultipleAdapter extends ArrayAdapter<Todos>{
	private Context context;
	private ArrayList<Todos> list;
	private ToDoListManager mymanager;
	public SelectMultipleAdapter(Context context,int textViewResourceId, ArrayList<Todos> list, ToDoListManager manager){
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
		mymanager = manager;
	}

	private class ViewKeep{
		TextView TodoName;
		CheckBox check;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		ViewKeep holder = null;
		if (convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.checkboxes_layout, null);
			holder = new ViewKeep();
			holder.TodoName = (TextView) convertView.findViewById(R.id.TodoListViewTextView);
			holder.check = (CheckBox) convertView.findViewById(R.id.ToDoCheckBox);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewKeep) convertView.getTag();
		}
		Todos todo = list.get(position);
		todo.addListener(new Listener(){
			public void update(){ 
				TodoList newlist = new TodoList();
				newlist.addAll(list);
				mymanager.saveTodoList(newlist);//this will be called when listener is notified
				
			}
		});
		holder.TodoName.setText(todo.getName());
		holder.check.setTag(todo);
		holder.check.setChecked(todo.getchecked());

		holder.check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Todos clickedtodo = (Todos) v.getTag();
				clickedtodo.changeCheck();
				ListController.changedChecked();
				Toast.makeText(context, "Changed Checked", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		return convertView;
		
	}	
	}