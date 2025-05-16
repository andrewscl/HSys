// initRouter.js

import { navigateTo } from '/navigationHandler.js';

export function initRouter() {
    // Buscar todos los enlaces internos
    const links = document.querySelectorAll('a[data-link]');

    links.forEach(link => {
        link.addEventListener('click', async (event) => {
            event.preventDefault();

            const path = link.getAttribute('href');

            if (path) {
                await navigateTo(path);
            }
        });
    });
}
