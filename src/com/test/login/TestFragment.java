package com.test.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;



public final class TestFragment extends Fragment {
	
	
	private static final String[] Names = new String[] { "菜1", "菜2",
	    "菜3", "菜4", "菜5", "菜6"};
		private static final String[] Price = new String[] { "价格1", "价格2",
	        "价格3", "价格4", "价格5", "价格6"};
		private static final int[] Images = new int[] { R.drawable.ic_launcher,
	    R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
	    R.drawable.ic_launcher, R.drawable.ic_launcher};
		
		
    private static final String KEY_CONTENT = "TestFragment:Content";
    private int ItemNum = 6;//,location[] = new int[2],posy=0;
    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	View v = inflater.inflate(R.layout.main_grid, container,false);
    	GridView gv = (GridView)v.findViewById(R.id.menu_face);
    	gv.setAdapter(new MyAdapter(inflater));
    	List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; i < Names.length;i++)
		{
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("name", Names[i]);
			listItem.put("price", Price[i]);
			listItem.put("image", Images[i]);
			listItems.add(listItem);
		}
		

 //   	gv.setOnScrollListener(new OnLoadMoreListener());
    	return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
    
    
    public static void LoadDish(int position,List<Map<String,Object>> menu,View view){
    	TextView tv ;
    	if(position < menu.size()){
    		ImageView iv = (ImageView)view.findViewById(R.id.dishesImageId);
    		iv.setImageBitmap(menu.get(position).getPhoto());
    		tv = (TextView)view.findViewById(R.id.dishesNameId);
    		tv.setText(menu.get(""+position).getName());
    		tv = (TextView)view.findViewById(R.id.dishesPriceId);
    		tv.setText(menu.get(""+position).getPrice());
    	}
    		
    	
    }
    
    
    
   private class MyAdapter extends BaseAdapter {
    	
    	LayoutInflater inflater;
    	
    	public MyAdapter(LayoutInflater inflater) {
    		this.inflater = inflater;
    	}
    	
		public int getCount() {
			// TODO Auto-generated method stub
			return ItemNum;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = inflater.inflate(R.layout.dishes_list, null);	
				LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.LL);
				/*ImageView imageview = (ImageView)convertView.findViewById(R.id.dishesImageId);
				TextView dishesNameId = (TextView)convertView.findViewById(R.id.dishesNameId);
				TextView dishesPriceId = (TextView)convertView.findViewById(R.id.dishesPriceId);*/
				
				
//				iv.setAnimation(anim);
			}
			return convertView;
		} 
    	
    }
}
