

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.log4j.Logger;

public class IntMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
    private static Logger THE_LOGGER = Logger.getLogger(IntDriver.class);


    /*

        (1, "2 3 4")
        (2, "3 4 3")

        ->

        (1, 1)
        (2, 1)
        (2, 1)

    */

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().trim().split(" ");
        int count = 0;
        for (String num:tokens){
            if(Integer.parseInt(num) % 3 == 0){
                context.write(key, new LongWritable(1));
            }
        }
    }
}


