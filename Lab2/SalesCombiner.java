
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Reducer.*;
import org.apache.log4j.Logger;



public class SalesCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text date, Iterable<IntWritable> frequency, Context context) throws IOException, InterruptedException {
        int count = 0;
        for(IntWritable num : frequency){
            count += num.get();
        }
        context.write(date, new IntWritable(count));
    }
}

