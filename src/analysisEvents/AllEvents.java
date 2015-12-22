package analysisEvents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


public class AllEvents implements Iterable<Event>{
	private JSONArray allEvents;
	private List<Event> events = new ArrayList<Event>();
	
	public AllEvents(String str){
		allEvents = JSON.parseArray(str);
		getEvents();
	}
	
	//获取所有event
	private void getEvents(){
		int num = 1;
		
		for (Object ob : allEvents ){
			Event event = new Event(ob, "(" + num + ")");
			events.add(event);
			num++;
		}
	}
	
	//获取包含片段par的event个数
	public int getDesNum(String par){
		int num = 0;
		for ( Event event : events ){
			num += event.getDesNum(par);
		}
		
		return num;
	}

	//获取包含片段par的Info
	public String getInfoByDes(String par){
		if ( this.getDesNum(par) == 1 ){
			String temp = "";
			for ( Event e : events ){
				temp = e.getInfoByDes(par);
				if ( temp != null ) break;
			}
			return temp;
		}else{
			return null;
		}
	}
	
	//替换Description
	public Boolean exchangeDescription(String par, String buffer){
		if ( getDesNum(par) == 1 ){
			for ( Event e : events ){
				e.exchangeDescription(par, buffer);
			}
			return true;
		}else{
			return false;
		}
	}
	
	//获取所有event的JSON-String
	public String getJsonString(){
		JSONArray jArray = new JSONArray();
		for ( Event event : events ){
			jArray.add(JSON.parseObject(event.getJsonString()));
		}
		
		return jArray.toJSONString();
	}
	
	public Iterator<Event> iterator(){
		return events.iterator();
	}
}
