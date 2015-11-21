package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// This should maybe extend htttServer
public class ServerRoot {

	private HttpServer server;

	public ServerRoot() {

		try {
			// Localhost doesn't require root
			server = HttpServer.create(new InetSocketAddress("localhost", 10000), 0);
			server.createContext("/server", new Handler());
			server.createContext("/", new Handler());
			server.setExecutor(Executors.newCachedThreadPool());
		} catch (IOException e) {
			e.printStackTrace();
		}

		server.start();

	}

	private class Handler implements HttpHandler {

		@Override
		public void handle(HttpExchange e) throws IOException {
			System.out.println(
					e.getRemoteAddress() +
					" requesting " +
					e.getRequestMethod());

			if( e.getRequestMethod().equalsIgnoreCase("GET")) {
				Headers respHeaders = e.getResponseHeaders();
				respHeaders.add("Content-Type", "text/plain");
				e.sendResponseHeaders(200, 0);

				OutputStream response = e.getResponseBody();

				File file = new File("server/error-message");
				FileInputStream fileStream = new FileInputStream(file);

				int content;
				while( (content = fileStream.read()) != -1 ) {
					response.write(content);
				}
				fileStream.close();

				response.close();

			}
			else if( e.getRequestMethod().equalsIgnoreCase("POST") ) {

				InputStream req = e.getRequestBody();
				String dataString = "";
				int content;
				while( (content = req.read()) != -1 ) {
					dataString = dataString.concat(Character.toString((char)content));
				}
				String testString = new String("test");


				Headers respHead = e.getResponseHeaders();
				respHead.set("content-type", "text/plain");
				e.sendResponseHeaders(200, 0);

				OutputStream resp = e.getResponseBody();


				if( Objects.equals(dataString, testString) ) {
					resp.write("test message recieved".getBytes());
				} else {
					resp.write("unidentified request".getBytes());
				}

				req.close();
				resp.close();


			}
			e.close();
		}
	}
}
