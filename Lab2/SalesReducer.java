package Lab2;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;


public class SalesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
   

    @Override
    public void reduce(Text date, Iterable<IntWritable> frequency, Context context) throws IOException, InterruptedException {   
        int count = 0;
        for(IntWritable num : frequency){
            count += num.get();
        } 
        context.write(date, new IntWritable(count));
    }
}

