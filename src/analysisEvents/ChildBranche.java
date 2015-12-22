package analysisEvents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChildBranche implements Iterable<ChildBrancheEvent>{
	private JSONObject childBranche;
	private List<ChildBrancheEvent> childBrancheEvents = new ArrayList<ChildBrancheEvent>();
	String title = "";

	public ChildBranche(Object ob, String title) {
		super();
		childBranche = (JSONObject) ob;
		getEvents();
		this.title = title;
	}

	public ChildBranche(String str, String title) {
		super();
		childBranche = JSON.parseObject(str);
		getEvents();
		this.title = title;
	}

	// 获取当前ChildBranche的所有Event
	private void getEvents() {
		for (String type : CONSTANT.EVENTTYPE) {
			if (childBranche.containsKey(type)) {
				ChildBrancheEvent childBrancheEvent = new ChildBrancheEvent(
						childBranche.getString(type), type);
				childBrancheEvents.add(childBrancheEvent);
				//System.out.println(childBrancheEvent.getDescription());
			}
		}
	}

	// 获取Description
	public String getDescription() {
		if (childBranche.containsKey("Description")) {
			return childBranche.getString("Description");
		} else {
			return "";
		}
	}

	// 获取Name
	public String getName() {
		if (childBranche.containsKey("Name")) {
			return childBranche.getString("Name");
		} else {
			return "";
		}
	}

	// 获取Id
	public String getId() {
		if (childBranche.containsKey("Id")) {
			return childBranche.getString("Id");
		} else {
			return "";
		}
	}

	// 获取Title
	public String getTitle() {
		return this.title;
	}

	// 获取包含片段par的event个数
	public int getDesNum(String par) {
		int num = 0;
		String description = this.getDescription();
		if ( description != null && description.indexOf(par) != -1)
			num++;
		for (ChildBrancheEvent e : childBrancheEvents) {
			num += e.getDesNum(par);
		}

		return num;
	}
	
	//获取包含片段par的Info
	public String getInfoByDes(String par){
		if ( this.getDesNum(par) == 1 ){
			String description = this.getDescription();
			String temp = "";
			if ( description != null && description.indexOf(par) != -1 ){
				temp = this.getEventInfo();
			}else{
				for ( ChildBrancheEvent e : childBrancheEvents ){
					temp = e.getInfoByDes(par);
					if ( temp != null ) break;
				}
			}
			return temp;
		}else{
			return null;
		}
	}

	// 替换Description
	public Boolean exchangeDescription(String par, String buffer) {
		if (this.getDesNum(par) == 1) {
			String description = this.getDescription();
			if (description != null && description.indexOf(par) != -1) {
				childBranche.put("Description", buffer);
			} else {
				for (ChildBrancheEvent e : childBrancheEvents) {
					e.exchangeDescription(par, buffer);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	// 获取当前Event的JSONO-String
	public String getJsonString() {
		for (ChildBrancheEvent e : childBrancheEvents) {
			childBranche.put(e.getTitle(), JSON.parseObject(e.getJsonString()));
		}

		return childBranche.toJSONString();
	}

	// 获取info，包含Description，Name，Id
	public String getEventInfo(){
		String temp = "\t\t" + this.getTitle() + "\r\n" + 
				  "Name: " + this.getName() + "\r\n" +
				  "Id: " + this.getId() + "\r\n" +
				  "Description: " + this.getDescription() + "\r\n\r\n";
		return temp;
	}
	
	//获取包含event及分支的所有信息
	public String getInfo() {
		String temp = this.getEventInfo();
		for (ChildBrancheEvent e : childBrancheEvents) {
			temp += e.getEventInfo();
		}
		temp += "\r\n\r\n";
		
		return temp;
	}
	
	
	public Iterator<ChildBrancheEvent> iterator(){
		return childBrancheEvents.iterator();
	}
}
