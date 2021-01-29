package Lab3.pt1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;


public class SalesMapper extends Mapper<LongWritable, Text, CompositeKey, IntWritable> {
    private static Logger THE_LOGGER = Logger.getLogger(SalesAnalyzerDriver.class);

    // 0, 2019-08-15, 13:30:57, 9, 15

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().trim().split(", ");
        String date = tokens[1];
        String purchase = String.format("(%s, %s)", tokens[2], tokens[0]);
        context.write(new CompositeKey(tokens[1], tokens[2]), new IntWritable(1));
    }
}


