# Nyugtas kiadas felvetel

import requests

API_KEY = "d2692ea9750aa3db29e8489cdd9b97f0"


class NyugtasKiadas:
    @staticmethod
    def _nyugta_olvasas() -> dict:
        with open("nyugta.jpg", 'rb') as file:
            nyugta = {'document': file}

            response = requests.post(
                url="https://api.mindee.net/v1/products/mindee/expense_receipts/v5/predict",
                files=nyugta,
                headers={"Authorization": API_KEY},
            )

        nyers_adatok: dict = response.json()

        termekek = nyers_adatok['document']['inference']['prediction']['line_items']
        bolt = None
        datum = None

        return {'termekek': termekek, 'bolt': bolt, 'datum': datum}


if __name__ == '__main__':
    # Lista Dictionarikkel
    bolti_termekek = NyugtasKiadas._nyugta_olvasas()['termekek']
    print(bolti_termekek)

    sajat_termek_nevek = []
    sajat_termek_arak = []
    for termek in bolti_termekek:
        print(termek['description'], termek['total_amount'])
        sajat_termek_nevek.append(input("nev: "))
        sajat_termek_arak.append(input("ar: "))

    print(sajat_termek_nevek)
    print(sajat_termek_arak)
    # ['dormi', 'csirkemell', 'csirkemell', 'zabfalat', 'mazsolas milka 549', 'pantastico', 'szatyor', 'baba', 'bacon',
    #  'bacon', 'sertes mix', 'kacsazsir', 'chili', 'szegfuszeg', 'kerek', 'szalag', 'szarny', 'prof', 'morando',
    #  'lapsajt']
    #  '7000', '480', '960', '480', '660']
    # ['298', '1220', '1426', '529', '549', '978', '299', '169', '1900', '1000', '1385', '1200', '500', '250', '2340',


