package com.example.wissal1.rbmapplication.Codes.Humidity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wissal1.rbmapplication.Codes.Temperature.TemperaturesAdapter;
import com.example.wissal1.rbmapplication.R;
import com.example.wissal1.rbmapplication.database.Humidity;
import com.example.wissal1.rbmapplication.database.Temperature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by WISSAL1 on 08/04/2018.
 */

 public class HumidityAdapter extends RecyclerView.Adapter<HumidityAdapter.MyViewHolder>{
    private Context context;
    private List<Humidity> humidityList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView humidity;
        public TextView dot;
        public TextView date;
        public TextView num ;

        public MyViewHolder(View view) {
            super(view);
            humidity = view.findViewById(R.id.humidity);
            dot = view.findViewById(R.id.dot);
            date = view.findViewById(R.id.date);
            num=view.findViewById(R.id.num);
        }
    }


    public HumidityAdapter(Context context, List<Humidity> humidityList) {
        this.context = context;
        this.humidityList = humidityList;
    }

    @NonNull
    @Override
    public HumidityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.temperature_list_row, parent, false);

        return new HumidityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Humidity humidity = humidityList.get(position);

        holder.humidity.setText(humidity.getValeurHumidity());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying date
        holder.date.setText(formatDate(humidity.getDate()));

        holder.num.setText(humidity.getNumruche());

    }



    @Override
    public int getItemCount() {
        return humidityList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d HH:mm:ss");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }



}




