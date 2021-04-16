package com.lyx.mycommunity.app;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lyx.shoppingcity.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private Toolbar toolbar,ltoolBar;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //获取传递的路径
        webView = findViewById(R.id.webView);
        toolbar = findViewById(R.id.toolbar_webview);
        ltoolBar = findViewById(R.id.toolbar_webcomment);
        findViewById(R.id.toolbar_webcomment).bringToFront();

    }

    @Override
    protected void onStart() {
        super.onStart();
        url = getIntent().getStringExtra("url");
        //显示JavaScript页面
        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view,url );
                //通过查看每个新闻的网页发现网页广告的div样式的选择器为body > div.top-wrap.gg-item.J-gg-item 然后去除这个样式，使其加载网页去掉广告
                view.loadUrl("javascript:function setTop(){document.querySelector('body > div.top-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop();");

            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){

             //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();

             //handleMessage(Message msg); 其他处理
            }

        });
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        /*settings.setDisplayZoomControls(false);*/
        webView.loadUrl(url);

        setSupportActionBar(ltoolBar);
        toolbar.setTitle("新闻");
        setSupportActionBar(toolbar);
        ltoolBar.inflateMenu(R.menu.tool_webbottom);
        ltoolBar.setTitle("感谢观看");
        ltoolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.news_share:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT,url);
                        intent.setType("text/plain");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent,getTitle()));
                        break;
                    case R.id.news_collect:

                        break;
                }
                return true;
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.top_bar_left_back);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_webview,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.news_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(WebViewActivity.this,query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent returnIntent = new Intent();
                WebViewActivity.this.finish();
                break;
            case R.id.news_setting:
              //  Toast.makeText(this,"夜间模式",Toast.LENGTH_SHORT).show();
                break;
            case R.id.news_feedback:
                break;
            default:
                break;
        }
        return true;
    }
}
