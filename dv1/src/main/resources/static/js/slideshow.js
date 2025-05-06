document.addEventListener('DOMContentLoaded', function(){
    const slides = document.querySelectorAll('.slide');
    const prevButton = document.querySelector('.nav.prev');
    const nextButton = document.querySelector('.nav.next');
    const dotsContainer = document.querySelector('.dots');
    const dots = document.querySelectorAll('.dot');
    let currentIndex = 0;

    function showSlide (index) {
        slides.forEach(slide => slide.classList.remove('active'));
        dots.forEach(dot => dot.classList.remove('active'));

        slides[index].classList.add('active');
        dots[index].classList.add('active');
        currentIndex = index;
    }
    
    function nextSlide() {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(currentIndex);
    }

    function prevSlide(){
        currentIndex =
            (currentIndex - 1 + slides.length) % slides.length;
        showSlide(currentIndex);
    }

    function goToSlide (index){
        showSlide(index);
    }

    //Event listeners para los botones de navegación
    if(nextButton){
        nextButton.addEventListener('click', nextSlide);
    }

    if(prevButton){
        prevButton.addEventListener('click', prevSlide);
    }

    //Event listeners para los puntos
    dots.forEach((dot, index) => {
        dot.addEventListener('click', ()=> goToSlide(index))
    });

    showSlide(0);
});