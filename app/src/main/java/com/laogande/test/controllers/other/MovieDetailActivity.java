/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.laogande.test.controllers.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.laogande.test.R;
import com.laogande.test.models.datasource.MovieDetailDataSource;
import com.laogande.test.models.enties.Discuss;
import com.laogande.test.models.enties.Movie;
import com.laogande.test.view.adapters.MovieDetailAdapter;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCUltraHelper;
import com.shizhefei.mvc.data.Data3;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;


/***
 * 测试下拉组件的超复杂的列表界面，MVCUltraHelper，RecyclerView
 * 
 * @author LuckyJayce
 *
 */
public class MovieDetailActivity extends Activity {

	private MVCHelper<Data3<Movie, List<Discuss>, List<Movie>>> listViewHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ultrarecyclerview);

		PtrClassicFrameLayout contentLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
		listViewHelper = new MVCUltraHelper<Data3<Movie, List<Discuss>, List<Movie>>>(contentLayout);

		// 设置数据源
		listViewHelper.setDataSource(new MovieDetailDataSource());
		// 设置适配器
		listViewHelper.setAdapter(new MovieDetailAdapter(getApplicationContext()));

		// 加载数据
		listViewHelper.refresh();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 释放资源
		listViewHelper.destory();
	}

	public void onClickBack(View view) {
		finish();
	}

}
