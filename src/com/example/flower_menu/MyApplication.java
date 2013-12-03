package com.example.flower_menu;

import android.app.Application;
import android.util.DisplayMetrics;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.example.flower_menu.MyApplication.java
系统编号：
系统名称：flower_menu
模块编号：
模块名称：
设计文档：
创建日期：2013-12-3 上午2:52:38
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class MyApplication extends Application {
	private static MyApplication mInstance = null;
	private ScreenSize mScreenSize;
	
	public static MyApplication getInstance() {
		return mInstance;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
		DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
         mScreenSize = new ScreenSize();
         mScreenSize.width = metrics.widthPixels;
         mScreenSize.height = metrics.heightPixels;
         
         if(mScreenSize.width > mScreenSize.height){
        	 int tempSize = mScreenSize.width;
        	 mScreenSize.width = mScreenSize.height;
        	 mScreenSize.height = tempSize;
         }
	}
	public ScreenSize getScreenSize(){
		return mScreenSize;
	}
	public class ScreenSize{
		public int width;
		public int height;
	}
}