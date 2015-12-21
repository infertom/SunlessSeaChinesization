package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import analysisEvents.AllEvents;
import analysisEvents.Event;
import tool.READFILEALL;

public class testAll {
	private static String fileInName = "test/testEvents.txt";
	private static StringBuilder testStr = new StringBuilder();
	private static String fileOutPath = "test/out/";
	
	public static void main(String[] args) throws FileNotFoundException {
		testStr = READFILEALL.readFileAl(fileInName);
		System.out.println(testStr.toString().substring(0, 100));
		
		//输出event文件
		AllEvents allEvents = new AllEvents(testStr.toString());
		for ( Event event : allEvents ){
			File file = new File(fileOutPath+event.getTitle()+".txt");
			FileOutputStream outputStream = new FileOutputStream(file);
			PrintStream out = new PrintStream(outputStream);
			out.println(event.getInfo().trim());
		}	
		
		System.out.println("Done");
	}

}
