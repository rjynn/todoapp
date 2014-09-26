//should really be changing this to archive activity

package com.rjynndee.todolist;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListToDosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived);
		ToDoListManager.initManager(this.getApplicationContext());
		final ListView listview = (ListView) findViewById(R.id.listofTodosArchiveList);
		Collection<Todos> Todoscoll = ListController.getTodoArchiveList().getTodos();
		final ArrayList<Todos> list = new ArrayList<Todos>(Todoscoll);
		final NewArchiveListAdapter TodoAdapter = new NewArchiveListAdapter(this,R.layout.checkboxes_layout,list, ToDoListManager.getManager());
		listview.setAdapter(TodoAdapter);
		registerForContextMenu(listview);
		ListController.recount();
		ListController.getTodoArchiveList().addListener(new Listener(){

			@Override
			public void update() {
				list.clear();
				Collection<Todos> Todoscoll = ListController.getTodoArchiveList().getTodos();
				list.addAll(Todoscoll);
				TodoAdapter.notifyDataSetChanged();
			}
		});	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_to_dos, menu);
		return true;
	}
	
	public void showStats(MenuItem menu){
		final ListController ls = new ListController();
		AlertDialog.Builder adb = new AlertDialog.Builder(ListToDosActivity.this);
		Statistics stats = ls.getStats();
		String message = "Number of Active Items: " + Integer.toString(stats.TodoCount)+'\n'+
				"       Checked: " + Integer.toString(stats.TodoChecked)+'\n'+
				"       Unchecked: " + Integer.toString(stats.TodoUnchecked)+'\n'+'\n'+
				"Number of Archived Items: " + Integer.toString(stats.ArchiveCount)+'\n'+
				"       Checked: " + Integer.toString(stats.ArchiveChecked)+'\n' +
				"       Unchecked:" + Integer.toString(stats.ArchiveUnchecked)+'\n' +
				"_____________________________" +'\n' +
				"Total Items: " + Integer.toString(stats.Total)+'\n'+
				"      Checked:" + Integer.toString(stats.Checked)+'\n'+
				"      Unchecked: " + Integer.toString(stats.Unchecked);
		adb.setMessage(message);
		adb.setCancelable(true);
		adb.setNegativeButton("OK", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {	
			}
		});
		adb.show();
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


public boolean onContextItemSelected(MenuItem item){
	AdapterView.AdapterContextMenuInfo myinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	switch (item.getItemId()) {
	case R.id.DeleteHoldMenu:
		final ListController ls = new ListController();
		AlertDialog.Builder adb = new AlertDialog.Builder(ListToDosActivity.this);
		adb.setMessage("Delete "+ls.getArchivedTodo(myinfo.position).toString()+"?");
		adb.setCancelable(true);
		final int fPosition = myinfo.position;
		adb.setPositiveButton("Delete", new OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Todos todo = ls.getArchivedTodo(fPosition);
				Toast.makeText(ListToDosActivity.this, "Deleted "+todo.toString(), Toast.LENGTH_SHORT).show();
				ls.removeArchiveToDo(todo);
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
		AlertDialog.Builder adb1 = new AlertDialog.Builder(ListToDosActivity.this);
		adb1.setMessage("Archive/Unarchive "+ls1.getArchivedTodo(myinfo.position).toString()+"?");
		adb1.setCancelable(true);
		final int fPosition1 = myinfo.position;
		adb1.setPositiveButton("Archive/Unarchive", new OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Todos todo = ls1.getArchivedTodo(fPosition1);
				Toast.makeText(ListToDosActivity.this, "Archived/Unarchived "+todo.toString(), Toast.LENGTH_SHORT).show();
				ls1.moveTodoDoFromArchive(todo);
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

