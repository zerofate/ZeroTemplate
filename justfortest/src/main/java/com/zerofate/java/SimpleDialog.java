package com.zerofate.java;

import android.app.Dialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatDialog;

import com.zerofate.template.justfortest.R;

/**
 * Create by timon on 2019/3/14
 **/
public class SimpleDialog extends Dialog {
    public SimpleDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_simple);
    }
}
