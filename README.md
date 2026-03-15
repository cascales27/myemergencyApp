# 🚨 Sistema de Emergencias

## 📌 Descripción general

El **Sistema de Emergencias** es una aplicación desarrollada en **Java** que simula un sistema de asistencia en caso de accidente o caída.

Permite registrar emergencias manualmente, detectar situaciones de riesgo automáticamente mediante un sistema de detección de caídas ejecutado en segundo plano y proporcionar información útil al usuario en situaciones críticas.

El proyecto aplica diferentes conceptos de desarrollo de software como:

- Programación Orientada a Objetos (POO)
- Uso de hilos (`Thread`)
- Persistencia de datos en formato JSON
- Uso de librerías externas
- Control de versiones con Git mediante desarrollo basado en ramas

---

# ⚙️ Funcionalidades implementadas

El sistema permite:

- Activación manual de emergencias
- Detección automática de caídas mediante un hilo en segundo plano
- Cuenta atrás de confirmación antes de enviar una alerta automática
- Registro y persistencia del historial de emergencias en formato JSON
- Visualización de tutoriales básicos de primeros auxilios según el tipo de emergencia
- Carga de centros de salud desde archivo JSON
- Cálculo del centro de salud más cercano a la ubicación del usuario

Estas funcionalidades simulan el comportamiento básico de sistemas de asistencia presentes en dispositivos como relojes inteligentes o aplicaciones de seguridad personal.

---

# 🏗️ Arquitectura del sistema

## Clases principales

### Main
Controla el flujo principal del programa y el menú interactivo de usuario.

### EmergencyManager
Centraliza la lógica de creación y gestión de emergencias.

### FallDetector
Hilo independiente que simula la detección automática de caídas.

### EmergencyRecord
Modelo de datos que representa una emergencia registrada.

### EmergencyHistoryManager
Gestiona la persistencia del historial de emergencias en formato JSON.

### UserAccount / UserData
Gestionan la información del usuario autenticado en el sistema.

### HealthCenterLoader
Clase encargada de cargar los centros sanitarios desde el fichero JSON.

### HealthCenterUtils
Clase utilitaria que calcula la distancia entre coordenadas y permite determinar el centro de salud más cercano.

---

# ⚙️ Detección automática de emergencias

La detección automática se implementa mediante la clase:

FallDetector

Esta clase extiende `Thread` y se ejecuta en segundo plano.

### Funcionamiento

1. El detector se ejecuta en segundo plano.
2. Cada cierto intervalo de tiempo simula una posible caída.
3. Si se detecta una caída:

- Se muestra un aviso por consola
- Se inicia una cuenta atrás de 10 segundos
- Si no hay intervención del usuario, la emergencia se envía automáticamente

Este diseño simula el comportamiento de sistemas reales de asistencia y monitorización.

---

# ⏳ Cuenta atrás de confirmación

Cuando se detecta una posible caída:

1. Se muestra una cuenta atrás visible por consola.
2. El usuario puede cancelar la emergencia.
3. Si finaliza el tiempo sin respuesta:

- La emergencia se registra automáticamente
- Se guarda en el historial con el tipo  
"Emergencia detectada automáticamente".

Este mecanismo simula un margen de reacción antes de enviar una alerta definitiva.

---

# 💾 Persistencia de datos

El sistema utiliza almacenamiento en **formato JSON** para guardar y recuperar el historial de emergencias.

## 📚 Librería utilizada

Se emplea la librería:

Gson

Ubicación:

lib/gson-2.13.2.jar

Gson permite convertir objetos Java en JSON (**serialización**) y JSON en objetos Java (**deserialización**).

---

## 🗂️ Clase responsable de la persistencia

La gestión del almacenamiento se realiza mediante:

EmergencyHistoryManager

### Responsabilidades principales

- Guardar nuevas emergencias en formato JSON
- Leer el historial almacenado
- Convertir objetos `EmergencyRecord` a JSON y viceversa
- Gestionar la escritura y lectura segura de archivos

---

# 🏥 Centros de salud

El sistema incluye un listado de centros sanitarios cargado desde un fichero JSON.

Este fichero contiene información geográfica y administrativa de los centros, como:

- Nombre del municipio
- Denominación del centro
- Tipo de centro sanitario
- Coordenadas geográficas

La clase `HealthCenterLoader` se encarga de leer el fichero y convertir los datos en objetos Java mediante la librería **Gson**.

Posteriormente, `HealthCenterUtils` calcula cuál es el centro sanitario más cercano a la ubicación actual del usuario.

---

# 📦 Modelo de datos persistido

Los datos se almacenan a partir del modelo:

EmergencyRecord

Cada registro incluye información como:

- Tipo de emergencia
- Ubicación
- Coordenadas
- Datos del usuario
- Información de contacto

Estos objetos se serializan automáticamente a JSON mediante **Gson**.

---

# 🔄 Flujo de almacenamiento

1. Se genera una nueva emergencia.
2. `EmergencyManager` la envía a `EmergencyHistoryManager`.
3. El objeto `EmergencyRecord` se convierte a JSON.
4. Se guarda en el fichero correspondiente.
5. Al iniciar la aplicación, el historial puede recuperarse desde el archivo JSON.

---

# ⚠️ Consideraciones técnicas

- El detector automático se ejecuta en un hilo independiente.
- El menú principal utiliza entrada por consola (`Scanner`).
- El uso concurrente de hilos y entrada estándar puede provocar comportamientos no deterministas.

Por este motivo:

- Se prioriza la estabilidad del sistema
- La lógica automática se mantiene separada del flujo interactivo del menú

Estas decisiones son habituales en aplicaciones educativas y están justificadas a nivel técnico.

---

# 🧩 Control de versiones (Git)

El proyecto se ha desarrollado utilizando **metodología basada en ramas**.

### Ramas principales

main → Versión estable del proyecto.

developer → Rama de integración de nuevas funcionalidades.

### Ejemplos de ramas de desarrollo

- feature_confirmacion_emergencia
- feature_confirmacion_cuenta_atras
- feature_estado_emergencia
- feature_estado_simple_emergencia
- feature_integracion_json
- feature_tutorial_primeros_auxilios
- feature_centro_mas_cercano

Cada funcionalidad se desarrolla en una rama independiente y posteriormente se integra en la rama principal cuando se considera estable.

---

# ▶️ Cómo ejecutar el proyecto

## Compilación (PowerShell / Windows)

javac -cp "lib\gson-2.13.2.jar" -d bin (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })


## Ejecución

java -cp "bin;lib\gson-2.13.2.jar" com.emergencias.main.Main


---

# 🎯 Conclusión

El proyecto implementa correctamente:

- Programación orientada a objetos
- Uso de hilos (`Thread`) para tareas en segundo plano
- Persistencia de datos en JSON mediante Gson
- Carga y procesamiento de datos geográficos desde archivos JSON
- Cálculo del centro sanitario más cercano
- Control de versiones con Git mediante desarrollo basado en ramas

El sistema constituye una base funcional para una futura aplicación real de asistencia en emergencias, pudiendo ampliarse con:

- Sensores reales de dispositivo
- Geolocalización real
- Integración con servicios de emergencia
- Notificación automática a contactos de confianza