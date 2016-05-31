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
		 * SimpleAdapter context:������ data�� ����ԴList<��extends Map<String, ?>>data)
		 * һ��Map����ɵ�List���� ÿһ��Map��ӦListView��һ�� ÿһ��Map(��-ֵ)�еļ��������������From����ָ���ļ�
		 * resource���б�Ĳ����ļ�ID from��Map�еļ�ֵ to����������ͼ�е�Id,��From��Ӧ
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
		// ѭ�����20��
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
		// ��ָ�뿪��Ļǰ����������һ��
		if (scrollState == SCROLL_STATE_FLING) {
			 Log.i("main", "��������");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", "������� ");
			map.put("image", R.drawable.ic_launcher);
			list_data.add(map);
		
			// ��̬����
			simple_adapter.notifyDataSetChanged();
		} else
		// ֹͣ����
		if (scrollState == SCROLL_STATE_IDLE) {
 Log.i("main", "��ͼֹͣ����");
		} else
		// ���ڹ���
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
			 Log.i("main", "��ָ�뿪��Ļ");
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
