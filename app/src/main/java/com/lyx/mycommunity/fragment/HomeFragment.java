package com.lyx.mycommunity.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lyx.mycommunity.adapter.HomeRecycleAdapter;
import com.lyx.mycommunity.bean.NoteInfo;
import com.lyx.mycommunity.dao.Note;
import com.lyx.mycommunity.dao.NoteDataBaseHelper;
import com.lyx.shoppingcity.R;
import com.lyx.mycommunity.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private  TextView tv_message_home,tv_user;
    private List<NoteInfo> list;
    private HomeRecycleAdapter adapterDome;
    private static NoteDataBaseHelper dbHelper;
    @Override
    public View initView() {
        Log.e(TAG," 主页视图被初始化了");
        View view = View.inflate(myContext, R.layout.fragment_home,null);
        rvHome = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        tv_user = view.findViewById(R.id.tv_user);
        dbHelper = new NoteDataBaseHelper(getContext(),"MyNote.db",null,1);

        initRecycleData();
        initRecycle();

        //跳转回主界面 刷新列表
        Intent intent1 =getActivity().getIntent();
        if (intent1 != null){
            getNoteList();
            adapterDome.refreshDataSet();//渲染列表
        }

        // 设置监听
        initListener();
        return view;

    }

    private void initRecycle() {

        // 创建adapter
        adapterDome = new HomeRecycleAdapter(getContext(),list);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        StaggeredGridLayoutManager stagger = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvHome.setLayoutManager(stagger);
        //给RecyclerView设置adapter
        rvHome.setAdapter(adapterDome);
        adapterDome = new HomeRecycleAdapter(getContext(),list);
        rvHome.setAdapter(adapterDome);
    }
    private void initRecycleData() {
        list = new ArrayList<>();

        dbHelper = new NoteDataBaseHelper(getContext(),"MyNote.db",null,1);
/*        if (list.isEmpty()){
            for (int i = 0; i < 20; i++) {
                strlist.add1("广州烧烤"+i+"，美味鲜香，快来试试！！享受生活享受美食");
            }
        }*/
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页面的的Fragment的数据被初始化了");
        //获取noteList
        getNoteList();
    }

    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //返回页面顶部
           rvHome.scrollToPosition(0);
            }
        });

        // 搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,"搜索",Toast.LENGTH_SHORT).show();
            }
        });

        // 消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,"进入消息中心",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //从数据库中读取所有帖子 封装成List<NoteInfo>
    private void getNoteList(){
        list.clear();
        Cursor allNotes = Note.getAllNotes(dbHelper);
        noteInfoSet(list,allNotes);
    }
    private void noteInfoSet(List<NoteInfo> noteinfo,Cursor Notes){
        for (Notes.moveToFirst(); !Notes.isAfterLast(); Notes.moveToNext()){
            NoteInfo noteInfo = new NoteInfo();
            noteInfo.setId(Notes.getString(Notes.getColumnIndex(Note._id)));
            noteInfo.setTitle(Notes.getString(Notes.getColumnIndex(Note.title)));
            noteInfo.setContent(Notes.getString(Notes.getColumnIndex(Note.content)));
            noteInfo.setDate(Notes.getString(Notes.getColumnIndex(Note.time)));
            noteInfo.setDes(Notes.getString(Notes.getColumnIndex(Note.content)));
            noteInfo.setPre(Notes.getString(Notes.getColumnIndex(Note.pre)));
            noteInfo.setPhoto(Notes.getBlob(Notes.getColumnIndex(Note.picture)));
            noteinfo.add(noteInfo);
        }
    }

}
