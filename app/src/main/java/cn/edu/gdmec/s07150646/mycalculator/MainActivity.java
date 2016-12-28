package cn.edu.gdmec.s07150646.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button cabutton;
    private EditText weitext;
    private RadioButton manbox;
    private RadioButton womanbox;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cabutton= (Button) findViewById(R.id.calculator);
        weitext= (EditText) findViewById(R.id.weight);
        manbox= (RadioButton) findViewById(R.id.man);
        womanbox= (RadioButton) findViewById(R.id.woman);
        result= (TextView) findViewById(R.id.result);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private void registerEvent(){
        cabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weitext.getText().toString().trim().equals("")){
                    if(manbox.isChecked()||womanbox.isChecked()){
                        Double weight=Double.parseDouble(weitext.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("-----评估结果为--------- \n");
                        if(manbox.isChecked()){
                            sb.append("男性标准身高为：");
                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");

                        }
                        if(womanbox.isChecked()){
                            sb.append("女性标准身高为：");
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");

                        }
                        result.setText(sb.toString());
                    }else{
                        show("请选择性别");
                    }
                }else{
                    show("请输入体重");
                }
            }
        });
    }
    private double evaluateHeight(double weight, String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return  height;
    }
    private void show(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
       alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
