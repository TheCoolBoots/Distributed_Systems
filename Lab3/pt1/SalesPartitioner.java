package Lab3.pt1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class SalesPartitioner extends Partitioner<CompositeKey, Text> {
    @Override
    public int getPartition(CompositeKey c, Text sale, int n){

        return Math.abs(c.getDate().hashCode() % n);
    }
}