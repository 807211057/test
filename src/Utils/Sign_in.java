package Utils;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
 
public class Sign_in {
	public boolean sign_in(String user_name,String user_password) throws JSONException, ClientProtocolException, IOException{
		Boolean result=false;
		String URL="http://192.168.1.103:8080/mywebsite/confirm";
		JSONObject json=new JSONObject();
		json.put("User_name", user_name);
		json.put("User_password", user_password);
		
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost post=new HttpPost(URL);
        post.setEntity(new StringEntity(json.toString()));
        HttpResponse hr = httpClient.execute(post);

        if(hr.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
        	JSONObject obj=new JSONObject(EntityUtils.toString(hr.getEntity()));
        	result=obj.getBoolean("info");
		}
		return result;
	}
}
