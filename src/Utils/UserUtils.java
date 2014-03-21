package Utils;

import handle.Translate;
import httpconnect.HttpConnect;
import object.User;

public class UserUtils {
	public User getUserInfo(String name) throws Exception{
		HttpConnect httpConnect=new HttpConnect();
		Translate translate=new Translate();
		User user=translate.Person_Analysis(httpConnect.getUserInfo(name));
		return user;
	}
}
