package test;

import com.alibaba.fastjson.JSONObject;

import tool.READFILEALL;
import analysisEvents.ChildBrancheEvent;

public class testChildBrancheEvent {
	private static String fileInName = "test.txt";
	static StringBuilder testStr = new StringBuilder();
	
	public static void main(String[] args) {
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
		
		//获取JSONObject
		JSONObject object = event.getJsonObject();
		System.out.println(object.toJSONString());
	}

}
