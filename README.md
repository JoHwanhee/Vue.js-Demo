# Vue.js-Demo
Vue.js Demo 

#### It has to include:
1. Data binding from data server example
```html
<div id="app">
    <div class="personcard">
      <h1> {{ message }} </h1>
      <ol>
        <li>이름 : {{ name }}</li>
        <li>나이 : {{ age }}</li>
        <li>최근 관심사 : {{ interesting }}</li>
        <li>주소 : {{ address }}</li>
      </ol>
    </div>
  </div>
```

2. Two data server example (python, java)

java example
```java
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
```

python example
```python
class MyHandler(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        self.json_data = DataManager().json_data
        self.send_response(200)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(self.json_data.encode('utf-8'))

        return None
```


#### Sample data
```json
{
    "person": {
        "name": "jhh",
        "address":"82-19, Yumri, Mapo, Seoul, Republic of Korea",
        "age" : 27,
        "interesting" : "Python, HTML5, CSS, JS(ES6), vue.js",
        "message": "Hello world"
    }
}
```

#### Result

![](./images/result.jpg)
