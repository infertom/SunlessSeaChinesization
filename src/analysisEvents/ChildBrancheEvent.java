package analysisEvents;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChildBrancheEvent {
	private JSONObject childBrancheEvent;
	private String title;
	
	public ChildBrancheEvent(String buffer, String title) {
		super();
		childBrancheEvent = JSON.parseObject(buffer);
		this.title = title;
	}
	
	//获取Description
	public String getDescription(){
		if ( childBrancheEvent.containsKey("Description")){
			return childBrancheEvent.getString("Description");
		}else{
			return "";
		}
	}
	
	//获取Name
	public String getName(){
		if ( childBrancheEvent.containsKey("Name") ){
			return childBrancheEvent.getString("Name");			
		}else{
			return "";
		}
	}
	
	//获取Id
	public String getId(){
		if ( childBrancheEvent.containsKey("Id") ){
			return childBrancheEvent.getString("Id");
		}else{
			return "";
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
		if ( description != null && description.indexOf(par) != -1 ) num++;
		
		//System.out.println(description + "(" + num + "," + description.indexOf(par) + ")" );
		
		return num;
	}
	
	//获取包含片段par的Info
	public String getInfoByDes(String par){
		String description = this.getDescription();
		if ( description != null && description.indexOf(par) != -1 ) {
			return this.getEventInfo();
		}else{
			return null;
		}
	}
	
	//替换Description
	public boolean exchangeDescription(String par, String buffer){
		String description = childBrancheEvent.getString("Description");
		if ( description != null && description.indexOf(par) != -1 ){
			this.childBrancheEvent.put("Description", buffer);
			return true;
		}else {
			return false;
		}
	}
	
	//获取重新构成的JSON-String
	public String getJsonString(){
		return childBrancheEvent.toJSONString();
	}
	
	//获取info，包含Description，Name，Id
	public String getEventInfo(){
		String temp = "\t\t" + this.getTitle() + "\r\n" + 
					  "Name: " + this.getName() + "\r\n" +
					  "Id: " + this.getId() + "\r\n" +
					  "Description: " + this.getDescription() + "\r\n\r\n";
		return temp;
	}
}
