package com.example.evonet.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.evonet.R;
import com.example.evonet.activities.SignActivity;
import com.example.evonet.javaBeans.Lesson;
import com.example.evonet.javaBeans.Lesson_Number_Holder;
import com.example.evonet.javaBeans.MyAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class fragment_signin extends Fragment {
    Bundle bundle = new Bundle();
//    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private List<Lesson> data=new ArrayList<>();
    private View view;
    private MyAdapter adapter;
//    private View present_Item_View;//当前选中的Item对象的View
    private TextView textview_lesson_number;//获取item中的课程号
    private String lesson_numebr;
    final Random color=new Random();
    final Paint p=new Paint();

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
        view = inflater.inflate(R.layout.sigin_fragment, null);
        //装配数据源
        Lesson bean_as=new Lesson();
        bean_as.setName("安卓应用开发");
        bean_as.setNumber("JHMXUS");
        bean_as.setSum_person("111人");
        data.add(bean_as);

        Lesson bean_xs=new Lesson();
        bean_xs.setName("形势与政策");
        bean_xs.setNumber("Q28GUH");
        bean_xs.setSum_person("245人");
        data.add(bean_xs);

        Lesson bean_os=new Lesson();
        bean_os.setName("操作系统");
        bean_os.setNumber("VKWRKC");
        bean_os.setSum_person("151人");
        data.add(bean_os);

        ListView listView=view.findViewById(R.id.mylesson);
//        listView.setAdapter(new MyAdapter(data,getActivity()));

        adapter=new MyAdapter(data,getActivity());

        listView.setAdapter(adapter);

        //获取到当前点击的adapter数据
        //创建ListView上每一个Item的点击事件，均跳转到签到页

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //AdapterView<?> parent当前的AdapterView对象，即ListView
                //view是AdapterView所绑定的Adapter的getView方法返回的View，即当前item的view，通过它可以获得该项中的各个组件
                //position是当前被点击的条目的索引号
                //id  AdapterView所绑定的Adapter的getItemId返回的值，是当前item的ID。这个id根据你在适配器中的写法可以自己定义
                MyAdapter adapter= (MyAdapter) parent.getAdapter();//获取当前选择值
                Object listItem=listView.getItemAtPosition(position);
                view=findView(position,listView);
                textview_lesson_number=view.findViewById(R.id.lesson_number);
                lesson_numebr=textview_lesson_number.getText().toString();//得到需要的课程号
                //随机设置背景色
                p.setARGB(155555,color.nextInt(256),color.nextInt(256),color.nextInt(256));
                textview_lesson_number.setBackgroundColor(p.getColor());

                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
                Lesson_Number_Holder.getInstance().setData(lesson_numebr);
                startActivity(intentlesson);
            }
        });

        return view;

    }

    //  得到每一个不同的Item所对应的 View
    private View findView(int position, ListView listView) {
        int firstListItemPosition = listView.getFirstVisiblePosition();
        int lastListItemPosition = firstListItemPosition
                + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }




}
