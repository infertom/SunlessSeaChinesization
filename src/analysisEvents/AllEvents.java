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
	
	private void getEvents(){
		int num = 1;
		
		for (Object ob : allEvents ){
			Event event = new Event(ob, "(" + num + ")");
			events.add(event);
			num++;
		}
	}
	
	public Iterator<Event> iterator(){
		return events.iterator();
	}
}
