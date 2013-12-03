package com.example.flower_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.flower_menu.MenuPopupWindow.MENU_ITEM_TYPE;
import com.example.flower_menu.MenuPopupWindow.MenuItemOnClickListener;

public class MainActivity extends Activity implements OnClickListener{
	MenuPopupWindow mMenuPopupWindow; //弹出菜单
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	public void initView() {
		mMenuPopupWindow = new MenuPopupWindow(this,menuItemOnClick);
		findViewById(R.id.bt_clickme).setOnClickListener(this);
	}
	 //为弹出窗口实现监听类
    private MenuItemOnClickListener  menuItemOnClick = new MenuItemOnClickListener(){
		@Override
		public void onMenuItemClick(MENU_ITEM_TYPE item) {
			// TODO Auto-generated method stub
			mMenuPopupWindow.dismissDirectly();
			switch (item) {
            case MENU_ITEM_1:
                break;
            case MENU_ITEM_2:
            	break;
            case MENU_ITEM_3:
            	break;
            case MENU_ITEM_4:
            	break;
            case MENU_ITEM_5:
            	break;
            default:
                    break;
            }
		}
    };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_clickme:
		{
			mMenuPopupWindow.showAtLocation(MainActivity.this.findViewById(R.id.bt_clickme),
               		Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,
               		0, 0); //设置layout在PopupWindow中显示的位置
		}
			break;

		default:
			break;
		}
	}

}
