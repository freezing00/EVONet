package com.example.evonet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.evonet.R;
import com.example.evonet.activities.IFragmentInterface;
import com.example.evonet.activities.MainActivity;
import com.example.evonet.activities.SignActivity;
import com.example.evonet.javaBeans.LessonBean;
import com.example.evonet.javaBeans.MyAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class fragment_signin extends Fragment {
    Bundle bundle = new Bundle();
//    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private List<LessonBean> data=new ArrayList<>();
    private View view;

    public fragment_signin() {
    }

    //构造函数
    public static fragment_signin newInstance() {
        return new fragment_signin();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.siginfragment, null);
//        for(int i=1;i<3;i++){
//            LessonBean bean=new LessonBean();
//            bean.setName("离成功还有"+i+"步");
//            data.add(bean);
//        }
        //在这里实例化LessonBean，对应每一个课程
        LessonBean bean_as=new LessonBean();
        bean_as.setName("安卓应用开发");
        bean_as.setNumber("JHMXUS");
        bean_as.setSum_person("111人");
        data.add(bean_as);

        LessonBean bean_xs=new LessonBean();
        bean_xs.setName("形势与政策");
        bean_xs.setNumber("Q28GUH");
        bean_xs.setSum_person("245人");
        data.add(bean_xs);

        LessonBean bean_os=new LessonBean();
        bean_os.setName("操作系统");
        bean_os.setNumber("VKWRKC");
        bean_os.setSum_person("151人");
        data.add(bean_os);

        ListView listView=view.findViewById(R.id.mylesson);
        listView.setAdapter(new MyAdapter(data,getActivity()));
        //创建ListView上每一个Item的点击事件，均跳转到签到页
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
                startActivity(intentlesson);
            }
        });


//        layout1=view.findViewById(R.id.layout1);
//        layout2=view.findViewById(R.id.layout2);
//        layout3=view.findViewById(R.id.layout3);
        //点击课程列表中一项跳转到SignActivity页面定位签到
//        layout1.setOnClickListener(this);
//        layout2.setOnClickListener(this);
//        layout3.setOnClickListener(this);

//        layout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
//                startActivity(intentlesson);
//            }
//        });
//        layout2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
//                startActivity(intentlesson);
//            }
//        });
//        layout3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
//                startActivity(intentlesson);
//            }
//        });

        return view;

    }
}