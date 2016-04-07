package io.example.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


/**
 * @author Tuhin Gupta
 *
 */
public class KafkaStreamProducer {
	
	Producer<String,String> producer = null;

	public KafkaStreamProducer() {
	
		Properties properties = new Properties();
	    properties.put("metadata.broker.list","localhost:9092");
	    properties.put("serializer.class","kafka.serializer.StringEncoder");
	    ProducerConfig producerConfig = new ProducerConfig(properties);
	    
	    producer = new Producer<String, String>(producerConfig);
	    
	}
	
	public void sendMessages(String topic, String message) throws Exception
	{
		
		KeyedMessage<String, String> kmessage =new KeyedMessage<String, String>(topic, message);
	    
	    
	    producer.send(kmessage);
	    
	}
	
	public void closeProducer(){
		
		producer.close();
	}

}
