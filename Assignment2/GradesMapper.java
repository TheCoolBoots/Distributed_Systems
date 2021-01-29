

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.log4j.Logger;

public class GradesMapper extends Mapper<LongWritable, Text, Student, Text> {

    // (1, "John Back, 23, B, CSC366")
    // composite key = (name, course)

    private static Logger THE_LOGGER = Logger.getLogger(GradesDriver.class);

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String contents = value.toString();
        String[] tokens = contents.split(", ");

        if(tokens.length == 4){
            String name = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            String course = String.format("(%s, %s)", tokens[2], tokens[3]);

            Student newKey = new Student(name, id, course);
            context.write(newKey, new Text(course));
        }
    }
}


