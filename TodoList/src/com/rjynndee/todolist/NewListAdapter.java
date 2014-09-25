package com.rjynndee.todolist;

import java.util.ArrayList;

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

public class NewListAdapter extends ArrayAdapter<Todos>{
	private Context context;
	private ArrayList<Todos> list;
	
	public NewListAdapter(Context context,int textViewResourceId, ArrayList<Todos> list){
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
	}

	private class ViewHolder{
		TextView TodoName;
		CheckBox check;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		ViewHolder holder = null;
		Log.v("--ConvertView", String.valueOf(position));
		if (convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.checkboxes_layout, null);
			holder = new ViewHolder();
			holder.TodoName = (TextView) convertView.findViewById(R.id.TodoListViewTextView);
			holder.check = (CheckBox) convertView.findViewById(R.id.ToDoCheckBox);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		Todos todo = list.get(position);
		holder.TodoName.setText(todo.getName());
		holder.check.setTag(todo);
		Log.v("--should be true", Boolean.toString(todo.getchecked()));
		holder.check.setChecked(todo.getchecked());

		holder.check.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Todos clickedtodo = (Todos) v.getTag();
				clickedtodo.changeCheck();
				Toast.makeText(context, "Changed Checked", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		return convertView;
		
	}	
	}
