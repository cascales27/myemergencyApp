DOCUMENTACIÓN – SISTEMA DE EMERGENCIAS

Funcionalidad: detección automática con cuenta atrás

1. Descripción general

El sistema de emergencias permite al usuario registrar emergencias de forma manual y también detectar situaciones de riesgo de forma automática mediante un detector de caídas que se ejecuta en segundo plano.

La aplicación está desarrollada en Java y utiliza control de versiones con Git, siguiendo una metodología basada en ramas (main, developer y feature_*).

2. Arquitectura básica del sistema
Clases principales:

Main
Controla el flujo principal del programa y el menú de usuario.

EmergencyManager
Centraliza la lógica de creación y almacenamiento de emergencias.

FallDetector
Hilo independiente que simula la detección automática de caídas.

EmergencyRecord
Modelo de datos que representa una emergencia.

EmergencyHistoryManager
Gestiona la persistencia del historial en formato JSON.

UserAccount / UserData
Gestionan la información del usuario autenticado.

3. Detección automática de emergencias

La detección automática se implementa mediante la clase FallDetector, que extiende Thread.

Funcionamiento:

El detector se ejecuta en segundo plano.

Cada cierto intervalo de tiempo simula una posible caída.

Si se detecta una caída:

Se muestra un aviso por consola.

Se inicia una cuenta atrás de 10 segundos.

Si no hay intervención, la emergencia se envía automáticamente.

Este diseño simula el comportamiento de sistemas reales de detección de emergencias en dispositivos de asistencia.

4. Cuenta atrás de confirmación

Cuando se detecta una posible caída:

Se muestra una cuenta atrás visible por consola.

Al finalizar el tiempo:

La emergencia se registra automáticamente.

Se guarda en el historial con el tipo “Emergencia detectada automáticamente”.

Esto permite simular un margen de reacción antes de enviar la alerta definitiva.

5. Consideraciones técnicas y limitaciones

El detector automático se ejecuta en un hilo independiente.

El menú principal utiliza entrada por consola (Scanner).

El uso concurrente de hilos y entrada estándar puede provocar comportamientos no deterministas (por ejemplo, que la cuenta atrás no siempre se muestre).

Por este motivo:

Se prioriza la estabilidad del sistema.

La lógica automática se mantiene separada del flujo interactivo del menú.

El sistema demuestra correctamente el uso de concurrencia, aunque con las limitaciones propias de aplicaciones de consola.

Estas decisiones son habituales en aplicaciones educativas y están justificadas a nivel técnico.

6. Control de versiones (Git)

Se ha seguido una metodología basada en ramas:

main
Versión estable del proyecto.

developer
Rama de integración de nuevas funcionalidades.

feature_confirmacion_emergencia
Implementación de confirmación de emergencias.

feature_confirmacion_cuenta_atras
Implementación de cuenta atrás en detección automática.

feature_estado_emergencia / feature_estado_simple_emergencia
Pruebas y mejoras incrementales relacionadas con el estado de las emergencias.

Solo las funcionalidades consideradas estables se han integrado en developer.

7. Conclusión

El proyecto implementa:

Programación orientada a objetos.

Uso de hilos (Thread).

Persistencia de datos en JSON.

Control de versiones profesional con Git.

Desarrollo incremental mediante ramas de funcionalidades.

El sistema constituye una base sólida y funcional para una aplicación de gestión de emergencias, cumpliendo los objetivos planteados y dejando margen para futuras ampliaciones.