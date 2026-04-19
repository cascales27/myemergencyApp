🚨 Sistema de Emergencias
📌 Descripción general

El Sistema de Emergencias es una aplicación desarrollada en Java con interfaz gráfica mediante JavaFX, que simula un sistema de asistencia en caso de accidente o caída.

Permite registrar emergencias manualmente desde la interfaz y almacenar la información de forma persistente en una base de datos MySQL.

El proyecto aplica conceptos avanzados de desarrollo de software como:

Programación Orientada a Objetos (POO)
Arquitectura en capas
Patrón DAO (Data Access Object)
Persistencia de datos con JDBC y MySQL
Interfaz gráfica con JavaFX
Uso de hilos (Thread) en funcionalidades auxiliares
Control de versiones con Git
⚙️ Funcionalidades implementadas

El sistema permite:

Activación manual de emergencias desde la interfaz JavaFX
Registro de emergencias en base de datos MySQL
Visualización de historial de emergencias
Gestión de datos del usuario
Cálculo del centro de salud más cercano
Visualización de tutoriales básicos de primeros auxilios
Simulación de coordenadas GPS
🏗️ Arquitectura del sistema

El proyecto está organizado en capas:

📦 Capa de interfaz (UI)
MainApp
MainController
HistoryController
🧠 Capa de lógica
EmergencyManager
💾 Capa de persistencia (DAO)
EmergencyDAO
DatabaseConnection
🧾 Modelo de datos
EmergencyEvent
EmergencyRecord
UserData
HealthCenter
🛠️ Utilidades
HealthCenterLoader
HealthCenterUtils
🗄️ Persistencia de datos (MySQL + JDBC)

El sistema utiliza una base de datos MySQL para almacenar las emergencias de forma persistente.

📌 Patrón DAO

Se ha implementado el patrón DAO (Data Access Object) para separar la lógica de acceso a datos del resto de la aplicación.

EmergencyDAO: gestiona las operaciones SQL
DatabaseConnection: gestiona la conexión JDBC
🧱 Estructura de la tabla

Tabla: emergencies

id
type
user_name
latitude
longitude
status
created_at
🔄 Flujo del sistema
El usuario crea una emergencia desde la interfaz JavaFX
EmergencyManager procesa la solicitud
Se crea un objeto EmergencyEvent
EmergencyDAO inserta el registro en MySQL
La emergencia queda almacenada de forma persistente
⚠️ Consideraciones técnicas
Interfaz desarrollada con JavaFX
Persistencia en MySQL mediante JDBC
Uso del driver oficial mysql-connector-j
Arquitectura modular basada en paquetes
Separación clara entre lógica, modelo y persistencia
🧩 Control de versiones (Git)

El proyecto se ha desarrollado mediante ramas funcionales, integrando progresivamente mejoras como:

migración de estructura de proyecto a src/main/java
integración de base de datos MySQL
implementación del patrón DAO
refactorización y limpieza de código duplicado
integración de JavaFX
▶️ Ejecución del proyecto
Compilación
javac -cp "lib/*;javafx-sdk-25.0.2/lib/*" -d bin src/main/java
Ejecución
java --module-path "javafx-sdk-25.0.2/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp "bin;lib/*;src/main/resources" \
com.emergencias.ui.MainApp
🎯 Conclusión

El proyecto ha evolucionado desde una aplicación basada en consola y ficheros JSON hacia una arquitectura moderna basada en:

interfaz gráfica JavaFX
base de datos MySQL
patrón DAO
arquitectura en capas

Esto mejora la escalabilidad, mantenibilidad y realismo del sistema, acercándolo a una aplicación real de gestión de emergencias.