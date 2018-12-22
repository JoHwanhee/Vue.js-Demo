import http.server
from data_manager import DataManager


class MyHandler(http.server.BaseHTTPRequestHandler):
    def do_GET(self):
        self.json_data = DataManager().json_data
        self.send_response(200)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(self.json_data.encode('utf-8'))

        return None




# 10.41.50.206
server_ip='localhost'
port = 8080
s = http.server.HTTPServer((server_ip, port), MyHandler)
print('Server started')
s.serve_forever()
