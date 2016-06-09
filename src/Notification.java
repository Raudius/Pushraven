import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.*;

public class Notification {
	private List<String> targets;
	private HashMap<String, Object> attributes;
	

	
	public Notification(){
		targets = new ArrayList<String>();
		attributes = new HashMap<String, Object>();
	}
	
	
	@SuppressWarnings("unchecked")
	public String toJSON(){
		JSONObject obj = new JSONObject();

		JSONObject not = new JSONObject();
		
		for(Entry<String, Object> e : attributes.entrySet()){
			not.put(e.getKey(), e.getValue());
		}
		
		obj.put("notification", not);
		
		JSONArray ids = new JSONArray();
		for(String str : targets)
			ids.add(str);
		
		

		System.out.println(obj.toString());
		
		obj.put("registration_ids", ids);
		
		return obj.toString();
	}
	
	
	// Notification attributes
	public Notification addAttribute(String key, Object value){
		attributes.put(key, value);
		return this;
	}
	
	public Notification title(String title){
		return addAttribute("title", title);
	}
	public Notification text(String text){
		return addAttribute("text", text);
	}
	public Notification priority(Integer p){
		p = Math.min(p, 10);
		p = Math.max(p, 0);
		
		return addAttribute("priority", p);
	}
	public Notification collapse_key(String key){
		return addAttribute("collapse_key", key);
	}
	public Notification delay_while_idle(Boolean b){
		return addAttribute("delay_while_idle", b);
	}
	public Notification time_to_live(Integer n){
		return addAttribute("time_to_live", n);
	}
	public Notification restricted_package_name(String name){
		return addAttribute("restricted_package_name", name);
	}
	public Notification click_action(String intent_filter){
		return addAttribute("click_action", intent_filter);
	}
	public Notification color(String rgb){
		return addAttribute("color", rgb);
	}
	public Notification tag(String tag){
		return addAttribute("tag", tag);
	}
	
	// Notification target(s)
	public Notification setTargets(List<String> targets){
		this.targets = targets;
		return this;
	}
	public Notification addTargets(List<String> targets){
		this.targets.addAll(targets);
		return this;
	}
	public Notification addTarget(String target){
		this.targets.add(target);
		return this;
	}
	
	
}
