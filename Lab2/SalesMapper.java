
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.log4j.Logger;


public class SalesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static Logger THE_LOGGER = Logger.getLogger(SalesAnalyzerDriver.class);


    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().trim().split(", ");
        String date = tokens[1];
        context.write(new Text(date), new IntWritable(1));
    }
}


