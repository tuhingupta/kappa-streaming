package io.vertx.example.http.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestClient {
	
	public static void main(String[] args) throws Exception
	{
		

		 Socket skt = new Socket("localhost", 8998);
         BufferedReader in = new BufferedReader(new
            InputStreamReader(skt.getInputStream()));
         System.out.print("Received string: '");

         while (!in.ready()) {}
         System.out.println(in.readLine()); // Read one line and output it

         System.out.print("'\n");
         in.close();
	
	
	
	
	}

}
