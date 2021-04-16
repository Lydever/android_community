package com.lyx.mycommunity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.lyx.mycommunity.adapter.NewAdapter;
import com.lyx.mycommunity.app.WebViewActivity;
import com.lyx.mycommunity.base.BaseFragment;
import com.lyx.mycommunity.bean.NewsBean;
import com.lyx.shoppingcity.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/13 13:11
 * 包名：
 * 描述：
 */
public class NewsFragment extends BaseFragment{
    private static final String TAG = NewsFragment.class.getSimpleName();
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<NewsBean.ResultBean.DataBean> list;
    private static final int UPNEWS_INSERT = 0;


    private String data;


    @Override
    public View initView() {
        Log.e(TAG, "新闻中心的Frament的UI被初始化");
        View view = View.inflate(myContext, R.layout.fragment_news,null);

        findViews(view);
        initListener();
        return view;

    }

    private void findViews(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
    }

    @SuppressLint("HandlerLeak")
    private Handler newsHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String uniquekey,title,date, category,author_name,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03;
            switch (msg.what){
                case UPNEWS_INSERT:
                    list = ((NewsBean) msg.obj).getResult().getData();
                    NewAdapter adapter = new NewAdapter(getActivity(),list);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private void initListener() {
        //下拉刷新
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        // 下一步实现从数据库中读取数据刷新到listview适配器中
                    }
                },1000);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取点击条目的路径，传值显示webview页面
                String url = list.get(position).getUrl();
                String uniquekey = list.get(position).getUniquekey();
                final NewsBean.ResultBean.DataBean dataBean = (NewsBean.ResultBean.DataBean) list.get(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "新闻中心的Fragment的数据被初始化了");

        //异步加载数据
        getDataFromNet(data);

    }

    private void getDataFromNet(final String data){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String path = "http://v.juhe.cn/toutiao/index?type="+data+"&key=7b86218d6dc3adfef38731c17319a227";
                URL url = null;
                try {
                    url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();
                        String json = streamToString(inputStream,"utf-8");
                        return json;
                    } else {
                        System.out.println(responseCode);
                        return "已达到今日访问次数上限";
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }
            protected void onPostExecute(final String result){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NewsBean newsBean = new Gson().fromJson(result,NewsBean.class);
                     //   System.out.println(newsBean.getError_code());
/*                        if ("10012".equals(""+newsBean.getError_code())){
                            //下一篇将要实现从数据库加载数据
                        }*/
                        Message msg=newsHandler.obtainMessage();
                        msg.what=UPNEWS_INSERT;
                        msg.obj = newsBean;
                        newsHandler.sendMessage(msg);
                    }
                }).start();
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        };
        task.execute();
    }
    private String streamToString(InputStream inputStream, String charset){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }
            bufferedReader.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}