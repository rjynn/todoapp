/*ToDoList: Allows user to create a list of items for reminders
    Copyright (C) 2014  Ruby De Jesus rdejesus@ualberta.ca

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


package com.rjynndee.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ToDoListManager.initManager(this.getApplicationContext());
		
		final ListView listview = (ListView) findViewById(R.id.listofTodosListView);
		Collection<Todos> Todoscoll = ListController.getTodoList().getTodos(); //need to specify what is in the listview
		final ArrayList<Todos> list = new ArrayList<Todos>(Todoscoll);
		final NewListAdapter TodoAdapter = new NewListAdapter(this,R.layout.checkboxes_layout, list, ToDoListManager.getManager());
		listview.setAdapter(TodoAdapter); //this adapter controls the view of the listview
		registerForContextMenu(listview);
		

		//added observer to update the list when changes occur 
		ListController.recount();
		ListController.getTodoList().addListener(new Listener(){
			public void update(){ //this will be called when listener is notified
				list.clear();
				Collection<Todos> Todoscoll = ListController.getTodoList().getTodos();
				list.addAll(Todoscoll);
				TodoAdapter.notifyDataSetChanged();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu,v,menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.holdsmenu,menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void SelectAll(MenuItem menu){
		Toast.makeText(this, "Selected All", Toast.LENGTH_SHORT).show();
	}
	
	public void goArchived(MenuItem menu){
		Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, ListToDosActivity.class);
		startActivity(intent);
	}
	
	public void showStats(MenuItem menu){
		final ListController ls = new ListController();
		AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
		Statistics stats = ls.getStats();
		String message = "Number of Active Items: " + Integer.toString(stats.TodoCount);
		adb.setMessage(message);
		adb.setCancelable(true);
		adb.setNegativeButton("OK", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {	
			}
		});
		adb.show();
	}
	
	public void addTodoAction(View view){
		Toast.makeText(this, "Added an item", Toast.LENGTH_SHORT).show();
		ListController ls = new ListController();
		EditText textView = (EditText) findViewById(R.id.TodoEditText);
		ls.addToDo(new Todos(textView.getText().toString()));
		textView.setText(null);
	}
	
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo myinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.DeleteHoldMenu:
			final ListController ls = new ListController();
			AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
			adb.setMessage("Delete "+ls.getTodo(myinfo.position).toString()+"?");
			adb.setCancelable(true);
			final int fPosition = myinfo.position;
			adb.setPositiveButton("Delete", new OnClickListener(){

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Todos todo = ls.getTodo(fPosition);
					Toast.makeText(MainActivity.this, "Deleted "+todo.toString(), Toast.LENGTH_SHORT).show();
					ls.removeToDo(todo);
				}
			});
			adb.setNegativeButton("Cancel", new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {	
				}
			});
			adb.show();
			break;
		case R.id.archiveHoldsMenu:
			final ListController ls1 = new ListController();
			AlertDialog.Builder adb1 = new AlertDialog.Builder(MainActivity.this);
			adb1.setMessage("Archive "+ls1.getTodo(myinfo.position).toString()+"?");
			adb1.setCancelable(true);
			final int fPosition1 = myinfo.position;
			adb1.setPositiveButton("Archive", new OnClickListener(){

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Todos todo = ls1.getTodo(fPosition1);
					Toast.makeText(MainActivity.this, "Archived "+ls1.getTodo(fPosition1).toString(), Toast.LENGTH_SHORT).show();
					ls1.addToDoArchive(todo);
				}
			});
			adb1.setNegativeButton("Cancel", new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {	
				}
			});
			adb1.show();
			return false;
			
		}
		return true;
	}
	
}


