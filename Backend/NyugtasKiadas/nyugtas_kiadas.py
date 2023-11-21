# Nyugtas kiadas felvetel

import io
import requests

API_KEY = "d2692ea9750aa3db29e8489cdd9b97f0"


def nyugta_adatfeldolgozas(photo) -> dict:
    nyugta = {'document': photo}

    response = requests.post(
        url="https://api.mindee.net/v1/products/mindee/expense_receipts/v5/predict",
        files=nyugta,
        headers={"Authorization": API_KEY},
    )

    nyers_adatok: dict = response.json()

    bolti_termek_nevek = [
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
    }


def nyugtas_kiadas_felvetel(nyugta_adatok: dict) -> dict:
    if uj_bolt_nev := input(f"Helytelen bolt nev eseten ird be a bolt nevet, kulonben hagy uresen ({nyugta_adatok['bolt_nev']})"): nyugta_adatok['bolt_nev'] = uj_bolt_nev
    if uj_datum := input(f"Helytelen datum eseten ird be a datumot, kulonben hagy uresen ({nyugta_adatok['datum']})"): nyugta_adatok['datum'] = uj_datum


    sajat_termek_nevek = []
    sajat_termek_arak = []
    for (bolti_termek_nev, bolti_termek_ar) in zip(nyugta_adatok['bolti_termek_nevek'], nyugta_adatok['bolti_termek_arak']):
        sajat_termek_nev = input(f"{bolti_termek_nev}: ")
        sajat_termek_ar = input(f"{bolti_termek_ar}: ")

        if sajat_termek_nev:
            sajat_termek_nevek.append(sajat_termek_nev)
        else:
            sajat_termek_nevek.append(bolti_termek_nev)

        if sajat_termek_ar:
            sajat_termek_arak.append(int(sajat_termek_ar))
        else:
            sajat_termek_arak.append(int(bolti_termek_ar))


    return {
        "bolt_nev": nyugta_adatok['bolt_nev'],
        "datum": nyugta_adatok['datum'],
        "bolti_termek_nevek": nyugta_adatok['bolti_termek_nevek'],
        "sajat_termek_nevek": sajat_termek_nevek,
        "sajat_termek_arak": sajat_termek_arak,
    }


if __name__ == '__main__':
    # print(NyugtasKiadas.felvetel())
    # file type: io.BufferReader
    ...
