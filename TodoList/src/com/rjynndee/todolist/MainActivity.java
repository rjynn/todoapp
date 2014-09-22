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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listview = (ListView) findViewById(R.id.listofTodosList);
		Collection<Todos> Todoscoll = ListController.getTodoList().getTodos();
		final ArrayList<Todos> list = new ArrayList<Todos>(Todoscoll);
		final ArrayAdapter<Todos> TodoAdapter = new ArrayAdapter<Todos>(this, android.R.layout.simple_list_item_1,list);
		listview.setAdapter(TodoAdapter);
		
		//added observer
		ListController.getTodoList().addListener(new Listener(){
			public void update(){
				list.clear();
				Collection<Todos> Todoscoll = ListController.getTodoList().getTodos();
				list.addAll(Todoscoll);
				TodoAdapter.notifyDataSetChanged();
			}
		});
		
		listview.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this, "Deleted "+list.get(position).toString(), Toast.LENGTH_SHORT).show();
				Todos todo = list.get(position);
				ListController.getTodoList().removetodo(todo);
				return false;
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
	public void addTodoAction(View view){
		Toast.makeText(this, "Added an item", Toast.LENGTH_SHORT).show();
		ListController ls = new ListController();
		EditText textView = (EditText) findViewById(R.id.AddNewToDoTextField);
		ls.addToDo(new Todos(textView.getText().toString()));
	}
}
