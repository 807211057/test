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
	//�����ı���Ͱ�ť
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
		//��ȡ�ı���Ͱ�ť
		etName = (EditText)findViewById(R.id.userEditText);
		etPass = (EditText)findViewById(R.id.pwdEditText);
		bnLogin = (Button)findViewById(R.id.bnLogin);
		text = (TextView)findViewById(R.id.TextView);
		TaskManager = new Handler();
		GoIntoNext = new Runnable() {

			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
			}
			
		};
		Error = new Runnable() {
			
			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				DialogUtil.showDialog(LoginActivity.this,"�û���������������������룡",false);
			}
		};
		Confirm = new Runnable() {
			
			@Override
			public void run() {
				if(validate())//��֤�û��������Ƿ�Ϊ��
				{
					if(loginPro())//��֤�û���������
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
			DialogUtil.showDialog(this,"�������û�����",false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if(pwd.equals(""))
		{
			DialogUtil.showDialog(this,"���������룡",false);
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
