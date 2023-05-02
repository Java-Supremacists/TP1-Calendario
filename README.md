# TP Calendario

Alumnos:

- Gerez Facundo Nahuel - 109429
- Orsi Tomas Fabrizio - 109735

![Diagrama UML](Diagrama/diagramaClases.png)

# Consigna
Lógica de negocio (etapa 1)

1. En un calendario se pueden crear, modificar y eliminar eventos y tareas.
2. Tanto los eventos como tareas pueden tener un título y una descripción.
3. Las tareas pueden marcarse como completadas.
4. Los eventos pueden ser:
	1. De día completo.
	2. Comenzar en una fecha y hora y tener una duración arbitrarios.
        En ambos casos, el evento puede comenzar en un día y terminar en otro.
5. Las tareas no tienen duración, pudiendo ser:
	1. De día completo.
	2. Tener una fecha y hora de vencimiento.
6. Los eventos se pueden repetir:
	1. Con frecuencia diaria, semanal, mensual o anual.
	2. En caso de frecuencia diaria, es posible definir un intervalo (ej: “cada 3 días”).
	3. En caso de frecuencia semanal, es posible definir los días de la semana (ej: “todos los martes y jueves”).
	4. La repetición puede ser:
		1. Infinita.
		2. Terminar en una fecha determinada (ej: hasta el 13 de enero).
		3. Terminar luego de una cantidad de repeticiones dada (ej: luego de 20 ocurrencias).
	5. Al modificar o eliminar un evento con repetición, el cambio o eliminación se aplica a todas sus repticiones.
7. En un evento o tarea se pueden configurar una o más alarmas:
	1. La alarma se dispara en un instante de tiempo, que se puede determinar de dos maneras:
		1. Una fecha y hora absoluta
		2. Un intervalo de tiempo relativo a la fecha y hora del evento/tarea (ej: “30 minutos antes”).
	2. El efecto producido al dispararse la alarma es configurable:
		1. Mostrar una notificación.
		2. Reproducir un sonido.
		3. Enviar un email.
		Nota: dado que en la primera etapa no se implementa la interacción con el usuario, no se deben implementar los efectos de las alarmas; pero sí deben tener pruebas asociadas.
