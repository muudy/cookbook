package com.zx.tools.dhly;

import java.util.List;
import java.util.Map;


import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter{
	private Context context;
	private List<Map<String, Object>> listitems;
	private LayoutInflater listContainerInflater;
	public final class GridItemView
    {
   	 public ImageView imageView;
   	 public TextView Name;
//   	 public ImageView bofang;
    }
	
	public GridViewAdapter(Context context,List<Map<String, Object>> listitems)
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
		GridItemView gridItemView=null;
		if(convertView==null)
		{
			gridItemView=new GridItemView();
			convertView=listContainerInflater.inflate(R.layout.gv,null);
			gridItemView.imageView=(ImageView) convertView.findViewById(R.id.imageView1);
			gridItemView.Name=(TextView) convertView.findViewById(R.id.textView1);
//			gridItemView.bofang=(ImageView)convertView.findViewById(R.id.imageView2);
			//设置控件集到convertView
			convertView.setTag(gridItemView);
		}
		else {   
			gridItemView = (GridItemView)convertView.getTag();   
        }   
		gridItemView.imageView.setImageBitmap((Bitmap) listitems.get(position).get("images"));
		gridItemView.Name.setText((CharSequence) listitems.get(position).get("text"));
//		gridItemView.bofang.setImageBitmap((Bitmap)listitems.get(position).get("bofang"));
		return convertView;
	}

}
