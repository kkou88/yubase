package com.yu.yubase;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			initView(rootView);
			initControl();
			initData();
			return rootView;
		}

		private void initView(View v) {
			TextView btn1 = (TextView) v.findViewById(R.id.btn1);
			TextView btn2 = (TextView) v.findViewById(R.id.btn2);
			TextView btn3 = (TextView) v.findViewById(R.id.btn3);
			TextView btn4 = (TextView) v.findViewById(R.id.btn4);
			TextView btn5 = (TextView) v.findViewById(R.id.btn5);

			// 第一个按钮
			btn1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					BaseApplication.getInstance().showtoast("这是测试1", 0);
				}
			});
			// 第二个按钮
			btn2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					BaseApplication.getInstance().showtoast("这是测试2", 1);
				}
			});
			// 第三个按钮
			btn3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					BaseApplication.getInstance().showProgressDialog(getActivity(), "正在加载..", true);
				}
			});
		}

		private void initControl() {

		}

		private void initData() {

		}

	}

}
