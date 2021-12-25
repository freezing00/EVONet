package com.example.evonet.javaBeans;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evonet.R;
import com.example.evonet.fragment.fragment_signin;

import java.util.List;
import java.util.Random;

public class MyAdapter extends BaseAdapter {
    private List<LessonBean>data;
    private Context context;
    public MyAdapter(List<LessonBean> data, Context context) {
        this.data = data;
        this.context=context;
    }




    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder.textView_lesson_name=convertView.findViewById(R.id.lesson_name);
            viewHolder.textView_lesson_number=convertView.findViewById(R.id.lesson_number);
            viewHolder.textView_lesson_sum_person=convertView.findViewById(R.id.lesson_sum_person);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();//主要是绑定布局文件中的id，不用每点击一次就去寻找，这里相当于一个容器
        }
        viewHolder.textView_lesson_sum_person.setText(data.get(position).getSum_person());//节省findViewById的时间
        viewHolder.textView_lesson_number.setText(data.get(position).getNumber());//节省findViewById的时间
        viewHolder.textView_lesson_name.setText(data.get(position).getName());//节省findViewById的时间

//        Log.e("leq","getview"+position);//打印

        return convertView;
    }
    //ListView中Item的控件组合
    private final class ViewHolder{
        TextView textView_lesson_name;
        TextView textView_lesson_number;
        TextView textView_lesson_sum_person;
    }


}
