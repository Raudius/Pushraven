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
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	/**
	 * Converts the attribute map into a JSON Object
	 * @return
	 */
	public JSONObject toJson() {
		JSONObject obj = new JSONObject(); // Parent object

		obj.putAll(attributes);

		return obj;
	}
	
	/**
	 * Adds an attribute object to the attribute map
	 * @param key Key with which to store the object
	 * @param value Attribute object to be stored
	 * @return
	 */
	public Payload addAttribute(String key, Object value){
		attributes.put(key, value);
		return this;
	}
	
	/**
	 * Adds a map to the attribute map.
	 * The map is converted to a Json Object before saving to the map.
	 * @param key Key with which to store the object
	 * @param map Attribute map to be stored
	 * @return
	 */
	public Payload addAttributeMap(String key, Map<?, ?> map) {
		JSONObject obj = new JSONObject();
		obj.putAll(map);

		return addAttribute(key, obj);
	}
	
	/**
	 * Adds an array (collection) to the attribute map.
	 * The array is converted to a JSON array before saving to the map.
	 * @param key Key with which to store the object
	 * @param arr Attribute array to be stored
	 * @return
	 */
	public Payload addAttributeArray(String key, Collection<?> arr) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(arr);
		return addAttribute(key, jsonArray);
		
	}

	/**
	 * Adds a Payload to the attribute map
	 * The Payload is converted to JSON before saving to the map
	 * @param key Key with which to store the object
	 * @param payload Attribute payload to be stored
	 * @return
	 */
	public Payload addAttributePayload(String key, Payload payload) {
		return addAttribute(key, payload.toJson());
	}
	
	/**
	 * Retrieves the attribute object for a given key.
	 * Remember that Maps and Payloads are converted to JSON Objects AND Collections converted to JSON Arrays
	 * @param key Key with which the attribute will be retrieved
	 * @return
	 */
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	/**
	 * Removes an attribute from the Payload
	 * @param key Key from the attribute to be deleted
	 */
	public void removeAttribute(String key) {
		attributes.remove(key);
	}
}
