/*this class holds the information pertaining to the activities of the ListController.
 It holds the summary of the data within the two lists in the application
 resetStats() is a method to reset the counts of the Statistics so that when one changes the
 data the List will update itself by recounting*/

package com.rjynndee.todolist;

public class Statistics{
		int TodoCount;
		int ArchiveCount;
		int Checked;
		int Unchecked;
		int TodoChecked;
		int TodoUnchecked;
		int ArchiveChecked;
		int ArchiveUnchecked;
		int Total;
		
		public Statistics(){
			TodoCount = 0;
			ArchiveCount =0;
			Checked = 0;
			Unchecked =0;
			TodoChecked = 0;
			TodoUnchecked =0;
			ArchiveChecked =0;
			ArchiveUnchecked =0;
			Total =0;
		}
		
		public void resetStats(){
			TodoCount = 0;
			ArchiveCount =0;
			Checked = 0;
			Unchecked =0;
			TodoChecked = 0;
			TodoUnchecked =0;
			ArchiveChecked =0;
			ArchiveUnchecked =0;
			Total =0;
		}
	
	
}
