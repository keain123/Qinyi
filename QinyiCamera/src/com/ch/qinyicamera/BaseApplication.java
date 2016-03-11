package com.ch.qinyicamera;

import org.litepal.LitePalApplication;

import cn.bmob.v3.Bmob;

public class BaseApplication extends LitePalApplication {
	
	@Override
	public void onCreate() {
		super.onCreate();
		Bmob.initialize(getApplicationContext(), "401bb0f93eee87776be96fb3cc677e94");
	}

}
