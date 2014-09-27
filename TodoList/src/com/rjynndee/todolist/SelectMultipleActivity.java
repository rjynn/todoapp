/*this activity is for the select multiple items to email UI. Most work is done within this activity itself because
 * emailing does not alter the data or application and does not need to be rememebered*/
package com.rjynndee.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import com.rjynndee.rdejesusnotes.R;

public class SelectMultipleActivity extends Activity {

	ArrayList <Todos> emailinglist = new ArrayList<Todos>();
	ArrayList <Todos> list = new ArrayList<Todos>();
	SelectMultipleAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectmultiplelayout);
		ListManager.initManager(this.getApplicationContext());
		final ListView listview = (ListView) findViewById(R.id.SelectMultipleListView);
		Collection<Todos> data = ListController.getTodoList().getTodos();
		Collection<Todos> data2 = ListController.getTodoArchiveList().getTodos();
		list.addAll(data);
		list.addAll(data2);
		adapter = new SelectMultipleAdapter(this, R.layout.selectcheckboxes_layout, list, ListManager.getManager());
		listview.setAdapter(adapter);

		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CheckBox check = (CheckBox) view.findViewById(R.id.selectthischeckbox);
				if(!check.isChecked()){
					check.setChecked(true);
					emailinglist.add(list.get(position));
					
				}
				else{
					check.setChecked(false);
					emailinglist.remove(list.get(position));
				}
			}
		});
	}
	public void EmailSelectedItems(View view){
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		String string = "TODOLIST:" +'\n'+'\n';
		for (Todos todo : emailinglist){
			string += todo.toString() + '\n';
		}
		intent.putExtra(Intent.EXTRA_TEXT, string);
		try{
			startActivity(Intent.createChooser(intent, "Emailing To do Items..."));
			emailinglist.clear();
			adapter.notifyDataSetChanged();
		}
		catch(Exception e){
			AlertDialog.Builder alert = new AlertDialog.Builder(SelectMultipleActivity.this);
			String message = "There are no email clients. Please Download one.";
			alert.setMessage(message);
			alert.setCancelable(true);
			alert.setNegativeButton("OK", new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {	
				}
			});
			alert.show();
			
		}
	}
	
	public void SelectAll(View view){
		emailinglist.addAll(list);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		String string = "TODOLIST:" +'\n'+'\n';
		for (Todos todo : emailinglist){
			string += todo.toString() + '\n';
		}
		intent.putExtra(Intent.EXTRA_TEXT, string);
		try{
			startActivity(Intent.createChooser(intent, "Emailing To do Items..."));
		}
		catch(Exception e){
			AlertDialog.Builder alert = new AlertDialog.Builder(SelectMultipleActivity.this);
			String message = "There are no email clients. Please Download one.";
			alert.setMessage(message);
			alert.setCancelable(true);
			alert.setNegativeButton("OK", new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {	
				}
			});
			alert.show();
			
		}
		startActivity(Intent.createChooser(intent, "Emailing To do Items..."));
		emailinglist.clear();
		adapter.notifyDataSetChanged();
		
		
		
	}
}
