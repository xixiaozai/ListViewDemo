package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,
		OnScrollListener {
	private ListView mlistView;
	// private ListAdapter mListAdapter;
	private SimpleAdapter simple_adapter;
	private List<Map<String, Object>> list_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mlistView = (ListView) findViewById(R.id.listView);
		/**
		 * ArrayAdapter
		 */
		// String[] data = new String[] { "1", "2", "3", "4" };
		// ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, data);
		// mlistView.setAdapter(array_adapter);
		/**
		 * SimpleAdapter context:上下文 data： 数据源List<？extends Map<String, ?>>data)
		 * 一个Map所组成的List集合 每一个Map对应ListView的一行 每一个Map(键-值)中的键必须包含所有在From中所指定的键
		 * resource：列表的布局文件ID from：Map中的键值 to：绑定数据视图中的Id,与From对应
		 */
		list_data = new ArrayList<Map<String, Object>>();
		simple_adapter = new SimpleAdapter(this, getData(), R.layout.item,
				new String[] { "image", "text" }, new int[] { R.id.image,
						R.id.text });
		mlistView.setAdapter(simple_adapter);
		mlistView.setOnItemClickListener(this);
		mlistView.setOnScrollListener(this);
	}

	private List<Map<String, Object>> getData() {
		// 循环添加20个
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("text", "" + i);
			list_data.add(map);
		}
		return list_data;
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		// 手指离开屏幕前，用力滑了一下
		if (scrollState == SCROLL_STATE_FLING) {
			 Log.i("main", "用力划动");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", "滚动添加 ");
			map.put("image", R.drawable.ic_launcher);
			list_data.add(map);
		
			// 动态更新
			simple_adapter.notifyDataSetChanged();
		} else
		// 停止滚动
		if (scrollState == SCROLL_STATE_IDLE) {
 Log.i("main", "视图停止滚动");
		} else
		// 正在滚动
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
			 Log.i("main", "手指离开屏幕");
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String text = mlistView.getItemAtPosition(position) + "";
		Toast.makeText(MainActivity.this,
				"position=" + position + " content=" + text, Toast.LENGTH_SHORT)
				.show();
	}
}
