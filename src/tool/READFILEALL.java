package tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class READFILEALL {
	public static File file = null;
	public static Scanner in = null;
	public static StringBuilder temp = null;
	public static StringBuilder readFileAl(String fileInName){
		try {
			temp = new StringBuilder();
			file = new File(fileInName);
			in = new Scanner(file, "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("tool:" + e.toString());
		}
		
		while ( in.hasNextLine() ) 
		{
			temp.append(in.nextLine().trim());
			//System.out.println(temp.toString()); 
		}
		
		return temp;
	}
}
