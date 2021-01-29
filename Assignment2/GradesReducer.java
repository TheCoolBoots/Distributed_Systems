

import java.util.ArrayList;
import java.lang.StringBuilder;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class GradesReducer extends Reducer<Student, Text, Text, Text> {
    
    @Override
    public void reduce(Student student, Iterable<Text> courses, Context context) throws IOException, InterruptedException {   
        ArrayList<String> courseList = new ArrayList<String>();

        for(Text course : courses){
            courseList.add(course.toString());
        }

        String outKey = String.format("%s, %d", student.getName().toString(), student.getID().get());

        StringBuilder sb = new StringBuilder();
        for(String course: courseList){
            sb.append(String.format("%s, ", course));
        }
        String outVal = sb.toString().trim();
        outVal = outVal.substring(0, outVal.length() - 1);

        context.write(new Text(outKey), new Text(outVal));
    }
}

