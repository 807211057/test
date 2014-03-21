package handle;

import org.json.JSONException;
import org.json.JSONObject;

import object.User;

public class Translate {
	
	public User Person_Analysis(JSONObject obj) throws JSONException{
		User person=new User();
		person.setName(obj.getString("name"));
		person.setPassword(obj.getString("sex"));
		person.setSchool(obj.getString("school"));
		person.setAge(Integer.getInteger(obj.getString("age")));
		return person;
	}
	
	public String Person_Code(User person){
		String JsonStr=null;
		JSONObject obj=new JSONObject();
		try {
			obj.put("User_name", person.getName());
			obj.put("User_password",person.getPassword());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonStr=obj.toString();
		return JsonStr;
	}
	
}
