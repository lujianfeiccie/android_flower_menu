package com.example.flower_menu;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.example.flower_menu.MenuPopupWindow.AnimationEndListener;
/**
 * 
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.selfdrive.util.MyAnimation.java
系统编号：
系统名称：SelfDrive
模块编号：
模块名称：
设计文档：
创建日期：2013-11-18 上午3:54:07
作 者：陆键霏
内容摘要：用来控制弹出菜单的动画
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class MyAnimation {
	// 图标的动画(入动画)
	public static void startAnimationsIn(ViewGroup viewgroup, int durationMillis) {

		viewgroup.setVisibility(0);
		for (int i = 0; i < viewgroup.getChildCount(); i++) {
			viewgroup.getChildAt(i).setVisibility(0);
			viewgroup.getChildAt(i).setClickable(true);
			viewgroup.getChildAt(i).setFocusable(true);
		}
		Animation animation;
		animation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setFillAfter(true);
		animation.setDuration(durationMillis);
		viewgroup.startAnimation(animation);

	}

	// 图标的动画(出动画)
	public static void startAnimationsOut(final ViewGroup viewgroup,
			int durationMillis, int startOffset) {

		Animation animation;
		animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setFillAfter(true);
		animation.setDuration(durationMillis);
		animation.setStartOffset(startOffset);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {}
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			@Override
			public void onAnimationEnd(Animation arg0) {
				viewgroup.setVisibility(8);
				for (int i = 0; i < viewgroup.getChildCount(); i++) {
					viewgroup.getChildAt(i).setVisibility(8);
					viewgroup.getChildAt(i).setClickable(false);
					viewgroup.getChildAt(i).setFocusable(false);
				}
			}
		});
		viewgroup.startAnimation(animation);
	}
	
	// 图标的动画(出动画)
	public static void startAnimationsOut(final ViewGroup viewgroup,
			int durationMillis, int startOffset,final Animation.AnimationListener listener) {
		
		Animation animation;
		animation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setFillAfter(true);
		animation.setDuration(durationMillis);
		animation.setStartOffset(startOffset);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				if(listener!=null)
				listener.onAnimationStart(animation);
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				if(listener!=null)
				listener.onAnimationRepeat(animation);
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				viewgroup.setVisibility(8);
				for (int i = 0; i < viewgroup.getChildCount(); i++) {
					viewgroup.getChildAt(i).setVisibility(8);
					viewgroup.getChildAt(i).setClickable(false);
					viewgroup.getChildAt(i).setFocusable(false);
				}
				if(listener!=null)
				listener.onAnimationEnd(animation);
			}
		});
		viewgroup.startAnimation(animation);
	}
	
	
	
	// 图标的动画(入动画)
		public static void startAnimationsIn(View view, int durationMillis) {

			view.setVisibility(0);
			Animation animation;
			animation = new RotateAnimation(0, 135, Animation.RELATIVE_TO_SELF,
					0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setFillAfter(true);
			animation.setDuration(durationMillis);
			view.startAnimation(animation);

		}
	// 图标的动画(出动画)
		public static void startAnimationsOut(final View view,
				int durationMillis, int startOffset,final Animation.AnimationListener listener) {
			
			Animation animation;
			animation = new RotateAnimation(135, 0, Animation.RELATIVE_TO_SELF,
					0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setFillAfter(true);
			animation.setDuration(durationMillis);
			animation.setStartOffset(startOffset);
			animation.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					if(listener!=null)
					listener.onAnimationStart(animation);
				}
				@Override
				public void onAnimationRepeat(Animation animation) {
					if(listener!=null)
					listener.onAnimationRepeat(animation);
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					view.setVisibility(8);
					if(listener!=null)
					listener.onAnimationEnd(animation);
				}
			});
			view.startAnimation(animation);
		}
		/**
		 *图标的动画(入动画) 为每个单独按钮设计 
		 * @param view
		 * @param durationMillis
		 * @param x
		 * @param radius 圆弧半径
		 */
		public static void startAnimationsIn(final View view, int durationMillis,final float x,float radius,final AnimationEndListener mAnimationEndListener) {

			
			Animation animation;
			//TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta
			final float y = getYfromX(x,radius);
			
			/*log("==============start===========================");
			log(String.format("(0,0) to (%s,%s) from radius=%s left=%s", x,y,radius,view.getLeft()));
			log("==============end===========================");
			*/
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, x ,Animation.RELATIVE_TO_SELF,y);
			
			animation.setFillAfter(true);
			animation.setDuration(durationMillis);
			((TranslateAnimation)animation).setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
				}
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					log("onAnimationEnd");
					if(mAnimationEndListener!=null){
						mAnimationEndListener.onLocationEndReceive(view.getLeft()+x, view.getTop()+y);
					}
				}
			});
			view.startAnimation(animation);
		}
		static float getYfromX(float x,float radius){
			float y=0;
			y = (float) Math.sqrt(radius*radius - x*x);
			y = Math.abs(y) * (-1);
			return y;
		}
		public static void startAnimationsOut(final View view, int durationMillis,float x,float radius,final Animation.AnimationListener listener) {
			view.setVisibility(0);
			Animation animation;
			//TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta
			float y=0;
			y = getYfromX(x,radius);
			y = Math.abs(y);
			animation = new TranslateAnimation(x, 0, -y, y);
			animation.setFillAfter(true);
			animation.setDuration(durationMillis);
			animation.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					if(listener!=null)
					listener.onAnimationStart(animation);
				}
				@Override
				public void onAnimationRepeat(Animation animation) {
					if(listener!=null)
					listener.onAnimationRepeat(animation);
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					view.setVisibility(View.GONE);
					if(listener!=null)
					listener.onAnimationEnd(animation);
				}
			});
			view.startAnimation(animation);
		}
		static void log(String msg){
			Log.d("MyAnimation", msg);
		}
}







