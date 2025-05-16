import { isPublicRoute, isProtectedRoute } from './routes.js';
import { fetchWithAuth } from './auth.js';

export async function navigateTo(path) {
    try {
        if(isPublicRoute(path)) {
            window.location.href = path; //Rutas gestionadas por Spring

        } else if (isProtectedRoute(path)) {
            const response = await fetchWithAuth(path);
            const html = await response.text();
            document.body.innerHTML = html;

            if(windoww.initRouter){
                window.initRouter();
            }

        } else {
            //Ruta no reconocida.
            window.location.href = '/public/home';
        }

    } catch (error){
        console.error("Error el navegar: ", error);
        alert("No se pudo cargar la ruta.");
    }

}