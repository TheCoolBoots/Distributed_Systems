

import org.apache.log4j.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.input.*;

public class GradesDriver extends Configured implements Tool {

    private static final Logger THE_LOGGER = Logger.getLogger(GradesDriver.class);

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance();// creates new Hadoop job
        job.setJarByClass(GradesDriver.class);
        job.setJobName("GradesDriver"); //same as java class name 
        job.setOutputKeyClass(Text.class); //output key class for reduce function
        job.setOutputValueClass(Text.class); //output value class for reduce function
        job.setMapOutputKeyClass(Student.class); //output key class for map function
        job.setMapOutputValueClass(Text.class); //output value class for map function
        job.setMapperClass(GradesMapper.class);//sets the mapper
        job.setReducerClass(GradesReducer.class);//sets the reducer
        job.setGroupingComparatorClass(GradesGroupingComparator.class);
        // job.setSortComparatorClass(GradesSortComparator.class);
        job.setPartitionerClass(GradesPartitioner.class);
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
        int returnStatus = ToolRunner.run(new GradesDriver(), args);
        THE_LOGGER.info("returnStatus=" + returnStatus);
        System.exit(returnStatus);
    }
}

