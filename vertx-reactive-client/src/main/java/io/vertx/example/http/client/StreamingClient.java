package io.vertx.example.http.client;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.Pump;
import io.vertx.example.util.Runner;

public class StreamingClient extends AbstractVerticle {
	
	
	  final static String FILE_NAME = "/Users/tgupt24/Documents/servers/vertx/upload.json";

	  // Convenience method so you can run it in your IDE
	  public static void main(String[] args) {
	    Runner.runExample(StreamingClient.class);
	  }

	  public void start() {

		  HttpClientRequest request = vertx.createHttpClient(new HttpClientOptions()).put(8998, "localhost", "", resp -> {
		      System.out.println("Response " + resp.statusCode());
		      System.out.println("Response " + resp.statusMessage());
		    });

		 // String filename = "/Users/tgupt24/Documents/servers/vertx/MOCK_DATA.json";
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
