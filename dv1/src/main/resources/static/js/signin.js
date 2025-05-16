import { fetchWithAuth } from './auth.js';

console.log("signin.js cargado correctamente");

document.addEventListener('DOMContentLoaded', function() {
    // Enfocar el primer campo (nombre de usuario) al cargar la página
    const usernameInput = document.getElementById('username');
    if (usernameInput) {
        usernameInput.focus();
    }

    // Funcionalidad para mostrar/ocultar contraseña (opcional)
    const passwordInput = document.getElementById('password');
    const togglePasswordButton = document.getElementById('togglePassword'); // Necesitarías añadir este botón en tu HTML

    if (togglePasswordButton && passwordInput) {
        togglePasswordButton.addEventListener('click', function() {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            this.textContent = type === 'password' ? 'Mostrar' : 'Ocultar'; // Cambia el texto del botón
        });
    }

});

document.addEventListener('DOMContentLoaded', () => {
    const loginBtn = document.getElementById("loginBtn");

    if(loginBtn) {
        loginBtn.addEventListener("click", (e) => {
            e.preventDefault();
        login();
        });
    }
});

//Login y manejo del token JWT:
async function login(){
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        /*Para realizar la solicitud fetch, se envía una solicitud POST
        a /api/auth/login con los datos de inicio de sesión
        (usuario y contraseña) en formato JSON.*/
        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({username, password})
        });

        if(!response.ok) {
            const message = await response.text();
            throw new Error(message || "Credenciales invalidas");
        }

        const data  = await response.json();
        const token = data.token;

        //Guardar el token en local storage
        localStorage.setItem("jwt", token);

        //decodifica el token para determinar el rol
        const payload = parseJwt(token);
        const roles = payload?.roles || [];

        let targetUrl = "/private/home"; //Ruta por defecto.

        if(roles.includes("ROLE_ADMIN")){
            targetUrl = "/admin/dashboard"
        } else if (roles.includes("ROLE_CLIENT")){
            targetUrl = "/private/home"
        }

        const protectResponse = await fetchWithAuth(targetUrl);
        const html = await protectResponse.text();

        document.body.innerHTML = html;
        window.history.pushState({}, '', targetUrl);

    } catch (error) {
        alert("Error al iniciar sesión: " + error.message);
    }
};

//función auxiliar para decodificar el token
function parseJwt(token) {
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload);
    } catch (e) {
        console.error("Error al decodificar el token", e);
        return null;
    }
}


