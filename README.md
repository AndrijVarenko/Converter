<!--
# Converter
-->
<!DOCTYPE html>
<html>
<head>
<title>Converter</title>
<link rel="stylesheet" href="bluestyle.css">
</head>
<body>

<h1>Currency converter</h1>
<!--
<p>Write text</p>
<p>Write text</p>
-->

</body>
</html>

### #_Usage_:
1. Compile code with `javac Main.java`
2. Run with `java Main.class <amount>, <currency>, <fee>`

### #_Task_:
Write a program for exchanging of USD to EUR and EUR to USD. The program shall include 2 classes: Converter and ConverterWrapper.
1.  Class Converter shall contain methods for conversion USD to EUR and EUR to USD. The arguments of methods are an amount for the conversion and an exchange fee. The rate could be received by calling of method of class/interface of RateProvider.
2.  Class ConverterWrapper shall contain a method “convert”. The arguments of method are an amount for the conversion, convertible currency symbol (USD or EUR) and an exchange fee. This method shall call required method from class Converter and shall return result in the following format: ".. USD = ,, EUR" or ".. EUR = ,, USD" where .. is input amount; ,, is conversion result.
3.  There are no requirements for RateProvider implementation.
4.  The program shall be tested by unit tests.
