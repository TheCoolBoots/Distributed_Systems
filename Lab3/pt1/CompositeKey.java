package Lab3.pt1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeKey implements WritableComparable<CompositeKey>{

    private final Text date = new Text();
    private final Text time = new Text();

    public CompositeKey(String date, String time){
        this.date.set(date);
        this.time.set(time);
    }

    // "Always have an empty constructor"
    public CompositeKey(){

    }
    
    public void write(DataOutput out) throws IOException{
        date.write(out);
        time.write(out);
    }
    
    public void readFields(DataInput in) throws IOException{
        this.date.readFields(in);
        this.time.readFields(in);
    }

    public Text getDate(){
        return this.date;
    }

    public Text getTime(){
        return this.time;
    }

    @Override
    public int compareTo(CompositeKey o) {
        if (date.compareTo((o.getDate())) != 0){
            return date.compareTo((o.getDate()));
        }
        return time.compareTo(o.getTime());
    }
}
