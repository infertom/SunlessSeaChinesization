package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

import analysisEvents.AllEvents;
import analysisEvents.Event;
import tool.READFILEALL;

public class testAll {
	private static String fileInName = "test/testEvents.txt";
	private static StringBuilder testStr = new StringBuilder();
	private static String fileOutPath = "test/out/";
	
	public static void main(String[] args) throws IOException {
		testStr = READFILEALL.readFileAl(fileInName);
		AllEvents allEvents = new AllEvents(testStr.toString());
		System.out.println(testStr.toString().substring(0, 100));
		
		
		//查找
		String par;
		Scanner in = new Scanner(System.in, "utf-8");
		System.out.println("enter the string for search:");
		par = in.nextLine();
		for ( Event event : allEvents ){
			if ( event.getDesNum(par) > 0 ) {
				System.out.println(event.getTitle());
			}
		}
		int num = allEvents.getDesNum(par);
		System.out.println(num + " descriptions contain");
		System.out.println("Info:\n" + allEvents.getInfoByDes(par).trim());
		System.out.println("Search Done");
		System.out.println();
		
		//替换
		System.out.println("enter the string to exchange description:");
		String buffer = in.nextLine();
		Boolean flag = false;
		flag = allEvents.exchangeDescription(par, buffer);
		if ( flag ){
			System.out.println("Exchange Done");
		}else{
			System.out.println("Exchange Error");
		}
		System.out.println();

		//输出单个event文件
		for ( Event event : allEvents ){
			File file = new File(fileOutPath+event.getTitle()+".txt");
			FileOutputStream outputStream = new FileOutputStream(file);
			PrintStream out = new PrintStream(outputStream);
			out.println(event.getInfo().trim());
		}
		System.out.println("single event Done");
		System.out.println();
		
		//输出整合的JSON文件
		File file = new File(fileOutPath+"allEvents.txt");
		FileWriter out = new FileWriter(file);
		out.write(allEvents.getJsonString());
		out.close();
		System.out.println("allEvents.txt Done");
		System.out.println();
		
		System.out.println("All Done");
	}	
}
