document.addEventListener("DOMContentLoaded", () => {
  const navbar = document.getElementById("navbar");
  const logoWhite = document.getElementById("logo-white");
  const logoDark = document.getElementById("logo-dark");

  let lastScrolled = false;

  window.addEventListener("scroll", () => {
    const isNowScrolled = window.scrollY > 60;

    // Solo hacer cambios si el estado cambió
    if (isNowScrolled !== lastScrolled) {
      lastScrolled = isNowScrolled;

      navbar.classList.toggle("solid", isNowScrolled);
      navbar.classList.toggle("transparent", !isNowScrolled);

      logoWhite.classList.toggle("visible", !isNowScrolled);
      logoWhite.classList.toggle("hidden", isNowScrolled);

      logoDark.classList.toggle("visible", isNowScrolled);
      logoDark.classList.toggle("hidden", !isNowScrolled);
    }
  });
});
