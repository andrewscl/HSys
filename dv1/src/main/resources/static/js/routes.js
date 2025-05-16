// routes.js

// Lista de rutas con su tipo
const routeConfig = [
  { path: '/public/', type: 'public' },
  { path: '/auth/', type: 'public' },
  { path: '/private/', type: 'protected' },
];

// Función para verificar coincidencia básica de prefijo
function matchRoute(path, prefix) {
  return path.startsWith(prefix);
}

// Determina el tipo de ruta según configuración
export function getRouteType(path) {
  for (const route of routeConfig) {
    if (matchRoute(path, route.path)) {
      return route.type;
    }
  }
  return 'unknown';
}

export function isPublicRoute(path) {
  return getRouteType(path) === 'public';
}

export function isProtectedRoute(path) {
  return getRouteType(path) === 'protected';
}
