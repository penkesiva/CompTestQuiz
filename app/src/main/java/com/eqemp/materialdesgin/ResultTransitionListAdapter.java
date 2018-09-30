package com.eqemp.materialdesgin;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ResultTransitionListAdapter extends BaseAdapter {

    ViewHolder viewHolder;

    private ArrayList<ResultsListItem> mItems = new ArrayList<ResultsListItem>();
    private Context mContext;

    public ResultTransitionListAdapter(Context context, ArrayList<ResultsListItem> list) {
        mContext = context;
        mItems = list;
    }


    public int getCount() {
        return mItems.size();
    }


    public Object getItem(int position) {
        return mItems.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {

            // inflate the layout
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.fragment_resultlist_item_transition, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.question = (TextView) v.findViewById(R.id.question);
            viewHolder.answeroption = (TextView) v.findViewById(R.id.answeroption);
            viewHolder.description = (TextView) v.findViewById(R.id.description);
            viewHolder.useroption = (TextView) v.findViewById(R.id.useranswer);
            viewHolder.ques_img = (ImageView) v.findViewById(R.id.ques_img);
            viewHolder.ans_img = (ImageView) v.findViewById(R.id.ans_img);
            viewHolder.user_img = (ImageView) v.findViewById(R.id.user_img);

            // store the holder with the view.
            v.setTag(viewHolder);

        } else {
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String que = mItems.get(position).getQuestion();
        String answer = mItems.get(position).getAnswer();
        String useranswer = mItems.get(position).getUseranswer();
        String desc = mItems.get(position).getDescription();


        if (que.contains("img =")) {
            viewHolder.ques_img.setVisibility(View.VISIBLE);
            String ss = que.substring(que.indexOf("img ="));
            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            StringBuilder sb = new StringBuilder(imgname);
            Log.d("data", ss);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.ques_img.setImageResource(identifier);

        } else if (que.contains("img=")) {
            viewHolder.ques_img.setVisibility(View.VISIBLE);
            String ss = que.substring(que.indexOf("img="));
            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            StringBuilder sb = new StringBuilder(imgname);
            Log.d("data", ss);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.ques_img.setImageResource(identifier);

        } else {
            viewHolder.ques_img.setVisibility(View.GONE);

        }

        if (useranswer.contains("img =")) {
            viewHolder.user_img.setVisibility(View.VISIBLE);
            String ss = useranswer.substring(useranswer.indexOf("img ="));
            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            StringBuilder sb = new StringBuilder(imgname);
            Log.d("data", ss);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.user_img.setImageResource(identifier);

        }
        if (useranswer.contains("img=")) {
            viewHolder.user_img.setVisibility(View.VISIBLE);
            String ss = useranswer.substring(useranswer.indexOf("img="));
            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            StringBuilder sb = new StringBuilder(imgname);
            Log.d("data", ss);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.user_img.setImageResource(identifier);

        } else {
            viewHolder.user_img.setVisibility(View.GONE);

        }

        if (answer != null && answer.contains("img =")) {
            viewHolder.ans_img.setVisibility(View.VISIBLE);
            String ss = answer.substring(answer.indexOf("img ="));
            Log.d("data", ss);
            Log.d("index of ", "" + ss.indexOf("\""));
            Log.d("index of =", "" + ss.indexOf("="));

            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            Log.d("imgname", imgname);

            StringBuilder sb = new StringBuilder(imgname);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.ans_img.setImageResource(identifier);

        } else if (answer != null && answer.contains("img=")) {
            viewHolder.ans_img.setVisibility(View.VISIBLE);
            String ss = answer.substring(answer.indexOf("img="));
            Log.d("data", ss);
            Log.d("index of ", "" + ss.indexOf("\""));
            Log.d("index of =", "" + ss.indexOf("="));

            String imgname = ss.substring(ss.indexOf("="), ss.indexOf("\""));
            Log.d("imgname", imgname);

            StringBuilder sb = new StringBuilder(imgname);
            if (sb.charAt(0) == '=') {
                Log.d("datasub", "" + sb.deleteCharAt(0));

            } else
                Log.d("datasub", "" + sb.toString());


            int identifier = ((ResultsActivity) mContext).getResources().getIdentifier(sb.toString(), "drawable", ((ResultsActivity) mContext).getPackageName());
            viewHolder.ans_img.setImageResource(identifier);

        } else {
            viewHolder.ans_img.setVisibility(View.GONE);

        }

        //viewHolder.question.setText(que);
        //viewHolder.answeroption.setText(":"+answer);

        if (que.contains("img =")) {
            StringBuffer text = new StringBuffer(que);
            text.replace(que.indexOf("\"img ="), que.length(), "");
            viewHolder.question.setText(text);
        } else if (que.contains("img=")) {
            StringBuffer text = new StringBuffer(que);
            text.replace(que.indexOf("\"img="), que.length(), "");
            viewHolder.question.setText(Html.fromHtml(text.toString()));
        } else {
            viewHolder.question.setText(Html.fromHtml(que));
        }

        if (answer != null && answer.contains("img =")) {
            StringBuffer text = new StringBuffer(answer);
            text.replace(answer.indexOf("\"img ="), answer.length(), "");
            viewHolder.answeroption.setText(":" + Html.fromHtml(text.toString()));
        } else if (answer != null && answer.contains("img=")) {
            StringBuffer text = new StringBuffer(answer);
            text.replace(answer.indexOf("\"img="), answer.length(), "");
            viewHolder.answeroption.setText(":" + Html.fromHtml(text.toString()));
        } else {
            viewHolder.answeroption.setText(":" + Html.fromHtml(answer));
        }


        if (useranswer.equalsIgnoreCase("Not answered")) {
            viewHolder.useroption.setTextColor(Color.parseColor("#FFBF00"));
        } else if (useranswer.equalsIgnoreCase(answer)) {
            viewHolder.useroption.setTextColor(Color.GREEN);

        } else {
            viewHolder.useroption.setTextColor(Color.RED);
        }
        if (useranswer.contains("img =")) {
            StringBuffer text = new StringBuffer(useranswer);
            text.replace(useranswer.indexOf("\"img ="), useranswer.length(), "");
            viewHolder.useroption.setText(":" + Html.fromHtml(text.toString()));
        } else if (useranswer.contains("img=")) {
            StringBuffer text = new StringBuffer(useranswer);
            text.replace(useranswer.indexOf("\"img="), useranswer.length(), "");
            viewHolder.useroption.setText(":" + Html.fromHtml(text.toString()));
        } else {
            viewHolder.useroption.setText(":" + Html.fromHtml(useranswer));
        }

        if (!desc.equalsIgnoreCase(""))
            viewHolder.description.setText(desc);
        else
            viewHolder.description.setVisibility(View.INVISIBLE);


        return v;
    }

    static class ViewHolder {
        TextView question;
        TextView answeroption, description, useroption;
        int position;
        ImageView ques_img, ans_img, user_img;
    }

}