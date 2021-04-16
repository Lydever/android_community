package com.lyx.mycommunity.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lyx.mycommunity.base.BaseFragment;
import com.lyx.mycommunity.bean.JsonBean;
import com.lyx.shoppingcity.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/13 13:10
 * 描述：
 */
public class ServiceFragment extends BaseFragment {
    private static final String TAG = ServiceFragment.class.getSimpleName();
    private TextView tv_msg;
    private EditText edt_phone;
    private Button btn_query;
    private OkHttpClient client;

    @Override
    public View initView() {
        Log.e(TAG," 服务的视图被实例化了");
        View view = View.inflate(myContext, R.layout.fragment_service,null);
        findview(view);
        initListener();
        return view;
    }

    private void findview(View view) {
        edt_phone=(EditText)view.findViewById(R.id.edt_phone);
        tv_msg=(TextView)view.findViewById(R.id.tv_msg);
        btn_query=(Button)view.findViewById(R.id.btn_query);

    }

    private void initListener() {
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try{
                            new GetGuiShuDi().run();}
                        catch (Exception e){
                            Log.i("出错了","Exception"+e.getCause()+e.getMessage());
                        }
                    }
                }.start();
            }
        });
    }



    @Override
    public void initData() {
        super.initData();
        Log.e(TAG," 分类数据被初始化了");
       // textView.setText("服务");
        client=new OkHttpClient();
    }
    /**请求的格式
     * {
     *         "resultcode":"200",
     *         "reason":"Return Successd!",
     *         "result":{
     *         "province":"广东",
     *         "city":"广州",
     *         "areacode":"020",
     *         "zip":"510000",
     *         "company":"联通",
     *         "card":""
     *         },
     *         "error_code":0
     *         }
     *手机号码段	   18027118545
     * 卡号归属地   广东 广州
     * 运营商	   联通
     * 区号	       020
     * 邮编   	   510000
     */
    public class GetGuiShuDi{
        String string = edt_phone.getText().toString();
        private void run() {
            Request request = new Request.Builder()
                    .url("http://apis.juhe.cn/mobile/get?phone="+string+"&key="+"84584f55b5178ba3b97ce2e56c0e5eff")
                    .get()
                    .build();
            try{
                Response response = client.newCall(request).execute();
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<JsonBean>() {}.getType();
                final JsonBean jsonBean = gson.fromJson(response.body().string(), type);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_msg.setText("查询号码："+string+"\n"+"号码归属地:"+jsonBean.result.province+" "+jsonBean.result.city+"\n"+"运营商:"+jsonBean.result.company+"\n"+"归属地：" + jsonBean.result.city+"\n"
                                +"区号："+jsonBean.result.areacode+"\n"+"邮编："+jsonBean.result.zip);
                    }
                });
                Log.i("json+++++++++++++", jsonBean.result.city);
            }catch (Exception e){
                Log.i("json++++++++++++", e.getMessage()+"/"+e.getCause());
            }

        }

    }
}


