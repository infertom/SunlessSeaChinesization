package analysisEvents;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChildBranche {
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

	// 替换Description
	public Boolean exchangeDescription(String par, String buffer) {
		if (this.getDesNum(par) == 1) {
			String description = childBranche.getString("Description");
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

	// 获取当前Event的JSONObject
	public JSONObject getJsonObject() {
		for (ChildBrancheEvent e : childBrancheEvents) {
			childBranche.put(e.getTitle(), e.getJsonObject());
		}

		return childBranche;
	}

	// 获取info，包含Description，Name，Id
	public String getInfo() {
		String temp = "\t" + this.getTitle() + "\r\n" + 
					  "Name: " + this.getName() + "\r\n" +
					  "Id: " + this.getId() + "\r\n" +
					  "Description: " + this.getDescription() + "\r\n\r\n";
		for (ChildBrancheEvent e : childBrancheEvents) {
			temp += e.getInfo();
		}
		temp += "\r\n\r\n";
		
		return temp;
	}
}
