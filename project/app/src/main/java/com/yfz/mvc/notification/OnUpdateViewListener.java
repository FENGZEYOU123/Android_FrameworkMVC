package com.yfz.mvc.notification;

import com.yfz.mvc.Bean.StudentBean;

/**
 * model对数据处理完毕后,传给通知view更新UI的
 */
public interface OnUpdateViewListener {
        //添加用户后回调
        void addStudent(StudentBean studentBean);
        //删除用户后回调
        void removeStudent(String studentName);
}
