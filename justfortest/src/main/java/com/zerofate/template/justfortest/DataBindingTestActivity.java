package com.zerofate.template.justfortest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.ToastUtils;
import com.zerofate.template.justfortest.databinding.ActivityDataBindingTestBinding;
import com.zerofate.template.justfortest.databinding.ActivityMeaninglessBinding;

public class DataBindingTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingTestBinding dataBindingTestActivity = DataBindingUtil.setContentView(
                this, R.layout.activity_data_binding_test);
        dataBindingTestActivity.btnTest.setOnClickListener(v -> {
            User user = new User();
            user.name = "你好";
            user.address = new User.Address();
            user.address.province = "广东";
            dataBindingTestActivity.setUser(user);
        });
        dataBindingTestActivity.setHandler(new EventHandler());
        dataBindingTestActivity.setPresenter(new Presenter());
        dataBindingTestActivity.stub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {

            }
        });
        dataBindingTestActivity.stub.getViewStub().setVisibility(View.VISIBLE);
        ((ActivityMeaninglessBinding) dataBindingTestActivity.stub.getBinding()).edit.setText("11111");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onProvinceClicked(View view) {
        ToastUtils.showShort("点击");
    }

    public class EventHandler {
        public void onClick(View view) {
            ToastUtils.showShort("onClick view");
        }
    }

    public class Presenter {
        public void onClick() {
            ToastUtils.showShort("onClick presenter");
        }

        public void onClickRoot() {
            ToastUtils.showShort("onClickRoot presenter");
        }
    }

    public static class User {
        private String name;
        private Address address;

        public static class Address {
            public String province;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }
}
