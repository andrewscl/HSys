console.log("signup.js cargado correctamente");

import { navigateTo } from '/navigationHandler.js';

document.addEventListener('DOMContentLoaded', () => {
    // Enfocar el input de usuario
    const usernameInput = document.getElementById('username');
    if (usernameInput) {
        usernameInput.focus();
    }

    // Mostrar/ocultar contraseña
    const passwordInput = document.getElementById('password');
    const togglePasswordButton = document.getElementById('togglePassword');

    if (togglePasswordButton && passwordInput) {
        togglePasswordButton.addEventListener('click', () => {
            const type = passwordInput.type === 'password' ? 'text' : 'password';
            passwordInput.type = type;
            togglePasswordButton.textContent = type === 'password' ? 'Mostrar' : 'Ocultar';
        });
    }

    // Capturar el submit del formulario
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', async (e) => {
            e.preventDefault(); // 🚫 evita que se envíe por defecto

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            try {
                const response = await fetch("/api/auth/signup", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ username, password })
                });

                if (!response.ok) {
                    const err = await response.text();
                    throw new Error(err || "Registro fallido");
                }

                // ✅ Redirigir mediante navigationHandler
                await navigateTo("/public/home");

            } catch (error) {
                alert("Error al registrar: " + error.message);
            }
        });
    }
});
