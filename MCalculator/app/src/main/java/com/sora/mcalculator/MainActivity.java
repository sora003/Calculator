package com.sora.mcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //定义Button
    Button bt_1;        //1
    Button bt_2;        //2
    Button bt_3;        //3
    Button bt_4;        //4
    Button bt_5;        //5
    Button bt_6;        //6
    Button bt_7;        //7
    Button bt_8;        //8
    Button bt_9;        //9
    Button bt_0;        //0
    Button bt_dot;      //.
    Button bt_add;      //+
    Button bt_minus;    //-
    Button bt_multiply; //×
    Button bt_divide;   //÷
    Button bt_equal;    //=
    Button bt_delete;   //删除一位
    Button bt_clear;    //清除
    TextView et_view;  //显示框
    boolean clear_flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //实例化Button
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_dot = (Button) findViewById(R.id.bt_dot);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_minus = (Button) findViewById(R.id.bt_minus);
        bt_multiply = (Button) findViewById(R.id.bt_multiply);
        bt_divide = (Button) findViewById(R.id.bt_divide);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        et_view = (TextView) findViewById(R.id.et_view);
        //OnClickListener监听事件
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_dot.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //监听并对输入串作进一步处理
    @Override
    public void onClick(View v) {
        String str = et_view.getText().toString();
        switch (v.getId()) {
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_0:
            case R.id.bt_dot:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_view.setText("");
                }
                et_view.setText(str + ((Button) v).getText());
                break;
            case R.id.bt_add:
            case R.id.bt_minus:
            case R.id.bt_multiply:
            case R.id.bt_divide:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_view.setText("");
                }
                et_view.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.bt_delete:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_view.setText("");
                } else if ((str != null) && (!str.equals("")))
                    et_view.setText(str.substring(0, str.length() - 1));
                break;
            case R.id.bt_clear:
                et_view.setText("");
                break;
            case R.id.bt_equal:
                getResult();
                break;
        }
    }

    //计算
    private void getResult() {
        String exp = et_view.getText().toString();
        if (exp == null || exp.equals("")) {
            return;
        }
        if (!exp.contains(" ")) {
            return;
        }
        if (clear_flag) {
            clear_flag = false;
            return;
        }
        clear_flag = true;
        double result = 0;
        int space = exp.indexOf(' ');
        String s1 = exp.substring(0, space);
        String op = exp.substring(space + 1, space + 2);
        String s2 = exp.substring(space + 3);
        //s1,s2都不为空
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            switch (op) {
                case "+":
                    result = d1 + d2;
                    break;
                case "－":
                    result = d1 - d2;
                    break;
                case "×":
                    result = d1 * d2;
                    break;
                case "÷":
                    if (d2 == 0)
                        result = 0;
                    else
                        result = d1 / d2;
                    break;
            }
            if (!s1.contains(".") && !s1.contains(".")) {
                switch (op) {
                    case "+":
                    case "－":
                    case "×":
                        int r = (int) result;
                        et_view.setText(r + "");
                        break;
                    case "÷":
                        if (d1 % d2 == 0) {
                            r = (int) result;
                            et_view.setText(r + "");
                        } else
                            et_view.setText(result + "");
                }
            }
            else et_view.setText(result + "");
        }
        //s1为空,s2不空,不计算
        if (s1.equals("") && !s2.equals("")) {
            return;
        }
        //s1不空,s2为空,不计算
        if (s1.equals("") && !s2.equals("")) {
            et_view.setText(s1);

        }
    }
}


