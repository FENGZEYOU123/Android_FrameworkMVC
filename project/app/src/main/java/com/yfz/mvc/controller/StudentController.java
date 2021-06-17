package com.yfz.mvc.controller;

import com.yfz.mvc.Bean.StudentBean;
import com.yfz.mvc.MainActivity;
import com.yfz.mvc.model.StudentModel;
import com.yfz.mvc.notification.OnUpdateViewListener;

public class StudentController {
    private static volatile StudentController mInstance = null;
    public StudentController(){}
    private StudentModel studentModel = StudentModel.getInstance();
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
     * 向添加Model层添加接口，当model层对数据处理完毕时，返回结果过来，然后更新view的UI
     */
    public void addOnUpdateViewListener(){
        studentModel.addOnUpdateViewListener(new OnUpdateViewListener() {
            @Override
            public void addStudent(StudentBean studentBean) {
                mainActivity.updateViewUi_studentAmount(); //更新数量
                mainActivity.updateViewUi_addedStudent(studentBean);
            }

            @Override
            public void removeStudent(String studentName) {
                mainActivity.updateViewUi_studentAmount();
                mainActivity.updateViewUi_removeStudent(studentName);
            }
        });
    }

    /**
     * 将MainActivity传入至Controller。实际上Activity本来就充当Controller的角色，
     * 但是所有的逻辑都写在Activity里代码量会非常大，不利于维护。所以我们把部分方法抽离出来，写成独立的Controller
     * @param mainActivity
     */
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        addOnUpdateViewListener();
    }


}
