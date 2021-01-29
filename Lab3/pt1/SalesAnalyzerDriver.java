package Lab3.pt1;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;


public class SalesAnalyzerDriver extends Configured implements Tool {

    private static final Logger THE_LOGGER = Logger.getLogger(SalesAnalyzerDriver.class);

    // find all sales for a given day
    // output in ascending order

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance();// creates new Hadoop job
        job.setJarByClass(SalesAnalyzerDriver.class);
        job.setJobName("SalesAnalyzer"); //same as java class name 
        job.setOutputKeyClass(Text.class); //output key class for reduce function
        job.setOutputValueClass(IntWritable.class); //output value class for reduce function
        job.setMapOutputKeyClass(CompositeKey.class); //output key class for map function
        job.setMapOutputValueClass(IntWritable.class); //output value class for map function
        job.setMapperClass(SalesMapper.class);//sets the mapper
        job.setReducerClass(SalesReducer.class);//sets the reducer
        job.setPartitionerClass(SalesPartitioner.class);
        job.setGroupingComparatorClass(SalesGroupingComparator.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean status = job.waitForCompletion(true); //runs the job, returns true if executed successfully 
        THE_LOGGER.info("run(): status=" + status);
        return status ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("usage: <input> <output>");
        }

        THE_LOGGER.info("inputDir = " + args[0]);
        THE_LOGGER.info("outputDir = " + args[1]);
        int returnStatus = ToolRunner.run(new SalesAnalyzerDriver(), args);
        THE_LOGGER.info("returnStatus=" + returnStatus);
        System.exit(returnStatus);
    }
}

