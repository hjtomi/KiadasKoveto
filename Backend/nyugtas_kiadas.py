# Nyugtas kiadas felvetel

import requests
from Adatbazis import Felhasznalo, Tranzakcio, adatok_hozzadasa

API_KEY = "d2692ea9750aa3db29e8489cdd9b97f0"

debug_nyers_nyugta_adatok = {
        'bolt_nev': "Spar",
        'datum': None,
        'bolti_termek_nevek': ['Fanta 0,5L', 'B00 db DORMI EPRES 30G 149 Ft/db', 'A00 CSIRKEMELLF. SZCS',
                               'A00 CSIRKEMELLE SZCS', 'CO0 GYE.ZABFAL.CS02244G', 'CO0 MILKA.MAZS.MOGY 100G',
                               'BO0 db PANTASTICO RETRO 489 Ft/db PUF', 'CO0 BEVASARLO TASKA',
                               'COO SZT.KIRALYI BABA 1L', 'COO AN BACON 2X200G', 'CO0 KOCKAZOTT BACON 500G',
                               'AOO AN SERTES-MARHA MIX', 'CO0 PF. KACSAZSIR 200G', 'CO0 THYMOS CHILT OROLT25',
                               'CO0 HR SZEGFUSZEG.10 G.', 'COO KEREKANYA KERESZTK', 'COO HOBBY-SZALAG, EZUST.',
                               'COO MC PROF400G SZARNY', 'db 479 Ft/db', 'COO MORANDO PROFESSIONAL',
                               'BO0 CHED.LAPKA SAJT 150G'],
        'bolti_termek_arak': [399.0, 298.0, 220.0, 436.0, 529.0, 549.0, 978.0, 299.0, 169.0, 899.0, 999.0, 385.0, 199.0,
                              499.0, 249.0, 339.0, 699.0, 479.0, 958.0, 479.0, 659.0]
    }


class NyugtasKiadas:
    def __init__(self, photo=None, debug=False):
        if debug:
            kesz_adatok = self.nyugtas_kiadas_felvetel(debug_nyers_nyugta_adatok)

            uj_elemek = []
            for (sajat_termek_ar, bolti_termek_nev, sajat_termek_nev) in zip(kesz_adatok['sajat_termek_arak'],
                                                                             kesz_adatok['bolti_termek_nevek'],
                                                                             kesz_adatok['sajat_termek_nevek']):
                uj_elemek.append(
                    Tranzakcio(
                        felhasznalonev="Proba",
                        tranzakcio_id=0,
                        tipus='nyugtas_kiadas',
                        ertek=sajat_termek_ar,
                        datum=kesz_adatok['datum'],
                        bolti_aru_nev=bolti_termek_nev,
                        sajat_aru_nev=sajat_termek_nev,
                        bolt=kesz_adatok['bolt_nev']
                    )
                )

            adatok_hozzadasa(uj_elemek)

        else:

            nyers_nyugta_adatok = self.nyugta_adatfeldolgozas(photo)
            kesz_adatok = self.nyugtas_kiadas_felvetel(nyers_nyugta_adatok)

            uj_elemek = []
            for (sajat_termek_ar, bolti_termek_nev, sajat_termek_nev) in zip(kesz_adatok['sajat_termek_arak'],
                                                                             kesz_adatok['bolti_termek_nevek'],
                                                                             kesz_adatok['sajat_termek_nevek']):
                uj_elemek.append(
                    Tranzakcio(
                        felhasznalonev="Proba",
                        tranzakcio_id=0,
                        tipus='nyugtas_kiadas',
                        ertek=sajat_termek_ar,
                        datum=kesz_adatok['datum'],
                        bolti_aru_nev=bolti_termek_nev,
                        sajat_aru_nev=sajat_termek_nev,
                        bolt=kesz_adatok['bolt_nev']
                    )
                )

            adatok_hozzadasa(uj_elemek)

    @staticmethod
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

    @staticmethod
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
