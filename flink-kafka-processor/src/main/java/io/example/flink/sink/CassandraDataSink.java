package io.example.flink.sink;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Tuhin Gupta
 *
 */
public class CassandraDataSink implements SinkFunction<Tuple2<String, Float>> {
	
	
	private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

	
	
	  /**
	   * Function for standard sink behaviour. This function is called for every record.
	   *
	   * @param tuple The input tuple entity that shall be stored in cassandra table
	   */
	@Override
	public void invoke(final Tuple2<String, Float> tuple) throws Exception 
	{
	
	    UserActivityEventsPersister userActivityEventsPersister = applicationContext.getBean(UserActivityEventsPersister.class);
	    userActivityEventsPersister.persist(jsonEntity);
	  
	}	

}
