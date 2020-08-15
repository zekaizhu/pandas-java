package java_project2;

import java.io.IOException;

public class TestPandas {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		MyDataFrame df = MyPandas.readCSV("sample.csv");
		MyDataFrame df1 = MyPandas.readCSV("sample.csv");

		System.out.println("Test 0: Concat");
		MyPandas.print_df(MyPandas.concat(df, df1));
		System.out.println();
		
		// Write file
		
		MyPandas.writeCSV(MyPandas.concat(df, df1),"Output.csv");
		System.out.println();
		
		// 1. head
		System.out.println("Test 1: head");
		MyDataFrame data = df.head(10);
		MyPandas.print_df(data);
		System.out.println();
		System.out.println();

		// 2. tail
		System.out.println("Test 2: tail");
		MyPandas.print_df(df.tail(8));
		System.out.println();
		System.out.println();

		// 3. dtype
		System.out.println("Test 3: dtype");
		System.out.println(df.dType(1));
		System.out.println(df.dType("Year"));
		System.out.println();

		// 4. slicing
		System.out.println("Test 4: slicing");
		// MyPandas.writeCSV(df.slice("State"),"Output.csv");
		MyPandas.print_df(df.slice(1).head(10));
		System.out.println();
		MyPandas.print_df(df.slice("Name"));
		System.out.println();
		System.out.println();

		int[] a = {1, 2};
		MyPandas.print_df(df.slice(a));
		// String[] b = {"Count","Gender"};
		// MyPandas.writeCSV(df.slice(b),"Output.csv");
		String[] c = { "State", "Gender" };
		MyPandas.print_df(df.slice(c));
		System.out.println();
		System.out.println();

		// 5. Filtering
		System.out.println("Test 5: filtering");
		// MyPandas.writeCSV(df.filter("Gender", "=", "F"),"Output.csv");
		MyPandas.print_df(df.filter("Gender", "=", "F"));
		System.out.println();
		MyPandas.print_df(df.filter("Count", ">", 50));
		System.out.println();
		MyPandas.print_df(df.filter("State", "<>", "IL"));
		System.out.println();
		System.out.println();

		// 6. indexing
		System.out.println("Test 6: indexing");
		// MyPandas.writeCSV(df.loc("IN", "IL"),"Output.csv");
		MyPandas.print_df(df.loc("IN", "IL"));
		System.out.println();
		MyPandas.print_df(df.loc(0, 10));
		System.out.println();
		MyPandas.print_df(df.loc(2));
		System.out.println();
		System.out.println();

		// 7. sorting
		System.out.println("Test 7: indexing");
		// MyPandas.writeCSV(df.sort("Count"),"Output.csv");
		MyPandas.print_df(df.sort(3));
		System.out.println();
		MyPandas.print_df(df.sort(4));
		System.out.println();
		MyPandas.print_df(df.sort("Year"));
		System.out.println();
		MyPandas.print_df(df.sort("Count"));
		System.out.println();
		System.out.println();
		
		// 8. Aggregation
		System.out.println("Test 8: aggregation");
		System.out.println(df.getMin(0));
		System.out.println(df.getMin(4));
		System.out.println(df.getMin(3));
		System.out.println(df.getMin("Count"));
		System.out.println(df.getMin("Year"));
		System.out.println(df.getMin("Name"));
		System.out.println(df.getMin("State"));
		System.out.println(df.getMax(2));
		System.out.println(df.getMax(3));
		System.out.println(df.getMax(4));
		System.out.println(df.getMax("Count"));
		System.out.println(df.getMax("Year"));
		System.out.println(df.getMax("Name"));
		System.out.println(df.getMax("State"));
		System.out.println();
	}
}
