from waitress import serve
from server import app

serve(app, host="192.168.0.108", port=52349)
