package Assignment1.MaxTemp;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.log4j.Logger;

public class MaxMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static Logger THE_LOGGER = Logger.getLogger(MaxDriver.class);


    /*

        (10/20/30, "2 3 4")
        (10/21/30, "3 4 3")

        ->

        (10/20/30, 2)
        (10/20/30, 3)
        (10/20/30, 4)
        ...

    */

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().trim().split(" ");
        int count = 0;
        String newKey = tokens[0];
        for(int i = 1; i < tokens.length;i++){
            context.write(new Text(newKey), new IntWritable(Integer.parseInt(tokens[i])));
        }
    }
}


