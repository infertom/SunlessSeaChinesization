package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

import analysisEvents.ChildBranche;
import tool.READFILEALL;

public class testChildBranche {
	private static String fileInName = "test/testChildBranche.txt";
	private static StringBuilder testStr = new StringBuilder();

	public static void main(String[] args) throws IOException {
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
		File file = new File("test/out/childBranche.txt");
		FileOutputStream outputStream = new FileOutputStream(file);
		FileWriter out = new FileWriter(file);
		out.write(event.getJsonString().trim());
		out.close();
		System.out.println(event.getJsonString());
	
		//获取Info
		System.out.println("\nInfo:\n" + event.getInfo().trim());
	}
}
