package com.yfz.mvc.mvcModel;

import com.yfz.mvc.studentBean.StudentBean;

import java.util.List;

/**
 * 接口，M层处理完毕后，会对所有注册过的接口发送消息
 */
public interface StudentModelListener {
        //添加用户后回调
        void OnModelAddStudentCompleted(List<StudentBean> mStudentList,StudentBean studentBean);
        //删除用户后回调
        void OnModelRemoveStudentCompleted(List<StudentBean> mStudentList,String studentName);
}
