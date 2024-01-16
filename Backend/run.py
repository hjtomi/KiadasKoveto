from waitress import serve
from server import app

serve(app, host="172.20.1.107", port=52349)
