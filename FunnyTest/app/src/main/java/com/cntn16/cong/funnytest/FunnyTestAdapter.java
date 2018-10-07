package com.cntn16.cong.funnytest;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FunnyTestAdapter extends ArrayAdapter<FunnyTest>{
    private final Context context;
    protected final LayoutInflater inflater;
    protected final ArrayList<FunnyTest> arrayQuest;


    public FunnyTestAdapter(@NonNull Context context, ArrayList<FunnyTest> funnyTestArrayList, int resource) {
        super(context, 0, funnyTestArrayList);
        this.context = context;
        this.arrayQuest=funnyTestArrayList;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*if (convertView==null) {
            convertView = createItemViewAt(position);
        }
        return convertView;*/
        final ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            if (getItem(position).type!=3) {
                convertView = inflater.inflate(R.layout.layout_test_template_mutiplechoice, null);
                viewHolder.radGroup = (RadioGroup) convertView.findViewById(R.id.radGroup);
                viewHolder.tvQuestion = (TextView) convertView.findViewById(R.id.tvQuestion);
                viewHolder.btnCheck = (Button) convertView.findViewById(R.id.btnCheck);
            }else {
                convertView = inflater.inflate(R.layout.layout_test_template_fillblanks,null);
                viewHolder.etAns = (EditText) convertView.findViewById(R.id.etAnswer);
                viewHolder.tvQuestion=(TextView)convertView.findViewById(R.id.tvQuestion);
                viewHolder.btnCheck = (Button)convertView.findViewById(R.id.btnCheck);
            }
            convertView.setTag(viewHolder);
            convertView=createItemViewAt(position);
        }
        else
            viewHolder=(ViewHolder)convertView.getTag();

        return convertView;
    }

    private View createItemViewAt(int position) {
        View view = createViewByLayoutID(R.layout.layout_test_template_mutiplechoice);
        FunnyTest itemData=getItem(position);
        if(itemData.type==1){
            bindItemDataToView(itemData,view, true);
            checkResult(itemData,view);
        }
        else if(itemData.type==2){
            bindItemDataToView(itemData,view, false);
            checkResult(itemData,view);
        }
        else if(itemData.type==3){
            view=createViewByLayoutID(R.layout.layout_test_template_fillblanks);
            ((TextView)view.findViewById(R.id.tvQuestion)).setText(itemData.question.toString());


            checkResultFillBlank(itemData,view);
        }
        return view;
    }

    private void checkResultFillBlank(final FunnyTest itemData, View view) {
        final EditText etAnswer=(EditText)view.findViewById(R.id.etAnswer);

        Button btnCheck=(Button)view.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAns=etAnswer.getText().toString();

                etAnswer.setText(itemData.answer[0]);
                if (userAns.equals(itemData.answer[0])){
                    etAnswer.setTextColor(Color.BLUE);
                    Toast.makeText(context,"Excellent!",Toast.LENGTH_SHORT).show();
                }
                else{
                    etAnswer.setTextColor(Color.RED);
                    Toast.makeText(context,"False",Toast.LENGTH_SHORT).show();
            }}});
    }

    private void checkResult(final FunnyTest itemData, View view) {
        final RadioButton rad1=(RadioButton)view.findViewById(R.id.radButtonAnsA);
        final RadioButton rad2=(RadioButton)view.findViewById(R.id.radButtonAnsB);
        final RadioButton rad3=(RadioButton)view.findViewById(R.id.radButtonAnsC);
        final RadioButton rad4=(RadioButton)view.findViewById(R.id.radButtonAnsD);
        Button btnCheck=(Button)view.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (itemData.truePos){
                    case 0:
                        if (rad1.isChecked()){
                            rad1.setTextColor(Color.BLUE);
                            Toast.makeText(context, "Excellent!",
                                    Toast.LENGTH_SHORT).show();}
                        else {
                            rad1.setTextColor(Color.RED);
                            Toast.makeText(context, "False!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        rad1.setChecked(true);
                        rad2.setChecked(false);
                        rad3.setChecked(false);
                        rad4.setChecked(false);
                        break;
                    case 1:
                        if (rad2.isChecked()) {
                            rad2.setTextColor(Color.BLUE);
                            Toast.makeText(context, "Excellent!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            rad2.setTextColor(Color.RED);
                            Toast.makeText(context, "False!",
                                    Toast.LENGTH_SHORT).show();}
                        rad1.setChecked(false);
                        rad2.setChecked(true);
                        rad3.setChecked(false);
                        rad4.setChecked(false);
                        break;
                    case 2:
                        if (rad3.isChecked()){
                            rad3.setTextColor(Color.BLUE);
                            Toast.makeText(context, "Excellent!",
                                    Toast.LENGTH_SHORT).show();}
                        else{
                            rad3.setTextColor(Color.RED);
                            Toast.makeText(context, "False!",
                                    Toast.LENGTH_SHORT).show();}
                        rad1.setChecked(false);
                        rad2.setChecked(false);
                        rad3.setChecked(true);
                        rad4.setChecked(false);
                        break;
                    case 3:
                        if (rad4.isChecked()) {
                            rad4.setTextColor(Color.BLUE);
                            Toast.makeText(context, "Excellent!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            rad4.setTextColor(Color.RED);
                            Toast.makeText(context, "False!",
                                    Toast.LENGTH_SHORT).show();}
                        rad1.setChecked(false);
                        rad2.setChecked(false);
                        rad3.setChecked(false);
                        rad4.setChecked(true);
                        break;
                }

            }
        });
    }


    private void bindItemDataToView(FunnyTest itemData,View view,boolean isVisible) {
        ((TextView)view.findViewById(R.id.tvQuestion)).setText(itemData.question.toString());
        final RadioGroup radGroup = ((RadioGroup)view.findViewById(R.id.radGroup));
        final RadioButton radA=(RadioButton)view.findViewById(R.id.radButtonAnsA);
        final RadioButton radB=(RadioButton)view.findViewById(R.id.radButtonAnsB);
        final RadioButton radC=(RadioButton)view.findViewById(R.id.radButtonAnsC);
        final RadioButton radD=(RadioButton)view.findViewById(R.id.radButtonAnsD);
        radA.setText(itemData.answer[0].toString());
        radB.setText(itemData.answer[1].toString());




        if(isVisible==true){
            ((RadioGroup)view.findViewById(R.id.radGroup)).setOrientation(RadioGroup.VERTICAL);
            radC.setText(itemData.answer[2].toString());
            radD.setText(itemData.answer[3].toString());
        }
        else{
            ((RadioGroup)view.findViewById(R.id.radGroup)).setOrientation(RadioGroup.HORIZONTAL);

            radC.setVisibility(View.INVISIBLE);
            radD.setVisibility(View.INVISIBLE);
        }

        radA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radB.isChecked()) radB.setChecked(false);
                if (radC.isChecked()) radC.setChecked(false);
                if (radD.isChecked()) radD.setChecked(false);
            }
        });

        radB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radA.isChecked()) radA.setChecked(false);
                if (radC.isChecked()) radC.setChecked(false);
                if (radD.isChecked()) radD.setChecked(false);
            }
        });
        radC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radB.isChecked()) radB.setChecked(false);
                if (radA.isChecked()) radA.setChecked(false);
                if (radD.isChecked()) radD.setChecked(false);
            }
        });
        radD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radB.isChecked()) radB.setChecked(false);
                if (radC.isChecked()) radC.setChecked(false);
                if (radA.isChecked()) radA.setChecked(false);
            }
        });
    }

    private View createViewByLayoutID(int item_layout) {
        return LayoutInflater.from(this.context).inflate(item_layout,null);
    }

}