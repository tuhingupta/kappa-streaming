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
