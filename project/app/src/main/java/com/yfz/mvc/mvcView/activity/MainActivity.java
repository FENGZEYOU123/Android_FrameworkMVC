package com.yfz.mvc.mvcView.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yfz.mvc.studentBean.StudentBean;
import com.yfz.mvc.R;
import com.yfz.mvc.mvcController.StudentController;

/**
 * Author: 游丰泽
 * Time: 9999/01/01
 * Describe: 探究MVC架构设计
 * Github: https://blog.csdn.net/ruiruiddd/article/details/117990466
 */
public class MainActivity extends AppCompatActivity {
    private Button vBtnDelete,vBtnAdd;
    private TextView vTvTotalPeopleAmount, vTvStudentInfo;
    //Controller层
    private StudentController studentController = StudentController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        addListener();
        studentController.setMainActivity(this);
    }
    private void initial(){
        vBtnDelete = findViewById(R.id.vBtnDelete);
        vBtnAdd = findViewById(R.id.vBtnAdd);
        vTvTotalPeopleAmount = findViewById(R.id.vTvTotalPeopleAmount);
        vTvStudentInfo = findViewById(R.id.vTvStudentInfo);
    }
    private void addListener(){
        //监听 添加按钮 的点击事件
        vBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //随机一个id,性别,姓名
                int max=100,min=1;
                int id = (int) (Math.random()*(max-min)+min);
                //new一个学生对象，并将响应用户的点击事件与所要处理的信息一并交给Controller层进行处理
                studentController.addStudent(new StudentBean("张三-"+id ,id,id<=50?StudentBean.Gender.男:StudentBean.Gender.女));
            }
        });
        //监听 删除按钮 的点击事件
        vBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将响应用户的删除点击事件与所要处理的信息一并交给Controller层进行处理
                studentController.removeStudent(0);
            }
        });
    }

    //提供更新UI的方法
    public void updateViewUi_studentAmount(int studentAmount){
        if(null != vTvTotalPeopleAmount){
            vTvTotalPeopleAmount.setText(""+studentAmount);
        }
    }
    //提供更新UI的方法
    public void updateViewUi_addedStudent(StudentBean studentBean){
        if(null != vTvStudentInfo){
            vTvStudentInfo.setText(
                    "被添加的学生信息:"+
                            "\n名字："+studentBean.getName()+
                            "\n学号: "+studentBean.getId()+
                            "\n性别: "+studentBean.getGender());
        }
    }
    //提供更新UI的方法
    public void updateViewUi_removeStudent(String studentName){
        if(null != vTvStudentInfo){
            vTvStudentInfo.setText(studentName);
        }
    }

}
