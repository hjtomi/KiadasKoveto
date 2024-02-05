from waitress import serve
from server import app


serve(app, host="192.168.0.113", port=52349)
