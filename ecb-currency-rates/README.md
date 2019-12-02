# Oficialūs valiutų kursai 

Programa parodo oficialius www.lb.lt kasdien skelbiamus valiutų kursus bei leidžia atlikti Eur valiutos konvertavimą į kitą pasirinktą

## Pradžia

Servisas paleidžiamas iš .jar failo
* [ecb-currency-rates.jar](https://we.tl/t-558XW4pndS) 
```
java -jar iban-checker-rest.jar
```

### Back-end
```
Java SE 11 
Spring Boot 2.2.1
Maven 3.6.1
H2
```
### Front-end
```
FreeMarker
Bootstrap
```
## Klasės/Ryšiai
Programoje yra trys POJO klasės - Currency, CurrentRate, HistoryRate.
* Currency -> CurrentRate (@OneToOne)
* Currency -> HistoryRate (@OneToMany)

## Programos veikimas

Paleidus programą, atsidaro (http://localhost:8080/) vartotojo interfeisas, kuriame parodomi paskutiniai Lietuvos banko puslapiai paskelbti valiutų kursai. Puslapyje rodomas kursų generavimo laikas sekundės tikslumu.
```
Exchange Rates of the Euro against Foreign Currencies (2019-12-02 16:21:04)
Currency code 	         Currency name 	            Currency rate 	  Date
  AUD 	               Australian dollar 	         1.6228 	2019-11-29
  BGN 	                 Bulgarian lev 	                 1.9558 	2019-11-29
  BRL 	                 Brazilian real 	         4.6459 	2019-11-29
 ...
```
Paspaudus ant valiutos pavadinimo (pvz., Australian dollar), atsidaro šios valiutos kurso istortijos formą: 
```
Exchange Rates of the Euro against Australian dollar (AUD) history
Currency rate 	Date
```
Vartotojui suteikiama galimybę pasirinkti valiutos kursų istorijos periodą:
```
1m - vieno mėnesio
3m - trijų mėnesių
6m - šešių mėnesių
YTD - šių metų
1y - vienerių metų
All - visa istorija nuo 2014-09-30
```
Kursų istorija pateikiama lentelės pavidalu:
```
Currency rate 	  Date
 1.6228 	2019-11-29
 1.6253 	2019-11-28
 1.6220 	2019-11-27
...
```
Naudojant viršutinį puslapio meniu, vartotojas gali grįžti prie aktuoalių valiutų kursų formos arba pasirinkti valiutų skaičiuoklę. 
Naudojant skaičiuoklę, vartotojas pasirenka valiutos, kurios kursą paskelbė Lietuvos bankas, pavadinimą, įveda sumą eurais (nemažiau 1Eur), ir, paspaudus mygtuką "Convert", vartotojui parodoma suma pagal pasirinktą valiutą, valiutos kursas bei kurso paskelbimo datą.


### Papildoma informacija

* aktualūs valiutų kursai automatiškai atnaujinami kasdien 19:00:00 pagal Lietuvos laiką.

## Testai

Programoje yra paprasti integraciniai, kurie testuoja puslapių veikimą, bei moduliniai, kurie testuoja CurrencyService valiutų skaičiuoklės metodą, testai


