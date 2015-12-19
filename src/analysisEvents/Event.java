package analysisEvents;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Event {
	private JSONObject event;
	JSONArray childBranches;
	String title;
	
	public Event(Object ob, String title){
		super();
		event = (JSONObject) ob;
		childBranches = JSON.parseArray(event.getString("ChildBranches"));
		this.title = title;
	}
	
	//获取Description
	public String getDescription(){
		return event.getString("Description");
	}
	
	//获取Name
	public String getName(){
		return event.getString("Name");
	}
	
	//获取Id
	public String getId(){
		return event.getString("Id");
	}
	
	//获取Title
	public String getTitle(){
		return this.title;
	}
	
	//获取包含片段par的event个数
	public int getDesNum(String par){
		int num = 0;
		String description = event.getString("Description");
		if ( description.indexOf(par) != -1 ) num++;
		
		
		return num;
	}
	
	//替换Description
	public ChildBranche exchangeDescription(String buffer){
		
	}
	
	//获取当前Event的JSONObject
	public JSONObject getJsonObject(){
		return event;
	}
	
}
