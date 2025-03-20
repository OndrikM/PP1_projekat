# PP1_projekat
 Ovaj projekat predstavlja kompajler za Mikrojavu — pojednostavljeni jezik baziran na Javi. Projekat ima za cilj da pomogne u učenju osnovnih principa kompajliranja, kao i u razumevanju kako se prevode programski jezici u mašinski kod. Kroz razvoj ovog kompajlera, implementirani su ključni aspekti procesa kompajliranja, uključujući leksičku analizu, sintaktičku analizu, semantičku analizu i generisanje koda.

Korišćeni alati Lex i Cup omogućavaju leksičku i sintaktičku analizu. Alat Lex koristi se za prepoznavanje osnovnih komandi Mikrojave, kao što su promenljive, petlje, uslovi, funkcije i objekti. Alat Cup koristi se za prepoznavanje sintaktičkih pravila i grešaka u programu. Ovi alati se nalaze u direktorijumu spec.

Projekat implementira leksičku analizu koja prepoznaje osnovne elemente Mikrojave. Takođe, sintaktička analiza validira strukturu koda i proverava da li kod prati sintaktička pravila Mikrojave. Semantička analiza detektuje greške u vezi sa tipovima podataka i operacijama. Generisanje srednjeg koda predstavlja prelaznu fazu ka ciljanom kodu, koji se kasnije generiše i optimizuje.

Projekat je razvijen korišćenjem Jave kao glavnog programskog jezika, dok su alatke Lex i Cup korišćene za analizu. Za testiranje i validaciju funkcionalnosti kompajlera takođe su korišćeni dodatni alati.

This project is a MicroJava compiler, a simplified Java-based language, aimed at learning the basic principles of compilation and understanding how programming languages are translated into machine code. Through the development of this compiler, key aspects of the compilation process were implemented, including lexical analysis, syntactical analysis, semantic analysis, and code generation.

The tools Lex and Cup are used for lexical and syntactical analysis. Lex is used for recognizing basic commands in MicroJava, such as variables, loops, conditions, functions, and objects. Cup is used for recognizing syntax rules and errors in the program. These tools are located in the spec directory.

The project implements lexical analysis that recognizes basic elements of MicroJava. Additionally, the syntactical analysis validates the structure of the code and checks whether the code follows the syntax rules of MicroJava. Semantic analysis detects errors related to data types and operations. Intermediate code generation represents a transitional phase toward target code, which is later generated and optimized.

The project was developed using Java as the main programming language, with Lex and Cup tools used for analysis. Additional tools were also used for testing and validating the compiler's functionality.
