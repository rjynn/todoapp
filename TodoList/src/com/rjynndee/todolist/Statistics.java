/*this class holds the information pertaining to the activities of the ListController.
 It holds the summary of the data within the two lists in the application
 resetStats() is a method to reset the counts of the Statistics so that when one changes the
 data the List will update itself by recounting*/

package com.rjynndee.todolist;

public class Statistics{
		public int TodoCount;
		public int ArchiveCount;
		public int Checked;
		public int Unchecked;
		public int TodoChecked;
		public int TodoUnchecked;
		public int ArchiveChecked;
		public int ArchiveUnchecked;
		public int Total;
		
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
