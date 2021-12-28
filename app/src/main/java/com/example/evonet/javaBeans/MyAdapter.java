package com.example.evonet.javaBeans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evonet.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private List<Lesson>data;
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String,Object>> mNumber;//存储textView_lesson_number的值
    //通过构造方法关联数据源与适配器

    public MyAdapter(List<Lesson> data, Context context) {
        this.data = data;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
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
            viewHolder.textView_lesson_number.setTag(position);

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();//主要是绑定布局文件中的id，不用每点击一次就去寻找，这里相当于一个容器
//            viewHolder.textView_lesson_number.setTag(position);
        }
        viewHolder.textView_lesson_sum_person.setText(data.get(position).getSum_person());//节省findViewById的时间
        viewHolder.textView_lesson_number.setText(data.get(position).getNumber());//节省findViewById的时间
        viewHolder.textView_lesson_name.setText(data.get(position).getName());//节省findViewById的时间


        return convertView;
    }

    //	定义接口方法
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    //	声明接口
    private onItemDeleteListener xmOnItemDeleteListener;


    //	根据我们在回调里写的是 xmOnItemDeleteListener 调用的onDeleteClick
    //	系统就会执行下面的方法
    public void setXmOnItemDeleteListener(onItemDeleteListener xmOnItemDeleteListener){
        this.xmOnItemDeleteListener = xmOnItemDeleteListener;
    }


    //ListView中Item的控件组合
    private final class ViewHolder{
        TextView textView_lesson_name;
        TextView textView_lesson_number;
        TextView textView_lesson_sum_person;
    }


}
