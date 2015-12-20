package test;

import com.alibaba.fastjson.JSONObject;

import analysisEvents.ChildBranche;
import tool.READFILEALL;

public class testChildBranche {
	private static String fileInName = "test/testChildBranche.txt";
	private static StringBuilder testStr = new StringBuilder();

	public static void main(String[] args) {
		testStr = READFILEALL.readFileAl(fileInName);
		System.out.println(testStr.toString());

		// 获取Description
		System.out.println("\ngetDescription:");
		ChildBranche event = new ChildBranche(testStr.toString(),
				"ChildBranche1");
		System.out.println(event.getDescription());

		// 获取包含片段的个数
		System.out.println("\ngetDesNum:");
		System.out.println(event.getDesNum("the"));

		// 替换Description
		System.out.println("\nexchangeDes:");
		if (event.exchangeDescription("the", "dddd")) {
			System.out.println(event.getDescription());
		} else {
			System.err.println("exchange error");
		}

		// 获取JSONObject
		System.out.println("getJSONObject:");
		JSONObject object = event.getJsonObject();
		System.out.println(object.toJSONString());
	
		//获取Info
		System.out.println("\nInfo:\n" + event.getInfo().trim());
	}
}
