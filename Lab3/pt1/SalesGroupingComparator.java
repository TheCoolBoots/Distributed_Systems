package Lab3.pt1;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SalesGroupingComparator extends WritableComparator {

    protected SalesGroupingComparator(){
        super (CompositeKey.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2){
        CompositeKey s1 = (CompositeKey) wc1;
        CompositeKey s2 = (CompositeKey) wc2;

        return s1.getDate().compareTo(s2.getDate());
     }
}
