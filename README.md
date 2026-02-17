Documentación

🚨 Sistema de Emergencias
📌 Descripción general

El Sistema de Emergencias es una aplicación desarrollada en Java que permite registrar emergencias de forma manual y detectar situaciones de riesgo automáticamente mediante un sistema de detección de caídas ejecutado en segundo plano.

El proyecto aplica:

Programación Orientada a Objetos (POO)

Uso de hilos (Thread)

Persistencia de datos en formato JSON

Control de versiones con Git mediante metodología por ramas

🏗️ Arquitectura del sistema
Clases principales
Main

Controla el flujo principal del programa y el menú interactivo de usuario.

EmergencyManager

Centraliza la lógica de creación y gestión de emergencias.

FallDetector

Hilo independiente que simula la detección automática de caídas.

EmergencyRecord

Modelo de datos que representa una emergencia registrada.

EmergencyHistoryManager

Gestiona la persistencia del historial de emergencias en formato JSON.

UserAccount / UserData

Gestionan la información del usuario autenticado en el sistema.

⚙️ Detección automática de emergencias

La detección automática se implementa mediante la clase:

FallDetector


Esta clase extiende Thread y se ejecuta en segundo plano.

Funcionamiento

El detector se ejecuta en segundo plano.

Cada cierto intervalo de tiempo simula una posible caída.

Si se detecta una caída:

Se muestra un aviso por consola.

Se inicia una cuenta atrás de 10 segundos.

Si no hay intervención del usuario, la emergencia se envía automáticamente.

Este diseño simula el comportamiento de sistemas reales de asistencia y monitorización.

⏳ Cuenta atrás de confirmación

Cuando se detecta una posible caída:

Se muestra una cuenta atrás visible por consola.

Al finalizar el tiempo:

La emergencia se registra automáticamente.

Se guarda en el historial con el tipo
"Emergencia detectada automáticamente".

Este mecanismo permite simular un margen de reacción antes de enviar la alerta definitiva.

💾 Persistencia de datos

El sistema utiliza almacenamiento en formato JSON para guardar y recuperar el historial de emergencias.

📚 Librería utilizada

Se emplea la librería:

Gson


Ubicación:

lib/gson-2.13.2.jar


Gson permite convertir objetos Java en JSON (serialización) y JSON en objetos Java (deserialización).

🗂️ Clase responsable de la persistencia

La gestión del almacenamiento se realiza mediante:

EmergencyHistoryManager

Responsabilidades principales

Guardar nuevas emergencias en formato JSON.

Leer el historial almacenado.

Convertir objetos EmergencyRecord a JSON y viceversa.

Gestionar la escritura y lectura segura de archivos.

📦 Modelo de datos persistido

Los datos se almacenan a partir del modelo:

EmergencyRecord


Cada registro incluye información como:

Tipo de emergencia

Fecha y hora

Estado

Datos asociados al usuario

Estos objetos se serializan automáticamente a JSON mediante Gson.

🔄 Flujo de almacenamiento

Se genera una nueva emergencia.

EmergencyManager la envía a EmergencyHistoryManager.

El objeto EmergencyRecord se convierte a JSON.

Se guarda en el fichero correspondiente.

Al iniciar la aplicación, el historial puede recuperarse desde el archivo JSON.

⚠️ Consideraciones técnicas y limitaciones

El detector automático se ejecuta en un hilo independiente.

El menú principal utiliza entrada por consola (Scanner).

El uso concurrente de hilos y entrada estándar puede provocar comportamientos no deterministas (por ejemplo, que la cuenta atrás no siempre se muestre correctamente).

Por este motivo:

Se prioriza la estabilidad del sistema.

La lógica automática se mantiene separada del flujo interactivo del menú.

Estas decisiones son habituales en aplicaciones educativas y están justificadas a nivel técnico.

🧩 Control de versiones (Git)

Se ha seguido una metodología basada en ramas:

main → Versión estable del proyecto.

developer → Rama de integración de nuevas funcionalidades.

feature_confirmacion_emergencia

feature_confirmacion_cuenta_atras

feature_estado_emergencia

feature_estado_simple_emergencia

Cada nueva funcionalidad se desarrolla en una rama independiente y solo se integra en developer cuando se considera estable.

▶️ Cómo ejecutar el proyecto
Compilación (PowerShell / Windows)
javac -cp "lib\gson-2.13.2.jar" -d bin (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })

Ejecución
java -cp "bin;lib\gson-2.13.2.jar" com.emergencias.main.Main

🎯 Conclusión

El proyecto implementa correctamente:

Programación orientada a objetos.

Uso de hilos (Thread) para tareas en segundo plano.

Persistencia de datos en JSON mediante Gson.

Control de versiones profesional con Git.

Desarrollo incremental basado en ramas de funcionalidades.

El sistema constituye una base sólida y funcional para una aplicación de gestión de emergencias, preparada para trabajo colaborativo y ampliaciones futuras.
