package com.example.flower_menu;

import android.app.Application;
import android.util.DisplayMetrics;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.example.flower_menu.MyApplication.java
ϵͳ��ţ�
ϵͳ���ƣ�flower_menu
ģ���ţ�
ģ�����ƣ�
����ĵ���
�������ڣ�2013-12-3 ����2:52:38
�� �ߣ�½����
����ժҪ��
���еĴ�������������Σ���������������������෽������
�ļ�����:
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