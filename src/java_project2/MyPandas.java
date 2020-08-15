package java_project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyPandas {
	// Read a file and store it into a MyDataFrame object.
	// Possible data types: Integer and String. You can assume that the first row of
	// the .csv file is a header.
	public static MyDataFrame readCSV(String path) throws IOException {

		MyDataFrame df = null;

		// load the names.csv
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		

			// read first line
			String first_line = br.readLine();
			String[] headers = first_line.split(",");
			int cols = headers.length;
			
			// add headers
			ArrayList<String> header = new ArrayList<String>();
			for (int i = 0; i < headers.length; i++) {
				header.add(headers[i]);
			}
			
			
						
			// create an array to save columns
			@SuppressWarnings("unchecked")
			ArrayList<String> columns[] = new ArrayList[cols];

			for (int i = 0; i < cols; i++) {
				columns[i] = new ArrayList<>();
			}

			// read remaining lines
			String line = br.readLine();
			// System.out.println(line);
			while (line != null) {
				String[] arr = line.split(",");

				for (int i = 0; i < cols; i++) {
					columns[i].add(arr[i]);
				}
				line = br.readLine();
				// System.out.println(line);
			}

			// store data into a MyDataFrame object
			df = new MyDataFrame(header, columns);

		} catch (IOException e) {
			System.out.println("File can not be loaded");
		}

		return df;
	}

	// Write a MyDataFrame object to file specified by path.
	public static void writeCSV(MyDataFrame data, String path) throws IOException 
	{
		try 
        {   
	    	 // set writer 
		     File file = new File(path);
		     FileOutputStream fos = new FileOutputStream(file);
		     PrintWriter pw = new PrintWriter(fos);
            
		     ArrayList<String> header = data.header;
			 ArrayList<String>[] columns = data.columns;
		     // print to file		
			 
			 for (int i = 0; i < header.size(); i++) 
				{
				 pw.printf("%-10s",header.get(i)+",");
				}
				
			 for (int j = 0; j< columns[0].size(); j++) 
			 {
				 pw.println();
				 for (int k = 0; k < columns.length; k++) 
				 {			
					 pw.printf("%-10s",columns[k].get(j)+",");
				 }
			 }
		  
             pw.flush();
	         pw.close();
	         fos.close();
	         System.out.println("Results have been documented in "+ path);
        }     
        catch (IOException e)
        {
       	 System.out.println("File can not be written");
        }   
	}

	// Concatenate two MyDataFrame object along rows.
	// Returns the concatenated MyDataFrame.
	public static MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) 
	{
		int num_col = df1.num_col;
		@SuppressWarnings("unchecked")
		ArrayList<String> new_columns[] = new ArrayList[num_col];
				
		for (int i = 0; i < df1.num_col; i++) 
		{
			new_columns[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < df2.num_row; i++)
		{
			for (int j = 0; j < num_col; j++)
			{
				df1.columns[j].add(df2.columns[j].get(i));
			}
		}
		
		return df1;
	}

	public static void print_df(MyDataFrame df) 
	{
		ArrayList<String> header = df.header;
		ArrayList<String>[] columns = df.columns;
		
		for (int i = 0; i < header.size(); i++) 
		{
			System.out.printf("%-10s", header.get(i));
		}
		
		for (int j = 0; j< columns[0].size(); j++) 
		{
			System.out.println();
			for (int k = 0; k < columns.length; k++) 
			{			
				System.out.printf("%-10s", columns[k].get(j));
			}
			
		}
		
		
	}

}
