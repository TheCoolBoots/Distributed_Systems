package Lab3.pt1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.awt.*;
import java.io.IOException;


public class SalesReducer extends Reducer<CompositeKey, Text, Text, Text> {
   

    @Override
    public void reduce(CompositeKey timestamp, Iterable<Text> frequency, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for(Text op : frequency){
            sb.append(op.toString());
        } 
        context.write(timestamp.getDate(), new Text(sb.toString()));
    }
}

