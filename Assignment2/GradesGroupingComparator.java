package Assignment2;

import org.apache.hadoop.io.*;

public class GradesGroupingComparator extends WritableComparator {

    protected GradesGroupingComparator(){
        super (Student.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2){
        Student s1 = (Student) wc1;
        Student s2 = (Student) wc2;

        return s1.getID().compareTo(s2.getID());
     }
}
