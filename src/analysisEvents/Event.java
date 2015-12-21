package analysisEvents;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

	
public class Event {
	private JSONObject event;
	private List<ChildBranche> childBranches = new ArrayList<ChildBranche>();
	String title = "";
	
	public Event(Object ob, String title){
		super();
		event = (JSONObject) ob;
		getChildBranches();
		this.title = title;
	}
	
	public Event(String str, String title){
		super();
		event = JSON.parseObject(str);
		getChildBranches();
		this.title = title;
	}
	
	//获取当前Event的所有ChildBranche
	void getChildBranches(){
		if ( event.containsKey("ChildBranches") ){
			JSONArray branchesaArray = JSON.parseArray(event.getString("ChildBranches"));
			int num = 1;
			
			for ( Object cb : branchesaArray ){
				ChildBranche childBranche = new ChildBranche(cb, "ChildBranches " + num);
				//System.out.println(childBranche.getInfo());
				childBranches.add(childBranche);
				num++;
			}
		}
	}
	
	//获取Description
	public String getDescription(){
		if ( event.containsKey("Description") ){
			return event.getString("Description");
		}else{
			return null;
		}
	}
	
	//获取Name 
	public String getName(){
		if ( event.containsKey("Name") ){
			return event.getString("Name");
		}else{
			return null;
		}
	}
	
	//获取Id
	public String getId(){
		if ( event.containsKey("Id") ){
			return event.getString("Id");
		}else{
			return null;
		}
	}
	
	//获取Title
	public String getTitle(){
		return this.title;
	}
	
	//获取包含片段par的event个数
	public int getDesNum(String par){
		int num = 0;
		String description = this.getDescription();
		
		if ( description != null && description.indexOf(par) != -1 ) {
			num++;
		}
		for ( ChildBranche e : childBranches ){
			num += e.getDesNum(par);
		}
		
		return num;
	}

	//替换Description
	public Boolean exchangeDescription(String par, String buffer){
		if ( this.getDesNum(par) == 1 ){
			String descriptionString = this.getDescription();
			if ( descriptionString != null && descriptionString.indexOf(par) != -1 ){
				event.put("Description", buffer);
			}else{
				for ( ChildBranche e : childBranches ){
					e.exchangeDescription(par, buffer);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	//获取当前Event的JSONObject
	public JSONObject getJsonObject(){
		JSONArray ja = new JSONArray();
		for ( ChildBranche e : childBranches ){
			ja.add(e.getJsonObject());
		}
		event.put("ChildBranches", ja.toJSONString());
		
		return event;
	}
	
	// 获取info，包含Description，Name，Id
	public String getInfo(){
		String temp = "\t" + this.getTitle() + "\r\n" + 
				  "Name: " + this.getName() + "\r\n" +
				  "Id: " + this.getId() + "\r\n" +
				  "Description: " + this.getDescription() + "\r\n\r\n";
		for ( ChildBranche e : childBranches ){
			temp += e.getInfo();
		}
		temp += "\r\n\r\n\r\n";
		
		return temp;
	}	
}
