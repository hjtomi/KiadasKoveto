import requests
import json

url = "https://api.mindee.net/v1/products/mindee/expense_receipts/v5/predict"

#Create a json
with open("nyugta1.jpg", "rb") as myfile:
    files = {"document": myfile}
    headers = {"Authorization": "db7e7f5ffcf2b8810e115fda0b1cd1cb"}
    response = requests.post(url, files=files, headers=headers)
    print(response.text)
    with open("response1.json", "w") as f:
        json.dump(json.loads(response.text), f)

#look for something in the text
with open("response1.json", "r") as i:
    data = json.load(i)

#kulcsok az egyik fő kulcsban
print(data['document'])

#lényeges kulcsok
print(data['document']['inference']['pages'][0]['prediction'].keys())

#aruk
print(data['document']['inference']['pages'][0]['prediction']['line_items'])