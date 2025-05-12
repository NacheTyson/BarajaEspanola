Se desea implementar un programa que maneje barajas de cartas. Una carta será una clase que tendrá dos atributos, número (int) y palo (String).
Una baraja estará compuesta por un conjunto de cartas. Con una baraja se podrá hacer una serie de operaciones:
• crearBaraja: será un método abstracto sin parámetros. • barajar: cambia la posición de las cartas de forma aleatoria. Para ello utilizaremos
la instrucción Collections.shuffle(lista), siendo lista un objeto de la clase ArrayList o LinkedLIst (se deberá importar java.util.Collections)
Trabajaremos con dos tipos de barajas, baraja española y baraja francesa. La baraja española tendrá un atributo booleano que me indicará si quiero jugar con 8 y 9 en cuyo caso tendré un total de 48 cartas o sin 8 y 9 haciendo un total de 40 cartas (si trabajamos sin 8 y 9 los números de las cartas de cada palo serán 1, 2, 3, 4, 5, 6, 7, 10, 11 y 12). Los palos de la baraja española serán OROS, COPAS, ESPADAS y BASTOS. La baraja francesa no tendrá ningún atributo y el número de cartas para cada palo será de 13 haciendo un total de 52 cartas. Los palos serán DIAMANTES, PICAS, CORAZONES y TRÉBOLES.
A la hora de mostrar una carta por pantalla se indicará el número y el palo pero en el caso de la baraja española en lugar de indicar los números 10, 11 y 12 los nombraremos como Sota, Caballo y Rey respectivamente, y en el caso de la francesa los números 11, 12 y 13 serán Jota, Reina y Rey. En ambos casos el 1 se nombra como As.
El programa principal mostrará un menú con las siguientes opciones: 1. Nueva baraja: creará una nueva baraja preguntando al usuario el tipo de baraja y,
en el caso de la española, si debe incluir 8 y 9. Si hubiese una ya creada ésta será reemplazada por la nueva.
2. Barajar: barajará las cartas de la baraja usando el método implementado en la
clase Baraja. En el caso de que no hubiera ninguna baraja creada lo indicará. 3. Mostrar baraja: mostrará todas las cartas de la baraja por pantalla de la forma que
se indica en el párrafo anterior. En el caso de que no hubiera ninguna baraja creada lo indicará.
4. Imprimir baraja: guardará en un fichero de texto cuyo nombre indicará el usuario la
baraja en el mismo formato que en la opción anterior 3. En el caso de que no hubiera ninguna baraja creada lo indicará.
5. Salir
Al salir del programa se almacenará la situación actual del programa de forma que al volver a iniciar el programa se recupere la baraja en el caso de que ésta ya hubiese sido creada en una ejecución anterior.
Se deberá controlar todas las posibles excepciones.
