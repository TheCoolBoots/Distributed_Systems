package Assignment1.CountDivisible;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;


public class IntCombiner extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
   
    /*

        (1, 1)
        (2, 1)
        (2, 1)
    
        ->

        (1, 1)
        (2, 2)

    */

    @Override
    public void reduce(LongWritable lineNum, Iterable<LongWritable> lines, Context context) throws IOException, InterruptedException {   
        int count = 0;
        for(LongWritable num:lines){
            count += num.get();
        } 
        context.write(lineNum, new LongWritable(count));
    }
}

