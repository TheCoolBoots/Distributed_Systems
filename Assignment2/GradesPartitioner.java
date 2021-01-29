

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class GradesPartitioner extends Partitioner<Student, Text> {
    @Override
    public int getPartition(Student s, Text course, int n){
        int hash = s.getName().hashCode();
        int id = s.getID().hashCode() * 37;

        return Math.abs((hash + id) % n);
    }
}