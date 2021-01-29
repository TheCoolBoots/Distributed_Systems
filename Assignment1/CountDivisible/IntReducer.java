package Assignment1.CountDivisible;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;


public class IntReducer extends Reducer<LongWritable, LongWritable, NullWritable, LongWritable> {
   
    /*

        (1, 1)
        (2, 2)
    
        ->

        (Null, 3)
    */

    @Override
    public void reduce(LongWritable lineNum, Iterable<LongWritable> lines, Context context) throws IOException, InterruptedException {   
        int count = 0;
        for(LongWritable num:lines){
            count += num.get();
        } 
        context.write(NullWritable.get(), new LongWritable(count));
    }
}

