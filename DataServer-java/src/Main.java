import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Main {

    public static void main(String[] args) throws Exception {
    	HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new Root());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class Root implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	setHeaders(t);
        	
        	// need to modify
            log("TRACE", "GET");
            
            DataManager dm = DataManager.getInstance();
            String response = dm.getJsonData();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
        private void setHeaders(HttpExchange t) {
        	t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            t.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
		}
        
        private void log(String logLevel, String message) {
        	long time = System.currentTimeMillis(); 

    		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    		String date = dayTime.format(new Date(time));
    		
    		System.out.printf("[%s] [%s] : %s\n", logLevel, date, message);
        }
    }
}