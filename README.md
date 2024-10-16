# NearbyEats

NearbyEats es una plataforma web diseñada para fomentar el comercio y la gastronomía regional, priorizando el apoyo a los pequeños comerciantes. Permite a los usuarios buscar, explorar y compartir lugares de interés como restaurantes, cafeterías, museos y hoteles, cercanos a su ubicación.

## Tecnologías Utilizadas

- **Backend**: Spring Boot, Gradle, MongoDB
- **Frontend**: Angular (TypeScript), Npm, Bootstrap 5

## Funcionalidades Principales

### Moderador

- Autorizar o rechazar lugares.
- Registro de actividades de moderación.
- Lista de lugares pendientes y autorizados.

### Usuario

- Registro y autenticación.
- Recuperación de contraseña por email.
- Explorar y visualizar lugares en un mapa.
- Crear, editar y eliminar lugares.
- Comentar y calificar lugares.
- Guardar lugares como favoritos.
- Búsqueda por nombre, tipo y distancia.
- Solicitar ruta entre ubicaciones con distancia y tiempo de viaje.
- Ver lista de lugares creados y responder comentarios.
- Eliminar cuenta.

## Consideraciones Importantes

- Utilización de Mapbox para la integración de mapas.
- Notificaciones por correo electrónico para acciones relevantes.
- Gestión de imágenes a través de un servicio externo (Cloudinary, Firebase, etc.).
- Código fuente alojado en GitHub para colaboración del equipo de desarrollo.
- Moderadores preconfigurados en la base de datos.