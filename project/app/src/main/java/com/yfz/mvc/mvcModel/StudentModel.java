package com.yfz.mvc.mvcModel;

import com.yfz.mvc.studentBean.StudentBean;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    //list储存所有实现的接口
    private List<StudentModelListener> mListenerList = new ArrayList<>();
    //学生对象list
    private List<StudentBean> mStudentList = new ArrayList<>();
    /**
     * 添加接口
     */
    public void addOnUpdateViewListener(StudentModelListener studentModelListener){
        if(!mListenerList.contains(studentModelListener)){
            mListenerList.add(studentModelListener);
        }
    }
    /**
     * 添加学生方法
     * 从Controller获取到学生对象数据后，对该数据进行操作，添加至学生list中。
     * 数据操作完毕后，通知View层进行更新UI事件
     * @param studentBean
     */
    public void addStudent(StudentBean studentBean){
        if(null != mStudentList && null != studentBean){
            if(!mStudentList.contains( studentBean )) {
                mStudentList.add(studentBean);
                //把数据处理结束的消息，发送到所有实现的接口
                for(StudentModelListener listener: mListenerList){
                    listener.OnModelAddStudentCompleted(mStudentList,studentBean);
                }
            }
        }
    }
    //删除学生对象
    public void removeStudent(int index){
        if(null != mStudentList && index>=0 && mStudentList.size()>0){
            String studentName = "删除：学生: "+mStudentList.get(index).getName() +" 性别: "+mStudentList.get(index).getGender() +" 学号："+mStudentList.get(index).getId();
            mStudentList.remove(index);
            //把数据处理结束的消息，发送到所有实现的接口
            for(StudentModelListener listener: mListenerList){
                listener.OnModelRemoveStudentCompleted(mStudentList,studentName);
            }
        }
    }

}
