package com.example.flower_menu;


import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.selfdrive.MenuPopupWindow.java
系统编号：
系统名称：SelfDrive
模块编号：
模块名称：
设计文档：
创建日期：2013-11-18 上午2:33:00
作 者：陆键霏
内容摘要：弹出菜单
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class MenuPopupWindow extends PopupWindow {
	MyApplication mApp = MyApplication.getInstance();
	private View mMenuView;
    private LinearLayout layout_menu_item1;
    private LinearLayout layout_menu_item2;
    private LinearLayout layout_menu_item3;
    private LinearLayout layout_menu_item4;
    private LinearLayout layout_menu_item5;
    private RelativeLayout  menu_layout;
    private Button bt_mid_cross;
    private int animationInterval = 300;
    float radius = 0;
    float menu_item2_delta_x = 0;//距离中间按钮
    float menu_item1_delta_x = 0;//距离中间按钮
    float menu_item2_delta_x_factor = 0.5f;
    float menu_item1_delta_x_factor = 0.866f;
    //弹出的一个按钮
    private Rect point1;
    private Rect point2;
    private Rect point3;
    private Rect point4;
    private Rect point5;
    //宽和高
    private int menu_item_width = 100;
    private int menu_item_height = 100;
    
	 public MenuPopupWindow(Activity context,final MenuItemOnClickListener itemsOnClick) {
         super(context);
         LayoutInflater inflater = (LayoutInflater) context
                         .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         //menu_popupwindow是旋转动画
         mMenuView = inflater.inflate(R.layout.menu_popupwindow2, null);
         menu_layout = (RelativeLayout) mMenuView.findViewById(R.id.menu_layout);
         layout_menu_item1 = (LinearLayout) mMenuView.findViewById(R.id.layout_menu_item1);
         layout_menu_item2 = (LinearLayout) mMenuView.findViewById(R.id.layout_menu_item2);
         layout_menu_item3 = (LinearLayout) mMenuView.findViewById(R.id.layout_menu_item3);
         layout_menu_item4 = (LinearLayout) mMenuView.findViewById(R.id.layout_menu_item4);
         layout_menu_item5 = (LinearLayout) mMenuView.findViewById(R.id.layout_menu_item5);
         bt_mid_cross = (Button)mMenuView.findViewById(R.id.bt_mid_cross);
         //设置SelectPicPopupWindow的View
         this.setContentView(mMenuView);
         //设置SelectPicPopupWindow弹出窗体的宽
         this.setWidth(LayoutParams.FILL_PARENT);
         //设置SelectPicPopupWindow弹出窗体的高
         this.setHeight(LayoutParams.WRAP_CONTENT);
         //设置SelectPicPopupWindow弹出窗体可点击
         this.setFocusable(true);
         //设置SelectPicPopupWindow弹出窗体动画效果
         //this.setAnimationStyle(R.style.AnimBottom);
         //实例化一个ColorDrawable颜色为半透明
         ColorDrawable dw = new ColorDrawable(0xb0000000);
         //设置SelectPicPopupWindow弹出窗体的背景
         this.setBackgroundDrawable(dw);
         //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
         mMenuView.setOnTouchListener(new OnTouchListener() {
                 
                 public boolean onTouch(View v, MotionEvent event) {
                         int height = mMenuView.findViewById(R.id.menu_layout).getTop();
                         int height_bt_mid_cross = bt_mid_cross.getTop();
                         int y=(int) event.getY();
                        // log(String.format("height=%s y=%s", height,y));
                         if(event.getAction()==MotionEvent.ACTION_UP){
                                 if((y<height || y>(height-height_bt_mid_cross)) &&  !animationPlaying){
                                         dismiss();
                                 }
                         }                      
                         float x = event.getX();
						log(String.format("(%s 	 ,	%s)", x,y));
						
						//第一个菜单按钮点击
						if(x > point1.left && x < point1.left+point1.right &&
						   y > point1.top && y < point1.top + point1.bottom){
							log("menu_item1");
							if(itemsOnClick!=null){
								itemsOnClick.onMenuItemClick(MENU_ITEM_TYPE.MENU_ITEM_1);
							}
						}
						//第二个菜单按钮点击
						else
						if(x > point2.left && x < point2.left+point2.right &&
						   y > point2.top && y < point2.top + point2.bottom){
							log("menu_item2");
							if(itemsOnClick!=null){
								itemsOnClick.onMenuItemClick(MENU_ITEM_TYPE.MENU_ITEM_2);
							}
						}
						//第三个菜单按钮点击
						else
						if(x > point3.left && x < point3.left+point3.right &&
							   y > point3.top && y < point3.top + point3.bottom){
								log("menu_item3");
								if(itemsOnClick!=null){
									itemsOnClick.onMenuItemClick(MENU_ITEM_TYPE.MENU_ITEM_3);
								}
						}
						//第四个菜单按钮点击
						else
						if(x > point4.left && x < point4.left+point4.right &&
						   y > point4.top && y < point4.top + point4.bottom){
							log("menu_item4");
							if(itemsOnClick!=null){
								itemsOnClick.onMenuItemClick(MENU_ITEM_TYPE.MENU_ITEM_4);
							}
						}
						//第五个菜单按钮点击
						else
						if(x > point5.left && x < point5.left+point5.right &&
						   y > point5.top && y < point5.top + point5.bottom){
							log("menu_item5");
							if(itemsOnClick!=null){
								itemsOnClick.onMenuItemClick(MENU_ITEM_TYPE.MENU_ITEM_5);
							}
						}
                         return true;
                 }
         });
         
         bt_mid_cross.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if(!animationPlaying){
				   dismiss();
				 }
			}
		});
         int width = mApp.getScreenSize().width;
         radius = width/2-width/8;
         menu_item2_delta_x = radius * menu_item2_delta_x_factor;
         menu_item1_delta_x = radius * menu_item1_delta_x_factor;
         
         log(String.format("radius=%s menu_item2_delta_x=%s menu_item1_delta_x=%s", radius,menu_item2_delta_x,menu_item1_delta_x));
         point1 = new Rect();
         point2 = new Rect();
         point3 = new Rect();
         point4 = new Rect();
         point5 = new Rect();
 }
	 @Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		// TODO Auto-generated method stub
		super.showAtLocation(parent, gravity, x, y);
		//旋转动画
		//MyAnimation.startAnimationsIn(menu_layout, animationInterval);
		//展开动画
		MyAnimation.startAnimationsIn(layout_menu_item1, animationInterval,-menu_item1_delta_x,radius,mAnimationEndListener1);
		MyAnimation.startAnimationsIn(layout_menu_item2, animationInterval,-menu_item2_delta_x,radius,mAnimationEndListener2);
		MyAnimation.startAnimationsIn(layout_menu_item3, animationInterval,0,radius,mAnimationEndListener3);
		MyAnimation.startAnimationsIn(layout_menu_item4, animationInterval,menu_item2_delta_x,radius,mAnimationEndListener4);
		MyAnimation.startAnimationsIn(layout_menu_item5, animationInterval,menu_item1_delta_x,radius,mAnimationEndListener5);
		//MyAnimation.startAnimationsIn(bt_menu_item1, animationInterval,width/2,radius);
		MyAnimation.startAnimationsIn(bt_mid_cross, animationInterval);
	}
	 
	public void dismissDirectly(){
		super.dismiss();
	}
	 @Override
	public void dismiss() {
		// TODO Auto-generated method stub
		 if(!animationPlaying){
			//展开动画
			MyAnimation.startAnimationsOut(layout_menu_item1, animationInterval,-menu_item1_delta_x,radius,null);
		    MyAnimation.startAnimationsOut(layout_menu_item2, animationInterval,-menu_item2_delta_x,radius,null);
			MyAnimation.startAnimationsOut(layout_menu_item3, animationInterval,0,radius,null);
			MyAnimation.startAnimationsOut(layout_menu_item4, animationInterval,menu_item2_delta_x,radius,null);
			MyAnimation.startAnimationsOut(layout_menu_item5, animationInterval,menu_item1_delta_x,radius,mAnimationEnd);
			 //MyAnimation.startAnimationsOut(menu_layout, animationInterval, 0,mAnimation);//外圈
			 MyAnimation.startAnimationsOut(bt_mid_cross, animationInterval, 0,null);//中心按钮
		 }
	}
	 boolean animationPlaying = false;
	 Animation.AnimationListener mAnimationEnd = new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				animationPlaying = true;
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				animationPlaying = true;
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				
				animationPlaying = false;
				handler.postDelayed(dismissTask, 10);
			
			}
		};
	    Handler handler =new Handler();
	    Runnable dismissTask = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				dismissDirectly();//动画结束后才消失
			}
		};
		AnimationEndListener mAnimationEndListener1 = new AnimationEndListener(){
			@Override
			public void onLocationEndReceive(float x, float y) {
				// TODO Auto-generated method stub
				point1.left = (int)x;
				point1.top = (int)y;
				point1.right =  menu_item_width;
				point1.bottom =  menu_item_height;
				log(String.format("mAnimationEndListener1 (%s,%s)",x,y));
			}
		};
		AnimationEndListener mAnimationEndListener2 = new AnimationEndListener(){
			@Override
			public void onLocationEndReceive(float x, float y) {
				// TODO Auto-generated method stub
				point2.left = (int)x;
				point2.top = (int)y;
				point2.right =  menu_item_width;
				point2.bottom =  menu_item_height;
				log(String.format("mAnimationEndListener2 (%s,%s)",x,y));
			}
		};
		AnimationEndListener mAnimationEndListener3 = new AnimationEndListener(){
			@Override
			public void onLocationEndReceive(float x, float y) {
				// TODO Auto-generated method stub
				point3.left = (int)x;
				point3.top = (int)y;
				point3.right =  menu_item_width;
				point3.bottom =  menu_item_height;
				log(String.format("mAnimationEndListener3 (%s,%s)",x,y));
			}
		};
		AnimationEndListener mAnimationEndListener4 = new AnimationEndListener(){
			@Override
			public void onLocationEndReceive(float x, float y) {
				// TODO Auto-generated method stub
				point4.left = (int)x;
				point4.top = (int)y;
				point4.right =  menu_item_width;
				point4.bottom =  menu_item_height;
				log(String.format("mAnimationEndListener4 (%s,%s)",x,y));
			}
		};
		AnimationEndListener mAnimationEndListener5 = new AnimationEndListener(){
			@Override
			public void onLocationEndReceive(float x, float y) {
				// TODO Auto-generated method stub
				point5.left = (int)x;
				point5.top = (int)y;
				point5.right =  menu_item_width;
				point5.bottom =  menu_item_height;
				log(String.format("mAnimationEndListener5 (%s,%s)",x,y));
			}
		};
	//用于得到结束的位置
	public interface AnimationEndListener{
		void onLocationEndReceive(float x,float y);
	}
	//用于实现一个按钮的回调
	public interface MenuItemOnClickListener{
		void onMenuItemClick(MENU_ITEM_TYPE item);
	}
	
	
	public enum MENU_ITEM_TYPE {

		/**
		 * 征途
		 * */
		MENU_ITEM_1("MENU_ITEM_1"),
		
		/**
		 * 离线
		 * */
		MENU_ITEM_2("MENU_ITEM_2"),
		
		/**
		 * 拍照
		 * */
		MENU_ITEM_3("MENU_ITEM_3"),
		
		/**
		 * 旅友
		 * */
		MENU_ITEM_4("MENU_ITEM_4"),
		/**
		 * 我
		 * */
		MENU_ITEM_5("MENU_ITEM_5")
		;
		
		private String TypeCode;
		
		
		/**
		 * 构造一个具有指定字符串的枚举值
		 * @param inTypeCode
		 */
		private MENU_ITEM_TYPE(String inTypecode){
			this.TypeCode=inTypecode;
		}
		
		public String toString(){
			return TypeCode;
		}
		
		/**
		 * 根据指定的字符串获取相应的枚举值
		 * @param inActionCode
		 * @return
		 */
		public static MENU_ITEM_TYPE getMsgType(String inTypeCode){
			for(MENU_ITEM_TYPE type:MENU_ITEM_TYPE.values()){
				if(type.TypeCode.equals(inTypeCode))
					return type;
			}
			
			return null;
		}
	}
	 void log(String msg){
         Log.d(getClass().getSimpleName(), msg);
	 }
}


