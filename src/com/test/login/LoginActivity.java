package com.test.login;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import Utils.Sign_in;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	//定义文本框和按钮
	EditText etName,etPass;
	Button bnLogin;
	Runnable GoIntoNext = null;
	Runnable Error = null;
	Runnable Confirm = null;
	Handler TaskManager = null;
	TextView text = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//获取文本框和按钮
		etName = (EditText)findViewById(R.id.userEditText);
		etPass = (EditText)findViewById(R.id.pwdEditText);
		bnLogin = (Button)findViewById(R.id.bnLogin);
		text = (TextView)findViewById(R.id.TextView);
		TaskManager = new Handler();
		GoIntoNext = new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
			}
			
		};
		Error = new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				DialogUtil.showDialog(LoginActivity.this,"用户名或密码错误，请重新输入！",false);
			}
		};
		Confirm = new Runnable() {
			
			@Override
			public void run() {
				if(validate())//验证用户名密码是否为空
				{
					if(loginPro())//验证用户名和密码
						TaskManager.post(GoIntoNext);
					else
						TaskManager.post(Error);
				}
			}
		};
		
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new Thread(Confirm).start();
			}
		});
	}
	
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this,"请填入用户名！",false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if(pwd.equals(""))
		{
			DialogUtil.showDialog(this,"请填入密码！",false);
			return false;
		}
		return true;
	}
	
	private boolean loginPro()
	{
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();
		Sign_in sign = new Sign_in();
		try {
			return sign.sign_in(username,pwd);
		} catch (ClientProtocolException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}

}
