The Main.java file inside the src folder is the main program of your project. It reads the JSON files, converts all the encoded numbers from their respective bases into normal decimal numbers, and then uses Lagrange interpolation to calculate the constant term of the polynomial. After the calculation, it prints the final result.

The json1.json and json2.json files inside the json folder are the input files. They contain the values of k and the list of base–value pairs. Your program reads these files and uses their data to compute the answer.

The json.jar file inside the libs folder is the JSON library. Java cannot understand JSON by itself, so this file adds the ability to read JSON objects. Without this library, your program would throw errors when trying to parse the JSON files.
