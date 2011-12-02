package duyoji.test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView mainListView;
	ArrayList<String> list = new ArrayList<String>();
	SubBaseAdapter adapter;
	
	private boolean scroll_flag = false;
	
	final private int F = ViewGroup.LayoutParams.FILL_PARENT;
    final private int W = ViewGroup.LayoutParams.WRAP_CONTENT;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainListView = (ListView) findViewById(R.id.main_listview);
        setArrayList();        
        setMainListViewEvent();        
        setFooterListView();
        setAdapter();
    }
    
    
    private LinearLayout.LayoutParams createParam(int width,int height){
    	
    	
        return new LinearLayout.LayoutParams(width, height);
    }
    
    
    private void setFooterListView(){
    	ProgressBar pb = new ProgressBar(this);    	
    	mainListView.addFooterView(pb);
    }
    
    
    private void setArrayList(){    	
    	for (int i = 1; i <= 40; i++) {
    		list.add("text"+i);
		}    	
    }
    
    
    private void setAdapter(){
    	adapter = new SubBaseAdapter(this, list);
    	mainListView.setAdapter(adapter);
    }
    
    
    private void setMainListViewEvent(){
    	mainListView.setOnScrollListener(new OnScrollListener() {			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				Log.d("TAG", "scrollState : " + scrollState);
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if(scroll_flag){					
					if(totalItemCount == firstVisibleItem + visibleItemCount){
						Log.d("TAG", "一番下");
						addListView();
					}
				}else{
					scroll_flag = true;
				}
				
			
			}
		});
    }
    
    private void addListView(){    
    	ArrayList<String> _list = new ArrayList<String>();
    	for (int i = 0; i < 10; i++) {
			_list.add("追加分"+i);
		}
    	adapter.addString(_list);
    	adapter.notifyDataSetChanged();
    	mainListView.invalidateViews();    	
    }
    
    private class SubBaseAdapter extends BaseAdapter{

    	public ArrayList<String> items;
    	public Context context;
    	
    	public SubBaseAdapter(Context c , ArrayList<String> _list) {
			// TODO Auto-generated constructor stub
    		context = c;
    		items = _list;
		}
    	
    	public void addString(ArrayList<String> _list){
    		for (int i = 0; i < _list.size(); i++) {
				items.add(_list.get(i));
			}
    	}
    	
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.items.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			TextView text = new TextView(context);
			text.setText(items.get(position));
			return text;
		}
    	
    }
    
    
}