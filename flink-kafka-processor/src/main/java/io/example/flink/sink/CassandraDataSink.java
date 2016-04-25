package io.example.flink.sink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.example.cassandra.configuration.ApplicationConfiguration;
import io.example.cassandra.service.TransactionSnapshotService;

/**
 * @author Tuhin Gupta
 *
 */
public class CassandraDataSink implements SinkFunction<Tuple2<String, Float>> {
	
	
	private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

	
	
	  /**
	   * Function for standard sink behavior. This function is called for every record.
	   *
	   * @param tuple The input tuple entity that shall be stored in cassandra table
	   */
	@Override
	public void invoke(final Tuple2<String, Float> tuple) throws Exception 
	{

		TransactionSnapshotService tsService = applicationContext.getBean(TransactionSnapshotService.class);
		tsService.persist(tuple);
	  
	}	

}
