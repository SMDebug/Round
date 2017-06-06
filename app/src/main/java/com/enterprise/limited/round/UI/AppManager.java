package com.enterprise.limited.round.UI;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2016/4/26.
 */
public class AppManager {
    private static AppManager instance = new AppManager();
    // 用来存放所有的Activity
    private Stack<Activity> activities;

    public static AppManager getInstance() {
        return instance;
    }

    // 添加Activity
    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new Stack<Activity>();
        }

        activities.add(activity);
    }



    // 结束所有的activity
    public void finishAllActivity() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }

        // 清空堆栈
        activities.clear();
    }

    public AppManager() {
        super();
    }

    /**
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    // 退出程序

    public void appExit() {
        // 1.首先，结束所有的activity
        finishAllActivity();

        // 2. killProcess

        android.os.Process.killProcess(android.os.Process.myPid());
        // 3.调用System.exit(0)
        System.exit(0);
    }
}
