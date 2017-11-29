package us.raudi.pushraven;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The payload class implements a collection of "attributes".
 * These attributes are stored in a map which can then be exported into JSON.
 * @author Raudius
 */
@SuppressWarnings("unchecked")
public class Payload {
	private Map<String, Object> attributes = new HashMap<>();
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject(); // Parent object

		obj.putAll(attributes);

		return obj;
	}
	
	public Payload addAttribute(String key, Object value){
		attributes.put(key, value);
		return this;
	}
	
	public Payload addAttributeMap(String key, Map<?, ?> map) {
		JSONObject obj = new JSONObject();
		obj.putAll(map);

		return addAttribute(key, obj);
	}
	
	public Payload addAttributeArray(String key, Collection<?> arr) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(arr);
		return addAttribute(key, jsonArray);
		
	}

	public Payload addAttributePayload(String key, Payload payload) {
		return addAttribute(key, payload.toJson());
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	public void removeAttribute(String key) {
		attributes.remove(key);
	}
}
