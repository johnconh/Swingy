# Swingy - Text and GUI Based RPG Game

## Descripción del Proyecto

Swingy es un juego de rol (RPG) basado en texto y en una interfaz gráfica (GUI). El jugador controla a un héroe que debe navegar por un mapa, enfrentarse a enemigos y mejorar sus habilidades a medida que gana experiencia. El juego ofrece la posibilidad de jugar tanto en modo consola como en una interfaz gráfica.

El propósito de este proyecto es demostrar la implementación de la arquitectura **Model-View-Controller (MVC)** en un entorno de juego, utilizando validaciones basadas en anotaciones con **Hibernate Validator** y la gestión de héroes mediante persistencia en una base de datos relacional.

## Características

- Dos modos de juego: 
  - **Modo consola** (basado en texto).
  - **Modo gráfico** (GUI) usando Swing.
- El juego ofrece la opción de crear un héroe nuevo o cargar un héroe previamente guardado.
- El héroe puede ganar experiencia y subir de nivel, lo que mejora sus estadísticas y equipo.
- Sistema de combate en el que el jugador puede optar por atacar o intentar huir.
- Persistencia de héroes y progreso en una base de datos relacional (PostgreSQL).
- Validación de entradas del usuario mediante Hibernate Validator para evitar comportamientos inesperados.

## Instalación

### Requisitos

- **Java 17** o superior
- **Maven** para la gestión de dependencias
- **PostgreSQL** como base de datos para persistir los héroes y su progreso.
  
### Instrucciones

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/swingy.git
   cd swingy
