/*this allows the listview in the select multiple to be set in a certain way */
package com.rjynndee.todolist;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.rjynndee.rdejesusnotes.R;

public class SelectMultipleAdapter extends ArrayAdapter<Todos>{
	private Context context;
	private ArrayList<Todos> list;
	public SelectMultipleAdapter(Context context,int textViewResourceId, ArrayList<Todos> list, ListManager manager){
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
	}

	private class ViewKeep{
		TextView TodoName;
		CheckBox check;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		ViewKeep holder = null;
		if (convertView == null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.selectcheckboxes_layout, null);
			holder = new ViewKeep();
			holder.TodoName = (TextView) convertView.findViewById(R.id.selectitemTextView);
			holder.check = (CheckBox) convertView.findViewById(R.id.selectthischeckbox);
			convertView.setTag(holder);
			
		}
		else{
			holder = (ViewKeep) convertView.getTag();
		}
		Todos todo = list.get(position);
		holder.TodoName.setText(todo.getName());
		holder.check.setChecked(false);
		return convertView;
		
	}	
	}