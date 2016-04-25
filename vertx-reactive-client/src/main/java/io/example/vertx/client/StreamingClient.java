package io.example.vertx.client;


import io.example.vertx.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.Pump;

/**
 * @author Tuhin Gupta
 *
 */
public class StreamingClient extends AbstractVerticle {
	
	
	  static String FILE_NAME = null;

	  
	  public static void main(String[] args) {
		  
		  if(null == args[0] || args[0] == ""){
			  System.err.println("Usage: javac io.vertx.example.http.client.StreamingClient <PATH_TO_INPUT_FILE_WITH_FILE_NAME>");
			  System.exit(0);
		  }
		  
		FILE_NAME = args[0];  
	    Runner.runExample(StreamingClient.class);
	  }

	  
	  //{"transaction_date":"2015-12-29","account_number":"201418777876948","transaction_amount":"803.02","last_name":"Willis","id":"405067","first_name":"Billy","email":"bwillis10@spotify.com"}
	 // {"transaction_date":"2015-05-30","account_number":"4905709701528306163","transaction_amount":"224.86","last_name":"Lee","id":"405068","first_name":"Douglas","email":"dleep6@posterous.com"}

	  public void start() {

		  HttpClientRequest request = vertx.createHttpClient(new HttpClientOptions()).put(8998, "localhost", "", resp -> {
		      System.out.println("Response " + resp.statusCode());
		      System.out.println("Response " + resp.statusMessage());
		    });


		    FileSystem fs = vertx.fileSystem();

		    fs.props(FILE_NAME, ares -> {
		      FileProps props = ares.result();
		      System.out.println("props is " + props);
		      long size = props.size();
		      request.headers().set("content-length", String.valueOf(size));
		      fs.open(FILE_NAME, new OpenOptions(), ares2 -> {
		        AsyncFile file = ares2.result();
		        Pump pump = Pump.pump(file, request);
		        file.endHandler(v -> {
		        	request.end();
		        });
		        pump.start();
		      });
		    });

		  
	  }
	 
	}
