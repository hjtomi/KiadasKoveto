from waitress import serve
from server import app

# BDani futtatasa
serve(app, host="192.168.0.112", port=52349)
# hjtomi futtatasa
# serve(app, host="0.0.0.0", port=8080)
