package analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.naming.InitialContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Events {
	public static void main(String[] args){
		List<String> allEventTypes = new ArrayList<String>();
		File fileIn = new File("events.txt");
		Scanner in = null;
		StringBuilder buffer = new StringBuilder();
	
		init(allEventTypes);
		try {
			in = new Scanner(fileIn);
			//getEventTypes(in);
			while ( in.hasNextLine() ) {
				buffer.append(in.nextLine());
			}
			analyEvents(buffer, allEventTypes);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		System.out.println("Done");
	}
	
	private static void init(List<String> list){
		if ( list != null ){
			list.add("DefaultEvent");
			list.add("ParentEvent");
			list.add("RareDefaultEvent");
			list.add("SuccessEvent");
			list.add("RareSuccessEvent");
		}
	}
	
	private static void getEventTypes(Scanner in){
		String tempStr;
		Set<String> eventsSet = new HashSet<String>();
		
		while ( in.hasNext() )
		{
			tempStr = in.next();
			if ( tempStr.contains("Event") ){
				eventsSet.add(tempStr);
			}
		}
		
		for ( String s: eventsSet ){
			System.out.println(s);
		}
	}
	
	private static void analyEvents(StringBuilder buffer, List<String> allEventTypes) throws FileNotFoundException{
		String fileName;
		File fileOut = new File("analy/out.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		PrintStream out = new PrintStream(fileOutputStream);
		int num = 1;
	
		JSONArray jsonArray = JSON.parseArray(buffer.toString());
		for ( Object ob : jsonArray ){
			JSONObject jsonObject = (JSONObject) ob;
//			fileName = "analy/" + jsonObject.getString("Name") + "(" + jsonObject.getString("Id") + ")" + ".txt";
			fileName = "analy/" + "(" + (num++) + ")" + ".txt";
			fileOut = new File(fileName);
			fileOutputStream = new FileOutputStream(fileOut);
			out = new PrintStream(fileOutputStream);
			
			printInfo(out, jsonObject);
			if ( jsonObject.containsKey("ChildBranches") ){
				analyChildBranches(jsonObject.getString("ChildBranches"), out, allEventTypes);
			}
		}
	}
	
	private static void analyChildBranches(String buffer, PrintStream out, List<String> allEventTypes){
		JSONArray jsonSArray = JSON.parseArray(buffer);
		int num = 1;
		
		for ( Object ob : jsonSArray ){
			out.println("\r\n\r\n\tChildBranches" + num++);
			JSONObject jsonObject = (JSONObject) ob;
			printInfo(out, jsonObject);
			analyChildEvents(jsonObject, out, allEventTypes);
		}
	}
	
	private static void analyChildEvents(JSONObject jsonObject, PrintStream out, List<String> allEventTypes){
		JSONObject eventJsonObject;
		for ( String eventType : allEventTypes ){
			if ( jsonObject.containsKey(eventType) ){
				eventJsonObject = jsonObject.getJSONObject(eventType);
				out.println("\t\t" + eventType);
				printInfo(out, eventJsonObject);
			}
		}
	}
	
	private static void printInfo(PrintStream out, JSONObject jsonObject){
		if ( jsonObject.containsKey("Name") ) out.println("Name:" + jsonObject.getString("Name"));
		if ( jsonObject.containsKey("Id") ) out.println("Id:" + jsonObject.getString("Id"));
		if ( jsonObject.containsKey("Description") ) out.println("Description:" + jsonObject.getString("Description"));
		out.println();
	}
}
