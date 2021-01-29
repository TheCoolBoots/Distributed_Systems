package Assignment1.MaxTemp;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;


public class MaxReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
   
    /*

        (1, 1)
        (2, 1)
        (2, 1)
    
        ->

        (1, 1)
        (2, 2)

    */

    @Override
    public void reduce(Text date, Iterable<IntWritable> temperatures, Context context) throws IOException, InterruptedException {   
        int max = Integer.MIN_VALUE;
        for(IntWritable temp:temperatures){
            if(temp.get() > max)
                max = temp.get();
        } 
        context.write(date, new IntWritable(max));
    }
}

