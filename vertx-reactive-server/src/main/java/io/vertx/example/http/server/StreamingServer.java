package io.vertx.example.http.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.example.util.Runner;


public class StreamingServer extends AbstractVerticle {
	
	// Convenience method so you can run it in your IDE
	  public static void main(String[] args) {
	    Runner.runExample(StreamingServer.class);
	  }

	  
	  static String pattern1 = "{";
	  static String pattern2 = "}";
		
	  static Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));

	  /* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start()
	 */
	@Override
	  public void start() throws Exception {
		
		System.out.println("Server started at localhost:8998 ...");
		
		
		
	    vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
	    	
	    	long byteswritten = 0;
	    	
			
			List<String> results = new ArrayList<String>();
			String extraBytes = null;
			
	    	int start = -1;
	    	int end = -1;

			@Override
			public void handle(HttpServerRequest request) {

				
				
				request.handler(new Handler<Buffer>(){


					@Override
					public void handle(Buffer buffer) 
					{
						
						byteswritten += buffer.length();
						String inputString = extraBytes == null ? buffer.toString(): extraBytes + buffer.toString();
						//using string patterns
						Matcher m = p.matcher(inputString);
						
						
						//set request content-length header
						request.headers().set("content-length", String.valueOf(byteswritten));
						
						while (m.find()) {
							if(start < 0){
								if(m.start() > 0)
									start = m.start();
							}

							results.add(m.group());
							end = m.start() + m.group().length(); 

						}//while
						
						//System.out.println("First wrong string - "+str.substring(0, start));
						//System.out.println("Last wrong string - "+end+" "+str.substring(end, str.length()));
						if(end >= 0 && end < inputString.length()){
							extraBytes = inputString.substring(end, inputString.length());
						}
						
						
						
						//---------------------------------------------------------------------------
						/* code for parsing stream and finding incomplete strings using jackson events. NOT using it right now.
						int counterClose;
						int counterStart;
						
						if(buffer.getString(0, 1) == "{"){
							
							counterStart = 0;
							
							int indOf = buffer.toString().indexOf("}");
							if(indOf > 0){
								
							}
							
						}else{
							
							int indOf = buffer.toString().indexOf("{");
							if(indOf > 0){
								
								counterStart = indOf;
								
								//extra = ;
								
							}
							
						}
						
						
						/*---------------------jackson parser
						try{
							
								parser = jsonfactory.createJsonParser(buffer.toString());
								JsonToken jt = null;
								JsonToken intjt = null;
								
								while (parser.nextToken() != JsonToken.END_ARRAY) 
								{
									
									jt = parser.getCurrentToken();
									
									if(jt == JsonToken.START_OBJECT){
										
										while(parser.nextToken() != JsonToken.END_OBJECT){
											
											intjt = parser.getCurrentToken();
											if(intjt == JsonToken.FIELD_NAME){
												System.out.println(":"+intjt.asString());
											}
											
										}
										
									}
									
								//	System.out.println("==> "+parser.nextToken());
								//	System.out.println(parser.getCurrentName());

								}
								
								
						}catch(Exception ex){
							ex.printStackTrace();
						}
						*///-------ends---jackson parser
						//---------------------------------------------------------------------------
						
						
					}
					
				
				});//request.handler
				
				request.endHandler(new Handler<Void>() {

					@Override
					public void handle(Void event) {
						
						System.out.println("reaching here..");
						
						request.response().setStatusCode(202).setStatusMessage("bytes written" + byteswritten);
						request.response().end();
						
						for (Iterator iterator = results.iterator(); iterator.hasNext(); ) {
							System.out.println( iterator.next());
							
						}
						
						//reset
						extraBytes = null;
						results = new ArrayList<String>();
						byteswritten = 0;
						
					}//handle
				
				});
				
				
			}
  	
	    	
	    }).listen(8998);
	  }

}
