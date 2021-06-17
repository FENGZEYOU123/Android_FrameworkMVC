package com.yfz.mvc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yfz.mvc.Bean.StudentBean;
import com.yfz.mvc.controller.StudentController;
import com.yfz.mvc.model.StudentModel;

/**
 * Author: 游丰泽
 * Time: 9999/01/01
 * Describe: 探究MVC架构设计
 */
public class MainActivity extends AppCompatActivity {
    private Button vBtnDelete,vBtnAdd;
    private TextView vTvTotalPeopleAmount, vTvStudentInfo;
    //Controller层
    private StudentController studentController = StudentController.getInstance();
    //Model层
    private StudentModel studentModel = StudentModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentController.setMainActivity(this);
        initial();
        addListener();
    }
    private void initial(){
        vBtnDelete = findViewById(R.id.vBtnDelete);
        vBtnAdd = findViewById(R.id.vBtnAdd);
        vTvTotalPeopleAmount = findViewById(R.id.vTvTotalPeopleAmount);
        vTvStudentInfo = findViewById(R.id.vTvStudentInfo);
    }
    private void addListener(){
        //添加学生按钮，点击后，将事件传给Controller层进行处理
        vBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //随机一个id,性别,姓名
                int max=100,min=1;
                int id = (int) (Math.random()*(max-min)+min);
                //添加一个学生对象
                studentController.addStudent(new StudentBean("张三-"+id ,id,id<=50?StudentBean.Gender.男:StudentBean.Gender.女));
            }
        });
        vBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentController.removeStudent(studentModel.getStudentAmount()-1);
            }
        });
    }
    /**
     * activity将添加用户的操作发给controller，controller发给model，model对数据处理完毕后，再通知activity更新UI
     */
    public void updateViewUi_studentAmount(){
        if(null != vTvTotalPeopleAmount){
            vTvTotalPeopleAmount.setText(" "+studentModel.getStudentAmount());
        }
    }
    public void updateViewUi_addedStudent(StudentBean studentBean){
        if(null != vTvStudentInfo){
            vTvStudentInfo.setText(
                    "被添加的学生信息:"+
                    "\n名字："+studentBean.getName()+
                    "\n学号: "+studentBean.getId()+
                    "\n性别: "+studentBean.getGender());
        }
    }
    public void updateViewUi_removeStudent(String studentName){
        if(null != vTvStudentInfo){
            vTvStudentInfo.setText(studentName);
        }
    }
}