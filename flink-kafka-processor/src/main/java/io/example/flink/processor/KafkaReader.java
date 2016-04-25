package io.example.flink.processor;

import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.example.flink.model.Transaction;
import io.example.flink.sink.CassandraDataSink;




/**
 * @author Tuhin Gupta
 *
 */
public class KafkaReader {
	
	public static void main(String[] args) throws Exception{
		
		// create execution environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		// only required for Kafka 0.8
		properties.setProperty("zookeeper.connect", "localhost:2181");
		properties.setProperty("group.id", "consumer3");
		
		
		
		DataStream<Tuple2<String, Float>> messageStream = env.
			   addSource(new FlinkKafkaConsumer08<>("appA", new SimpleStringSchema(), properties))
			   .flatMap(new StreamToTuple())
			   .keyBy(0)
               //.timeWindow(Time.seconds(5))
               .sum(1)
               ;
		
	//	messageStream.print();
	   
		messageStream.rebalance().addSink(new CassandraDataSink());
		
		System.out.println(env.getExecutionPlan());
	   
	   env.execute();

	}
	
	
	public static class StreamToTuple implements FlatMapFunction<String, Tuple2<String, Float>> {
        
		ObjectMapper mapper = new ObjectMapper();
		
		@Override
        public void flatMap(String sentence, Collector<Tuple2<String, Float>> out) throws Exception {
            
        	//System.out.println(sentence);
        	try{
        		Transaction  obj = mapper.readValue(sentence, Transaction.class);

        		out.collect(new Tuple2<String, Float>(obj.getAccountNumber(), obj.getAmount()));
        	}catch(Exception ex){
        		System.err.println(ex.getMessage()+" - Error processing record - "+sentence);
        	}

        }
    }

}
