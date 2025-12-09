README
Descripción del Proyecto
Este proyecto implementa un sistema de procesamiento funcional de tweets a partir de un archivo CSV del dataset Twitter Entity Sentiment Analysis obtenido de Kaggle.
El sistema realiza las siguientes tareas principales:
•	Lectura de tweets desde un archivo CSV.
•	Limpieza y transformación del texto:
o	Eliminación de menciones (@usuario).
o	Eliminación de hashtags (#tema).
o	Eliminación de espacios extra.
o	Conversión a mayúsculas.
•	Análisis estadístico de los tweets:
o	Conteo por tipo de sentimiento.
o	Cálculo del promedio de longitud del texto para un sentimiento específico.
•	Generación de archivos de salida con los tweets limpios y un resumen estadístico.

Estructura del Proyecto

ProyectoTweetsFuncional/
├── data/
│   └── tweets.csv
├── output/
│   ├── tweets_limpios.csv
│   └── resumen_estadisticas.txt
└── src/
    ├── app/
    │   └── Main.java
    ├── model/
    │   └── Tweet.java
    ├── service/
    │   ├── TweetLoader.java
    │   ├── TweetAnalyticsService.java
    │   └── TextTransformService.java
    └── report/
        └── ReportGenerator.java








Descripción de los paquetes

Model
Contiene la clase Tweet, que representa únicamente la estructura de datos de un tweet.
Atributos principales:
•	id
•	entidad
•	sentimiento
•	contenido
Incluye:
•	Getters
•	Método toString()

service
Contiene toda la lógica de procesamiento funcional:
 TweetLoader
Implementa la lectura del archivo CSV usando:
•	Supplier<List<Tweet>>
•	Método: crearLectorTweets(String rutaArchivo)
Retorna un Supplier que al ejecutar get() carga los tweets.

 TextTransformService
Contiene las transformaciones de texto usando:
•	Function<Tweet, Tweet>
•	Consumer<Tweet>
Métodos principales:
•	transformarTweets(...)
Aplica una transformación a una lista de tweets.
•	procesarTweets(...)
Aplica una transformación y consume el resultado usando un Consumer.
Aquí se definen funciones como:
•	Eliminación de menciones
•	Eliminación de hashtags
•	Eliminación de espacios
•	Conversión a mayúsculas

TweetAnalyticsService
Realiza análisis estadístico usando Streams:
•	calcularPromedioLongitud(List<Tweet>, String sentimiento)
•	contarTweetsPorSentimiento(List<Tweet>)
Devuelve mapas y valores promedio según el sentimiento.

report
Contiene la generación de archivos de salida:
ReportGenerator
Se encarga de guardar:
•	Tweets limpios en archivo CSV.
•	Resumen estadístico en archivo de texto.

app
Contiene la clase principal:
Main.java
Aquí se orquesta todo el flujo del programa usando:
•	Supplier → Lectura de datos
•	Function → Transformación de tweets
•	Consumer → Consumo de tweets transformados
•	Runnable → Ejecución completa del pipeline funcional
El Runnable ejecuta:
1.	La carga de tweets.
2.	La transformación.
3.	El procesamiento.
4.	El análisis estadístico.
5.	La generación de reportes.
Interfaz	Ubicación
Supplier<List<Tweet>>	service/TweetLoader.java
Function<Tweet, Tweet>	service/TextTransformService.java
Consumer<Tweet>	app/Main.java
Runnable	app/Main.java



Archivos de salida generados
•	output/tweets_limpios.csv → Contiene todos los tweets transformados.
•	output/resumen_estadisticas.txt → Contiene:
o	Conteo por sentimiento.
o	Promedio de longitud de tweets positivos.

DECLARATORIA DE USO DE IA
¿Utilizaste herramientas de IA generativa (como ChatGPT, Copilot, etc.) para este proyecto? Sí, me apoyé para recordar cómo era la forma más factible de leer un archivo con una gran cantidad de datos, además para saber cuáles eran las expresiones regulares que necesitaba para poder leer correctamente el archivo csv y realizar el archivo de salida. 
También la usé para resolver un error de tipo: ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3. 
He revisado, comprendido y probado este Código, por lo tanto me hago responsible de su funcionamiento.



