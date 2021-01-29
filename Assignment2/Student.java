

import java.io.*;
import org.apache.hadoop.io.*;

public class Student implements WritableComparable<Student>{

    private final Text name = new Text();
    private final IntWritable id = new IntWritable();
    private final Text course = new Text();

    public Student(String name, int id, String course){
        this.name.set(name);
        this.id.set(id);
        this.course.set(course);
    }

    // "Always have an empty constructor"
    public Student(){

    }
    
    public void write(DataOutput out) throws IOException{
        name.write(out);
        id.write(out);
        course.write(out);
    }
    
    public void readFields(DataInput in) throws IOException{
        this.name.readFields(in);
        this.id.readFields(in);
        this.course.readFields(in);
    }

    public int compareTo(Student other){

        if(name.compareTo(other.getName()) != 0){
            return name.compareTo(other.getName());
        }
        else if (id.compareTo(other.getID()) != 0){
            return id.compareTo(other.getID());
        }
        return course.compareTo(other.getCourse());

        
    }

    public Text getName(){
        return this.name;
    }

    public IntWritable getID(){
        return this.id;
    }

    public Text getCourse(){
        return this.course;
    }
}
