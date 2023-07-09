document.addEventListener('DOMContentLoaded', function() {
    var title = document.querySelector('h1');
    var initialFontSize = parseFloat(getComputedStyle(title).fontSize);

    // Отримання позиції курсора відносно напису
    function getPosition(event) {
        var rect = title.getBoundingClientRect();
        var x = event.clientX - rect.left;
        var y = event.clientY - rect.top;

        return { x: x, y: y };
    }

    // Зміна розміру шрифту в залежності від позиції курсора
    function adjustFontSize(event) {
        var position = getPosition(event);
        var threshold = 40; // Відстань від верхнього краю, коли починається зміна розміру шрифту
        var maxScale = 1.6; // Максимальне збільшення шрифту
        var minScale = 1.0; // Початковий розмір шрифту

        var scaleFactor = maxScale - minScale; // Діапазон зміни шрифту
        var scale = minScale;

        if (position.y < threshold) {
            scale = minScale + (threshold - position.y) / threshold * scaleFactor;
        }

        title.style.fontSize = (initialFontSize * scale) + 'px';
    }

    // Плавна анімація зміни розміру шрифту
    function animateFontSizeChange() {
        title.style.transition = 'font-size 0.2s ease-in-out';
    }

    // Відновлення початкової анімації після закінчення розташування курсора
    function resetAnimation() {
        title.style.transition = '';
    }

    // Обробник події наведення курсору на напис
    title.addEventListener('mouseenter', animateFontSizeChange);
    title.addEventListener('mousemove', adjustFontSize);

    // Обробник події відведення курсору від напису
    title.addEventListener('mouseleave', function() {
        resetAnimation();
        title.style.fontSize = initialFontSize + 'px';
    });
});