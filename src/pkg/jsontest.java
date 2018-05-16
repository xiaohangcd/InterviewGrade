package pkg;

import java.util.Iterator;

import net.sf.json.JSONObject;

public class jsontest {
	
	public static void main(String[] args) {
		JSONObject object = new JSONObject();
		object.put("key", 30);
		object.put("nihao", "hello");
		Iterator<String> iterator = object.keys();
		while (iterator.hasNext()) {
			System.out.println(object.getString(iterator.next()));
			
			
		}

	}

}
