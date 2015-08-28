package com.laogande.test.models.datasource;

import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laogande.test.models.enties.Book;
import com.laogande.test.utils.MyVolley;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;


import java.util.ArrayList;
import java.util.List;

public class BooksAsyncDataSource implements IAsyncDataSource<List<Book>> {
	private int mPage;
	private int mMaxPage = 10;

	public BooksAsyncDataSource() {
		super();
	}

	@Override
	public RequestHandle refresh(ResponseSender<List<Book>> sender) throws Exception {
		return loadBooks(sender, 1);
	}

	@Override
	public RequestHandle loadMore(ResponseSender<List<Book>> sender) throws Exception {
		return loadBooks(sender, mPage + 1);
	}

	@Override
	public boolean hasMore() {
		return mPage < mMaxPage;
	}

	private RequestHandle loadBooks(final ResponseSender<List<Book>> sender, final int page) throws Exception {

		String url = "http://www.baidu.com";
		Uri.Builder builder = Uri.parse(url).buildUpon();
		builder.appendQueryParameter("api_key", "75ee6c644cad38dc8e53d3598c8e6b6c");
		StringRequest jsonObjRequest = new StringRequest(Request.Method.GET, builder.toString(), new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				List<Book> books = new ArrayList<Book>();
				for (int i = 0; i < 30; i++) {
					books.add(new Book("page" + page + "  Java编程思想 " + i, 108.00d));
				}
				mPage = page;
				sender.sendData(books);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				sender.sendError(error);
			}
		});
		MyVolley.getRequestQueue().add(jsonObjRequest);
		return new VolleyRequestHandle(MyVolley.getRequestQueue(), jsonObjRequest);
	}
}
