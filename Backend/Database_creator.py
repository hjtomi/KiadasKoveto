import sqlite3

# A Connection típusú objektum létrehozza a kapcsolatot az adatbázissal,
con = sqlite3.connect("main.db")

# A cursor objektum metódusaival valóstíhatjuk meg az SQL utasításokat
cur = con.cursor()

cur.execute("CREATE TABLE felhasznalo (f_nev, email, jelszo, ossz_egyen, bank_egyen, keszpenz_egyen, sajat_kateg)")
cur.execute("CREATE TABLE nyugta (f_nev, datum, id, egyen_fiok, kateg, bolti_nev, real_nev, ar)")

# A változások mentéséhez szükséges a commit() metódus hívása!
con.commit()

con.close()
