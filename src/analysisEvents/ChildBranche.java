package analysisEvents;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChildBranche {
	private JSONObject childBranche;
	private List<ChildBrancheEvent> childBrancheEvents = new ArrayList<ChildBrancheEvent>();
	String title;
	
	public ChildBranche(Object ob, String title) {
		super();
		childBranche = (JSONObject) ob;
		getEvents();
		this.title = title;
	}
	
	//获取当前ChildBranche的所有Event
	private void getEvents(){
		for ( String type : CONSTANT.EVENTTYPE ){
			if ( childBranche.containsKey(type) ){
				ChildBrancheEvent childBrancheEvent = new ChildBrancheEvent(childBranche.getString(type), type);
				childBrancheEvents.add(childBrancheEvent);
			}
		}
	}

	//获取Description
	public String getDescription(){
		return childBranche.getString("Description");
	}
	
	//获取Name
	public String getName(){
		return childBranche.getString("Name");
	}
	
	//获取Id
	public String getId(){
		return childBranche.getString("Id");
	}
	
	//获取Title
	public String getTitle(){
		return this.title;
	}
	
	//获取包含片段par的event个数
	public int getDesNum(String par){
		int num = 0;
		String description = childBranche.getString("Description");
		if ( description.indexOf(par) != -1 ) num++;
		for ( ChildBrancheEvent e : childBrancheEvents ){
			num += e.getDesNum(par);
		}
		
		return num;
	}
	
	//替换Description
	public ChildBranche exchangeDescription(String buffer){
		if ( this.getDesNum(buffer) != 1 ) return this;
		
		String description = childBranche.getString("Description");
		if ( description.indexOf(buffer) != -1 ){
			this.childBranche.put("Description", buffer);
		}else{
			for ( ChildBrancheEvent e : childBrancheEvents ){
				e.exchangeDescription(buffer);
			}
		}
		return this;
	}
	
	//获取当前Event的JSONObject
	public JSONObject getJsonObject(){
		return childBranche;
	}
}
