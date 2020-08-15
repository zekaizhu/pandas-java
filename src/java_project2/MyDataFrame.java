package java_project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyDataFrame {
	
	public ArrayList<String> header;
	public ArrayList<String>[] columns;
	public int num_row;
	public int num_col;
	
	 //constructor
	
	public MyDataFrame(ArrayList<String> h,ArrayList<String> c[]) 
	{
		this.header = h;
		this.columns = c;
		this.num_row = c[0].size();
		this.num_col = h.size();
	}
    
	
	//1. Head and Tail.
		
	/**
	   * @param n: n rows.
	   * @return the first n rows of the data.
	   */
	public MyDataFrame head(int n)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[]=new ArrayList[num_col];
        
		// add first n rows from original df into new dataframe
		for(int i = 0; i < num_col; i++) {
			new_columns[i] = new ArrayList<String> (columns[i].subList(0, n));
		}
		
		return new MyDataFrame(header, new_columns);
	}
	
	 /**
	   * @param n: n rows.
	   * @return the last n rows of the data.
	   */
	public MyDataFrame tail(int n)
	{	
		// the first row will be the total number of row - n
		int start = num_row - n;

		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[]=new ArrayList[num_col];
        
		// add last n rows from original df into new dataframe
		for(int i = 0; i < num_col; i++) {
			new_columns[i] = new ArrayList<String>(columns[i].subList(start, num_row));
		}
		
		return new MyDataFrame(header, new_columns);
	}
	
	//2. dType
	/**
	   * @param index: integer.
	   * @return the type of the column specified by index. If the type is not uniform, return ‘String’
	   */

	public String dType(int index)
	{	
		String type = "String";
		for(int i = 0; i < num_row; i++) {
			try{
				// for each row in the inputted column , check if it can be convert to integer
				Integer.parseInt(columns[index].get(i));
				type = "Integer";
			} catch (NumberFormatException e){
	            type = "String";
	            return type;
			}
		}
		return type;
	}
	
	/**
	   * @param name: String.
	   * @return the type of the column specified by index. If the type is not uniform, return ‘String’
	   */
	public String dType(String name)
	{
		int index = -1;
		// find the index 
		for (int j = 0; j < num_col; j++) {
			if (header.get(j).equals(name))
				index = j;
		}
		String type = "String";
		for(int i = 0; i < num_row; i++) {
			try{
				// for each row in the inputted column , check if it can be convert to integer
				Integer.parseInt(columns[index].get(i));
				type = "Integer";
			} catch (NumberFormatException e){
	            type = "String";
	            return type;
			}
		}
		return type;
	}
	
	//3. Slicing
	/**
	   * @param index: integer.
	   * @return the column specified by index.
	   */
	public MyDataFrame slice(int index)
	{
		ArrayList<String> new_header = new ArrayList<String>();
		new_header.add(header.get(index));

		@SuppressWarnings("unchecked")
		// get all rows for desired column
		ArrayList<String> new_columns[] = new ArrayList[1];
		new_columns[0] = columns[index];

		return new MyDataFrame(new_header, new_columns);
	}
	
	/**
	   * @param name: String.
	   * @return the column specified by name.
	   */
	public MyDataFrame slice(String name)
	{
		// find the index for given name
		int index = header.indexOf(name);
		int a = header.get(0).compareTo("State");
		System.out.println(header.get(0).charAt(0) + "State");
		int b = header.get(0).length();
		int c = "State".length();
		System.out.println(a + " " + b + " " + c);

		ArrayList<String> new_header = new ArrayList<String>();
		new_header.add(header.get(index));

		@SuppressWarnings("unchecked")
		// get all rows for desired column
		ArrayList<String> new_columns[] = new ArrayList[1];
		new_columns[0] = columns[index];

		return new MyDataFrame(new_header, new_columns);
	}
	
	/**
	   * @param indexArr: int[].
	   * @return the columns specified by an index array.
	   */
	public MyDataFrame slice(int[] indexArr)
	{
		ArrayList<String> new_header = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[indexArr.length];
		
		// get all rows for desired columns
		for (int i = 0; i < indexArr.length; i++) {
			new_header.add(header.get(indexArr[i]));
			new_columns[i] = columns[indexArr[i]];
		}

		return new MyDataFrame(new_header, new_columns);
	}
	
	/**
	   * @param nameArr: String[].
	   * @return the columns specified by a name array.
	   */
	
	public MyDataFrame slice(String[] nameArr)
	{
		int indexArr[] = new int[nameArr.length];
		// find the indexes for given names
		for (int j = 0; j < nameArr.length; j++) {
			indexArr[j] = header.indexOf(nameArr[j]);
		}

		ArrayList<String> new_header = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[indexArr.length];
		
		// get all rows for desired column
		for (int i = 0; i < indexArr.length; i++) {
			new_header.add(header.get(indexArr[i]));
			new_columns[i] = columns[indexArr[i]];
		}

		return new MyDataFrame(new_header, new_columns);
	}
	
	//4. Filtering
	
	/**
	   * @param String col, String op, Object o
	   * @return data filtered by applying “col op o” on MyDataFrame object, e.g. “count >
	             10”, “state = ‘IL’”.
	   */
	public MyDataFrame filter(String col, String op, Object o)
	{
		// get the index for given col
		int col_index = header.indexOf(col);

		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];

		for (int k = 0; k < num_col; k++) {
			new_columns[k] = new ArrayList<>();
		}

		// check each row
		for (int i = 0; i < columns[col_index].size(); i++) {
			// check each column to check if col == o
			if ((op.equals("=") || op.equals("==")) && columns[col_index].get(i).equals(String.valueOf(o))) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
				// check each column to check if col >= o
			} else if (op.equals(">=") && Integer.parseInt(columns[col_index].get(i)) >= (int) o) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
				// check each column to check if col >= o
			} else if (op.equals("<=") && Integer.parseInt(columns[col_index].get(i)) <= (int) o) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
				// check each column to check if col > o
			} else if (op.equals(">") && Integer.parseInt(columns[col_index].get(i)) > (int) o) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
				// check each column to check if col < o
			} else if (op.equals("<") && Integer.parseInt(columns[col_index].get(i)) < (int) o) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
				
				// check each column to check if col != o
			} else if ((op.equals("<>") || op.equals("!=")) && !columns[col_index].get(i).equals(String.valueOf(o))) {
				for (int j = 0; j < new_columns.length; j++) {
					new_columns[j].add(columns[j].get(i));
				}
			}
		}
		return new MyDataFrame(header, new_columns);
	}
	
	//5. Indexing
	
	/**
	   * @param index: integer.
	   * @return the rows starting from index.
	   */
	
	public MyDataFrame loc(int index)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		for (int k = 0; k < num_col; k++) {
			new_columns[k] = new ArrayList<>();
		}

		for (int i = index; i < num_row; i++) {
			for (int j = 0; j < new_columns.length; j++) {
				new_columns[j].add(columns[j].get(i));
			}
		}

		return new MyDataFrame(header, new_columns);
	}
	
	/**
	   * @param label: String.
	   * @return the rows starting from label.
	   */
	public MyDataFrame loc(String label) 
	{
		int index = -1;
		for (int i = 0; i < num_row; i++) {
			for (int j = 0; j < num_col; j++) {
				if (columns[j].get(i).equals(label)) {
					index = i;
				}
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		for (int k = 0; k < num_col; k++) {
			new_columns[k] = new ArrayList<>();
		}

		for (int i = index; i < num_row; i++) {
			for (int j = 0; j < num_col; j++) {
				new_columns[j].add(columns[j].get(i));
			}
		}

		return new MyDataFrame(header, new_columns);
	}
	
	/**
	   * @param from: Integer.
	   * @param to: Integer.
	   * @return the rows between from and to (including from and to).
	   */
	public MyDataFrame loc(int from, int to)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		for (int k = 0; k < num_col; k++) {
			new_columns[k] = new ArrayList<>();
		}

		for (int i = from; i <= to; i++) {
			for (int j = 0; j < num_col; j++) {
				new_columns[j].add(columns[j].get(i));
			}
		}

		return new MyDataFrame(header, new_columns);
		
	}
	
	/**
	   * @param from: String.
	   * @param to: String.
	   * @return the rows between from and to (including from and to).
	   */
	public MyDataFrame loc(String from, String to)
	{
		int start = -1;
		int end = -1;
		for (int i = 0; i < num_row; i++) {
			for (int j = 0; j < num_col; j++) {
				if (columns[j].get(i).equals(from) && start == -1) {
					start = i;
				} 
				else if (columns[j].get(i).equals(to)) {
					end = i;
				} 
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		
		for (int k = 0; k < num_col; k++) {
			new_columns[k] = new ArrayList<>();
		}

		for (int i = start; i <= end; i++) {
			for (int j = 0; j < num_col; j++) {
				new_columns[j].add(columns[j].get(i));
			}
		}

		return new MyDataFrame(header, new_columns);
	}
	
	//6. Sorting
	
	//convert string to int
	public ArrayList<Integer> convert(ArrayList<String> a)
	{
		 ArrayList<Integer> b = new ArrayList<Integer>();
	
		 for (String myInt : a) 
         { 
           b.add(Integer.valueOf(myInt)); 
         }
   
		return b;
	}
	
	/**
	   * @param index: Integer.
	   * @return the data sorted by the column specified by index.
	   */
	
	//put index and the specific column into a hashmap for later sorting
	public HashMap<Integer, Integer> hashmapInt(int index)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		ArrayList<Integer> new_col= new ArrayList<Integer>();
		
		new_col = convert(columns[index]);
		
		for(int i = 0; i < num_row; i++)
        {
			hm.put(i, new_col.get(i));        
        }
		
		return hm;
	}
	
	public HashMap<Integer, String> hashmapStr(int index)
	{
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		
		for(int i = 0; i < num_row; i++)
        {
			hm.put(i, columns[index].get(i));        
        }
		
		return hm;
	}
	
	public MyDataFrame sort(int index)
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		
		for (int i = 0; i < num_col; i++) 
		{
			new_columns[i] = new ArrayList<>();
		}
		
		//when the index indicates year or count, return df sorted by interger, otherwise return df sorted by string
		if (header.get(index).equals("Year")|| header.get(index).equals("Count"))
		{
			HashMap<Integer, Integer> hm = hashmapInt(index);
				 
		    // Create a list from elements of HashMap 
	        List<Map.Entry<Integer, Integer> > list = 
	    		  new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet()); 
	
	        // Sort the list (column values)
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() 
	        {   
	        	public int compare(Map.Entry<Integer, Integer> o1,  
	                             Map.Entry<Integer, Integer> o2) 
	        	{ 
	        		return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
		        
		    // put data from sorted list to hashmap  
		    HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>(); 
		    for (Map.Entry<Integer, Integer> aa : list) 
		    { 
		    	temp.put(aa.getKey(), aa.getValue()); 
		    } 
		      
		    for (Integer key :temp.keySet()) 
		    {
		    	for (int i =0; i< num_col;i++) 
			    {
		    		new_columns[i].add(columns[i].get(key));
			    }		      
		    }		 	      
		}
		else
		{
			HashMap<Integer, String> hm = hashmapStr(index);
			 
		    // Create a list from elements of HashMap 
	        List<Map.Entry<Integer, String> > list = 
	    		  new LinkedList<Map.Entry<Integer, String> >(hm.entrySet()); 
	
	        // Sort the list (column values)
	        Collections.sort(list, new Comparator<Map.Entry<Integer, String> >() 
	        {   
	        	public int compare(Map.Entry<Integer, String> o1,  
	                             Map.Entry<Integer, String> o2) 
	        	{ 
	        		return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
		        
		    // put data from sorted list to hashmap  
		    HashMap<Integer, String> temp = new LinkedHashMap<Integer, String>(); 
		    for (Map.Entry<Integer, String> aa : list) 
		    { 
		    	temp.put(aa.getKey(), aa.getValue()); 
		    } 
		      
		    for (Integer key :temp.keySet()) 
		    {
		    	for (int i =0; i< num_col;i++) 
			    {
		    		new_columns[i].add(columns[i].get(key));
			    }		      
		    }		 	      
		}
		     
   	 return  new MyDataFrame(header, new_columns);
	}
	
	/**
	   * @param name: String.
	   * @return the data sorted by the column specified by name.
	   */
	public MyDataFrame sort(String name)
	{
		// find index
		int index = -1;
		
		for (int j = 0; j < num_col; j++) 
		{
			if (header.get(j).equals(name))
				index = j;
		}
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
		
		for (int i = 0; i < num_col; i++) 
		{
			new_columns[i] = new ArrayList<>();
		}
		
		//when the index indicates year or count, return sorted df, otherwise return the original df
		if (name.equals("Year")|| name.equals("Count"))
		{
			HashMap<Integer, Integer> hm = hashmapInt(index);
				 
		    // Create a list from elements of HashMap 
	        List<Map.Entry<Integer, Integer> > list = 
	    		  new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet()); 
	
	        // Sort the list (column values)
	        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() 
	        {   
	        	public int compare(Map.Entry<Integer, Integer> o1,  
	                             Map.Entry<Integer, Integer> o2) 
	        	{ 
	        		return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
		        
		    // put data from sorted list to hashmap  
		    HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>(); 
		    for (Map.Entry<Integer, Integer> aa : list) 
		    { 
		    	temp.put(aa.getKey(), aa.getValue()); 
		    } 
		      
		    for (Integer key :temp.keySet()) 
		    {
		    	for (int i =0; i< num_col;i++) 
			    {
		    		new_columns[i].add(columns[i].get(key));
			    }      
		    }
		 	      
		}
		else
		{
			HashMap<Integer, String> hm = hashmapStr(index);
			 
		    // Create a list from elements of HashMap 
	        List<Map.Entry<Integer, String> > list = 
	    		  new LinkedList<Map.Entry<Integer, String> >(hm.entrySet()); 
	
	        // Sort the list (column values)
	        Collections.sort(list, new Comparator<Map.Entry<Integer, String> >() 
	        {   
	        	public int compare(Map.Entry<Integer, String> o1,  
	                             Map.Entry<Integer, String> o2) 
	        	{ 
	        		return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
		        
		    // put data from sorted list to hashmap  
		    HashMap<Integer, String> temp = new LinkedHashMap<Integer, String>(); 
		    for (Map.Entry<Integer, String> aa : list) 
		    { 
		    	temp.put(aa.getKey(), aa.getValue()); 
		    } 
		      
		    for (Integer key :temp.keySet()) 
		    {
		    	for (int i =0; i< num_col;i++) 
			    {
		    		new_columns[i].add(columns[i].get(key));
			    }		      
		    }		 	      
     	}
		     
   	 return  new MyDataFrame(header, new_columns);
	}
	
	//7. Aggregation
	
	/**
	   * @param index: Integer
	   * @return the minimum element of the column specified by index.
	   */
	public String getMin(int index)
	{
        String minValue;
		MyDataFrame df = sort(index);
		
		// sort column get the first element
		minValue = df.columns[index].get(0);
     	  	
		return minValue;
	}
	
	/**
	   * @param label: String
	   * @return the minimum element of the column specified by label.
	   */
	public String getMin(String label)
	{
		// find index
		int index = -1;
	
		for (int j = 0; j < num_col; j++) 
		{
			if (header.get(j).equals(label))
				index = j;
		}
			
		String minValue;
		
		MyDataFrame df = sort(index);
		minValue = df.columns[index].get(0);
		
		return minValue;
	}
	
	/**
	   * @param index: Integer
	   * @return the maximum element of the column specified by index.
	   */
	public String getMax(int index)
	{
		String maxValue;		
		MyDataFrame df = sort(index);
		
		// sort column get the last element
		maxValue = df.columns[index].get(num_row-1);
	       
	    return maxValue;
	}
    
	/**
	   * @param index: Integer
	   * @return the maximum element of the column specified by label.
	   */
	public Object getMax(String label)
	{
		// find index
		int index = -1;
		
		for (int j = 0; j < num_col; j++) {
			if (header.get(j).equals(label))
				index = j;
		}
		
		String maxValue;
        MyDataFrame df = sort(index);
		
		maxValue = df.columns[index].get(num_row-1);
     	
		return maxValue;
	}		
}
