package httpconnect;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpConnect {

	public JSONObject getUserInfo(String name) throws Exception {
		/**StringBuilder str = new StringBuilder();*/
		
		String url="http://10.0.2.2:8080/mywebsite/confirm";
		HttpGet httpGet=new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();

		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpResponse response = client.execute(httpGet);
		
		JSONObject obj=new JSONObject(EntityUtils.toString(response.getEntity()));
		return obj;
		
		/**
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			reader.close();
		}

		return str.toString();*/
	}
	
	
}
