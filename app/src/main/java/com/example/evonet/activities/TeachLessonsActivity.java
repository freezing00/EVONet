package com.example.evonet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.evonet.R;
import com.example.evonet.javaBeans.Lesson;
import com.example.evonet.javaBeans.Lesson_Data_Holder;

import com.example.evonet.javaBeans.LessonsAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TeachLessonsActivity extends AppCompatActivity {
    //    Bundle bundle = new Bundle();
    //    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private View view;
    private LessonsAdapter adapter;

    //    private View present_Item_View;//当前选中的Item对象的View
    private TextView textview_lesson_number;//获取item中的课程号
    private TextView textView_lesson_name;
    private TextView textView_lesson_sum_person;
    //向发布签到页面传送Item对应View的课程名，课程号，课程人数3个数据
    private String lesson_numbers;
    private String lesson_name;
    private String lesson_sum_person;
    private final List<Lesson> data = new ArrayList<>();

//    public teach_lessons() {
//    }
//
//    //构造函数
//    public static teach_lessons newInstance() {
//        return new teach_lessons();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lesson lesson = new Lesson();
        setContentView(R.layout.activity_teach_lessons);
        lesson.setName("安卓应用开发"); ;//得到需要的课程号
        lesson.setNumber("JHMXUS");
        lesson.setSum_person("111人");
        data.add(lesson);
        lesson.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
            }
        });


        lesson =new Lesson();
        lesson.setName("形势与政策"); ;//得到需要的课程号
        lesson.setNumber("Q28GUH");
        lesson.setSum_person("245人");
        data.add(lesson);
        lesson.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
            }
        });


        lesson =new Lesson();
        lesson.setNumber("VKWRKC") ;//得到需要的课程号
        lesson.setName("操作系统");
        lesson.setSum_person("151人");
        data.add(lesson);
        lesson.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
            }
        });


        ListView listView=findViewById(R.id.teachlesson);
        adapter=new LessonsAdapter(data, TeachLessonsActivity.this);
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
                LessonsAdapter adapter= (LessonsAdapter) parent.getAdapter();//获取当前选择值
                Object listItem=listView.getItemAtPosition(position);
                view=findView(position,listView);
                textview_lesson_number=view.findViewById(R.id.lesson_number_teah);
                textView_lesson_name=view.findViewById(R.id.lesson_name_teah);
                textView_lesson_sum_person=view.findViewById(R.id.lesson_sum_person_teah);
                lesson_numbers =textview_lesson_number.getText().toString();//得到需要的课程号
                lesson_name=textView_lesson_name.getText().toString();
                lesson_sum_person=textView_lesson_sum_person.getText().toString();

                Intent intent = new Intent(TeachLessonsActivity.this, StartSignActivity.class);//跳转到发布签到页面
                Lesson_Data_Holder.getInstance().setName(lesson_name);
                Lesson_Data_Holder.getInstance().setNumber(lesson_numbers);
                Lesson_Data_Holder.getInstance().setSum(lesson_sum_person);
                startActivity(intent);
            }
        });

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
