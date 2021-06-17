package com.yfz.mvc.notification;

import com.yfz.mvc.Bean.StudentBean;

/**
 * model层对数据处理完毕后,回调给C层
 */
public interface OnUpdateViewListener {
        //添加用户后回调
        void addStudent(StudentBean studentBean);
        //删除用户后回调
        void removeStudent(String studentName);
}
