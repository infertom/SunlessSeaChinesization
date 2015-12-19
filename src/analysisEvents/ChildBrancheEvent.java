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
		return childBrancheEvent.getString("Description");
	}
	
	//获取Name
	public String getName(){
		return childBrancheEvent.getString("Name");
	}
	
	//获取Id
	public String getId(){
		return childBrancheEvent.getString("Id");
	}
	
	//获取Title
	public String getTitle(){
		return this.title;
	}
	
	//获取包含片段par的event个数
	public int getDesNum(String par){
		int num = 0;
		String description = childBrancheEvent.getString("Description");
		if ( description.indexOf(par) != -1 ) num++;
		
		return num;
	}
	
	//替换Description
	public boolean exchangeDescription(String par, String buffer){
		String description = childBrancheEvent.getString("Description");
		if ( description.indexOf(par) != -1 ){
			this.childBrancheEvent.put("Description", buffer);
			return true;
		}else {
			return false;
		}
	}
	
	//获取重新构成的JSONObject
	public JSONObject getJsonObject(){
		return childBrancheEvent;
	}
}
