package com.yfz.mvc.model;

import com.yfz.mvc.Bean.StudentBean;
import com.yfz.mvc.MainActivity;
import com.yfz.mvc.notification.OnUpdateViewListener;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private static volatile StudentModel mInstance = null;
    public StudentModel(){}
    //通知view更新UI接口
    private OnUpdateViewListener mOnUpdateViewListener = null;
    //学生对象list
    private List<StudentBean> mStudentList = new ArrayList<>();
    //MainActivity
    private MainActivity mainActivity = null;
    //DCL
    public static StudentModel getInstance(){
        if(mInstance == null){
            synchronized (StudentModel.class){
                if(mInstance == null){
                    mInstance = new StudentModel();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加接口
     */
    public void addOnUpdateViewListener(OnUpdateViewListener onUpdateViewListener){
        mOnUpdateViewListener = onUpdateViewListener;
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
                if(null != mOnUpdateViewListener){
                    mOnUpdateViewListener.addStudent(studentBean);
                }
            }
        }
    }
    //删除学生对象
    public void removeStudent(int index){
        if(null != mStudentList && index>=0){
            String studentName = "删除：学生: "+mStudentList.get(index).getName() +" 性别: "+mStudentList.get(index).getGender() +" 学号："+mStudentList.get(index).getId();
            mStudentList.remove(index);
            if(null != mOnUpdateViewListener){
                mOnUpdateViewListener.removeStudent(studentName);
            }
        }
    }
    /**
     * 返回当前学生总人数
     */
    public int getStudentAmount(){
        int amount = 0;
        if(null != mStudentList){
            amount = mStudentList.size();
        }
        return amount;
    }
}
