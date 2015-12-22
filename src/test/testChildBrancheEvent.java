package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import tool.READFILEALL;
import analysisEvents.ChildBrancheEvent;

public class testChildBrancheEvent {
	private static String fileInName = "test/testChildBrancheEvent.txt";
	private static StringBuilder testStr = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		testStr = READFILEALL.readFileAl(fileInName);
		System.out.println(testStr.toString());
		
		//获取Description
		ChildBrancheEvent event = new ChildBrancheEvent(testStr.toString(), "1");
		System.out.println(event.getDescription());
		
		//获取包含片段的个数
		System.out.println(event.getDesNum("noise su"));
		
		//替换Description
		if ( event.exchangeDescription("noise su", "dddd") ){
			System.out.println(event.getDescription());
		}else{
			System.err.println("exchange error");
		}
		
		//获取JSON-String
		File file = new File("test/out/childBrancheEvent.txt");
		FileOutputStream outputStream = new FileOutputStream(file);
		FileWriter out = new FileWriter(file);
		out.write(event.getJsonString().trim());
		out.close();
		System.out.println(event.getJsonString());
	
		//获取info
		System.out.println("Info\n" + event.getEventInfo());
	}

}
