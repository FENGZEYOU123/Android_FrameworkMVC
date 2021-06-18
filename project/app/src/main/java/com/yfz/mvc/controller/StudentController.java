package com.yfz.mvc.controller;

import com.yfz.mvc.Bean.StudentBean;
import com.yfz.mvc.MainActivity;
import com.yfz.mvc.model.StudentModel;
import com.yfz.mvc.notification.OnUpdateViewListener;

import java.util.List;

public class StudentController implements OnUpdateViewListener{
    private static volatile StudentController mInstance = null;
    public StudentController(){}
    //创建M层实例
    private StudentModel studentModel = new StudentModel();
    //MainActivity
    private MainActivity mainActivity;
    //DCL
    public static StudentController getInstance(){
        if(mInstance == null){
            synchronized (StudentController.class){
                if(mInstance == null){
                    mInstance = new StudentController();
                }
            }
        }
        return mInstance;
    }
    /**
     * 向Model层发送添加学生数据的指令
     * @param studentBean
     */
    public void addStudent(StudentBean studentBean){
        //将储存学生对象的数据操作，交给StudentModel去处理
        studentModel.addStudent(studentBean);
    }

    /**
     * 向Model层发送删除学生数据的指令
     */
    public void removeStudent(int index){
        //将储存学生对象的数据操作，交给StudentModel去处理
        studentModel.removeStudent(index);
    }

    /**
     * 将MainActivity传入至Controller。实际上Activity本来就充当Controller的角色，
     * 但是Activity同时又操作如何对V层更新的逻辑，所有的逻辑都写在Activity里代码量会非常大，不利于维护。
     * 所以我们把部分方法抽离出来，写成独立的Controller
     * @param mainActivity
     */
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        //向M层注册接口监听数据处理完成的事件
        studentModel.addOnUpdateViewListener(this);
    }

    //实现接口，调用activity提供更新V层的方法
    @Override
    public void OnModelAddStudentCompleted(List<StudentBean> mStudentList, StudentBean studentBean) {
        mainActivity.updateViewUi_studentAmount(mStudentList.size());
        mainActivity.updateViewUi_addedStudent(studentBean);
    }
    //实现接口，调用activity提供更新V层的方法
    @Override
    public void OnModelRemoveStudentCompleted(List<StudentBean> mStudentList, String studentName) {
        mainActivity.updateViewUi_studentAmount(mStudentList.size());
        mainActivity.updateViewUi_removeStudent(studentName);
    }
}
