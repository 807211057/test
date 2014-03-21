package com.test.login;

import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.UnderlinePageIndicatorEx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends FragmentActivity{
	private static final String[] CONTENT = new String[] { "推荐菜品", "新菜上架", "发现"};
	
	Button button1;
	GridView grid01Id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		button1 = (Button)findViewById(R.id.mian_button1);
		button1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
				startActivity(intent);
			}
		});
		
		
	
		
		FragmentPagerAdapter adapter = new MainAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        UnderlinePageIndicatorEx uindicator = (UnderlinePageIndicatorEx)findViewById(R.id.indicator_underline);
        indicator.setViewPager(pager);
        uindicator.setViewPager(pager);
        uindicator.setFades(false);
        
        indicator.setOnPageChangeListener(uindicator);
        

       
         

	}

	
    public static String[] getContent() {
		return CONTENT;
	}


	class MainAdapter extends FragmentPagerAdapter {
        public MainAdapter(FragmentManager fm) {
            super(fm);
       
	}

		@Override
		public Fragment getItem(int position) {
			return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];//.toUpperCase();
        }
		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return CONTENT.length;
		}
    }
}
