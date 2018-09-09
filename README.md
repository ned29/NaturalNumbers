# Natural Number Interpretation

***About***
This application are checking the possible ambiguities in number spelling in Greek phone numbers.<br/>
The Greek phone numbers have 10 or 14 digits. If they have 10 digits, they must start with ‘2’ or ‘69’.<br/>
If they have 14 digits, they must start with ‘00302’ or ‘003069’.<br/>
For example if the input sequence contains ‘... 20 5...’ the result number may either be ‘...205...’ or ‘...25...’.<br/>
Also if the sequence contains ‘...75...’, the result number may be: ‘...705...’ or ‘...75...’<br/>
The application identify these ambiguities, and return any possible number interpretations.<br/>

***How to execute***

Clone the project and make ```clean install```<br/>
For starting .jar file use "java -jar NaturalNumbers.jar" command.

***Important***
Allowed only number and sequence should be up to a three digit number.<br/>

***Program execution example***
```
IMPORTANT: Allowed only number and sequence should be up to a three digit number
Enter numbers:
2 10 6 9 30 6 6 4
2106930664 - phone number: VALID
210693664 - phone number: INVALID
Enter numbers:
2 10 69 30 6 6 4
2106930664 - phone number: VALID
2106093664 - phone number: VALID
210693664 - phone number: INVALID
21060930664 - phone number: INVALID
Enter numbers:
0 0 30 69 700 24 1 3 50 2
003069700241352 - phone number: INVALID
00306097002413502 - phone number: INVALID
0030697002413502 - phone number: INVALID
00306972413502 - phone number: VALID
0030609700241352 - phone number: INVALID
00306097241352 - phone number: INVALID
0030697241352 - phone number: INVALID
003060972413502 - phone number: INVALID
Enter numbers:
2 10 9999 456
[main] WARN com.Application - ERROR: Allowed only number and sequence should be up to a three digit number
```