package com.example.javaapplication.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;

public class CreateTable {
    String[] TitleColumn;
    Context context;
    int column;
    ArrayList<String[]> content;



    public CreateTable(Context context,String[] TitleColumn,int column,ArrayList<String[]> content){
        this.context = context;
        this.TitleColumn = TitleColumn;
        this.column = column;
        this.content = content;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TableLayout createTL(){
        TableLayout tableLayout = new TableLayout(this.context);

        //标题栏
        TableRow TRtilte = createTR(this.TitleColumn,16,Color.parseColor("#000000"));
        TableLayout.LayoutParams TLParamsTitle = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT,1);
        tableLayout.addView(TRtilte,TLParamsTitle);

        //内容
        for(int i=0;i < column;i++){
            TableRow tableRow = new TableRow(this.context);
            String[] obj = content.get(i);

            for(int j=0;j < obj.length;j++){
                TextView textView = new TextView(this.context);
                textView.setText(obj[j]);
                textView.setTextSize(15);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setPadding(0,10,0,10);
                TableRow.LayoutParams TRLParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT,1);
                tableRow.addView(textView,TRLParams);
            }

            TableLayout.LayoutParams TLParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT,1);
            tableLayout.addView(tableRow,TLParams);
        }

        return tableLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TextView createTV(String name, int size, int color){
        TextView textView = new TextView(this.context);
        textView.setText(name);
        textView.setTextSize(size);
        textView.setTextColor(color);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return textView;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TableRow createTR(String[] names, int size, int color) {
        TableRow tableRow = new TableRow(this.context);

        for(int column=0;column <names.length;column++){
            TextView tv = createTV(names[column],size,color);//Color.parseColor("#000")
            tv.setPadding(2,0,2,0);
            TableRow.LayoutParams TRLParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT,1);
            tableRow.addView(tv,TRLParams);
        }
        return tableRow;
    }
}
