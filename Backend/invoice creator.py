import requests
import json

url = "https://api.mindee.net/v1/products/mindee/invoices/v4/predict"

#Create a json
with open("D:/Munka/Vod/Info/Python2/muhely/IMG2.jpg", "rb") as myfile:
    files = {"document": myfile}
    headers = {"Authorization": "db7e7f5ffcf2b8810e115fda0b1cd1cb"}
    response = requests.post(url, files=files, headers=headers)
    #print(response.text)
    with open("response3.json", "w") as f:
        json.dump(json.loads(response.text), f)

#look for something in the text
with open("response3.json", "r") as i:
    data = json.load(i)

for x in data['document']['inference']['pages'][0]['prediction']['line_items']:
    print(x)
