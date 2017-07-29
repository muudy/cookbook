package com.zx.tools.dhly;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	private Context context;
	private List<Map<String, Object>> listitems;
	private LayoutInflater listContainerInflater;
	public final class ListTextView
    {
   	 public TextView Name;
   	 public ImageView tx;
   	 
    }
	public ListViewAdapter(Context context,List<Map<String, Object>> listitems)
    {
   	 this.context=context;
   	 //创建视图容器并设置上下文
   	 listContainerInflater=LayoutInflater.from(context);
   	 this.listitems=listitems;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListTextView ltv=null;
		if(convertView==null){
			ltv=new ListTextView();
			convertView=listContainerInflater.inflate(R.layout.lv,null);
			ltv.Name=(TextView) convertView.findViewById(R.id.textV);
			ltv.tx=(ImageView)convertView.findViewById(R.id.imageV);
			convertView.setTag(ltv);
		}else{
			ltv = (ListTextView)convertView.getTag();
		}
		ltv.Name.setText((CharSequence) listitems.get(position).get("text"));
		ltv.tx.setImageBitmap((Bitmap) listitems.get(position).get("images"));
		return convertView;
	}
}
