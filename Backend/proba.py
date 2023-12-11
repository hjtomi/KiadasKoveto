import requests

API_KEY1 = "7582ffa485133f201ac58cb1b7ec6a95"
API_KEY2 = "ae305e7f297f1927fc2ac2dd2a8388f7"
URL1 = "https://api.mindee.net/v1/products/mindee/expense_receipts/v5/predict"
URL2 = "https://api.mindee.net/v1/products/mindee/invoices/v4/predict"
API_KEY = "d2692ea9750aa3db29e8489cdd9b97f0"
URL = "https://api.mindee.net/v1/products/mindee/invoices/v4/predict"

def _nyugta_adatfeldolgozas(photo) -> dict:
    """Hasznalja a mindee API-t es a visszakapott adatokbol
    kiszuri a szamunkra fontosakat, ezeket hivjuk nyers_nyugta_adatok-nak"""

    files = {'document': photo}
    headers = {"Authorization": API_KEY}

    response = requests.post(
        url=URL,
        files=files,
        headers=headers,
    )

    nyers_adatok: dict = response.json()

    '''bolti_termek_nevek = [
        sub['description'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolti_termek_arak = [
        sub['total_amount'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolt_nev = nyers_adatok['document']['inference']['prediction']['supplier_name']['value']

    datum = nyers_adatok['document']['inference']['prediction']['date']['value']

    return {
        "bolt_nev": bolt_nev,
        "datum": datum,
        "bolti_termek_nevek": bolti_termek_nevek,
        "bolti_termek_arak": bolti_termek_arak,
    }'''

    return nyers_adatok

def _nyugta_adatfeldolgozas1(photo) -> dict:
    """Hasznalja a mindee API-t es a visszakapott adatokbol
    kiszuri a szamunkra fontosakat, ezeket hivjuk nyers_nyugta_adatok-nak"""

    files = {'document': photo}
    headers = {"Authorization": API_KEY1}

    response = requests.post(
        url=URL1,
        files=files,
        headers=headers,
    )

    nyers_adatok: dict = response.json()

    '''bolti_termek_nevek = [
        sub['description'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolti_termek_arak = [
        sub['total_amount'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolt_nev = nyers_adatok['document']['inference']['prediction']['supplier_name']['value']

    datum = nyers_adatok['document']['inference']['prediction']['date']['value']

    return {
        "bolt_nev": bolt_nev,
        "datum": datum,
        "bolti_termek_nevek": bolti_termek_nevek,
        "bolti_termek_arak": bolti_termek_arak,
    }'''
    return nyers_adatok

def _nyugta_adatfeldolgozas2(photo) -> dict:
    """Hasznalja a mindee API-t es a visszakapott adatokbol
    kiszuri a szamunkra fontosakat, ezeket hivjuk nyers_nyugta_adatok-nak"""

    files = {'document': photo}
    headers = {"Authorization": API_KEY2}
    response = requests.post(
        url=URL2,
        files=files,
        headers=headers,
    )

    nyers_adatok: dict = response.json()

    '''bolti_termek_nevek = [
        sub['description'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolti_termek_arak = [
        sub['total_amount'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
    ]

    bolt_nev = nyers_adatok['document']['inference']['prediction']['supplier_name']['value']

    datum = nyers_adatok['document']['inference']['prediction']['date']['value']

    return {
        "bolt_nev": bolt_nev,
        "datum": datum,
        "bolti_termek_nevek": bolti_termek_nevek,
        "bolti_termek_arak": bolti_termek_arak,
    }'''
    return nyers_adatok

with open("nyugta2.jpg", "rb") as f:
    nyugta = f
    #print(_nyugta_adatfeldolgozas(nyugta))
    #print(_nyugta_adatfeldolgozas1(nyugta))
    print(_nyugta_adatfeldolgozas2(nyugta))



