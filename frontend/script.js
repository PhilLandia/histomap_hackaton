// Показ главной страницы при загрузке
document.addEventListener("DOMContentLoaded", function() {
    showPage('homePage');
});

// Функция для переключения страниц
function showPage(pageId) {
    document.querySelectorAll('.page').forEach(page => page.style.display = 'none');
    document.getElementById(pageId).style.display = 'block';

    if (pageId === 'createMapPage') {
        initializeMap(); // Инициализация карты при переходе на страницу создания карты
    }
}

// Инициализация карты с OSM
let map;

function initializeMap() {
    if (!map) { // Проверка, чтобы карта не создавалась заново
        map = L.map('map').setView([55.751244, 37.618423], 10); // центр карты на Москву
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(map);
    }
}
const intervalSlider = document.getElementById('interval-range');  // Обновлено id для ползунка
const intervalValue = document.getElementById('intervalValue');

intervalSlider.oninput = function() {
    intervalValue.textContent = intervalSlider.value;
}
// Объект для хранения данных GeoJSON и аннотаций по годам
let geojsonDataByYear = {}; // Формат: { 2023: { geojson: {}, annotation: "Текст" }, ... }
// Функция добавления аннотации на карту для конкретного года
// Функция добавления аннотации на карту для конкретного года с очисткой текущих аннотаций
// Функция добавления аннотации на карту для конкретного года с очисткой текущих аннотаций
function addAnnotation(year) {
    const annotationText = document.getElementById(`annotation-${year}`).value;
    if (!annotationText) {
        alert("Пожалуйста, введите текст аннотации.");
        return;
    }

    // Получаем координаты центра карты для размещения аннотации
    const center = map.getCenter();

    // Проверяем, если для данного года еще нет объекта, создаем его
    if (!geojsonDataByYear[year]) {
        geojsonDataByYear[year] = { geojson: null, annotations: [] };
    }

    // Создаем новый маркер с аннотацией и делаем его draggable
    const marker = L.marker([center.lat, center.lng], { draggable: true }).addTo(map)
        .bindPopup(annotationText)
        .openPopup();

    // Добавляем новый маркер в массив аннотаций для текущего года
    geojsonDataByYear[year].annotations.push(marker);

    // Сохраняем аннотированные данные для этого года
    saveAnnotationsForYear(year);
}

// Функция для сохранения аннотаций в объекте (при изменении или удалении)
function saveAnnotationsForYear(year) {
    const annotations = geojsonDataByYear[year].annotations.map(marker => ({
        lat: marker.getLatLng().lat,
        lng: marker.getLatLng().lng,
        text: marker.getPopup().getContent()
    }));
    geojsonDataByYear[year].annotationsData = annotations;  // Сохраняем данные аннотаций
}
// Функция для генерации временных меток
function generateTimestamps() {
    const startYear = parseInt(document.getElementById('start-year').value);
    const endYear = parseInt(document.getElementById('end-year').value);
    const interval = parseInt(document.getElementById('interval-range').value);
    const timestampsContainer = document.getElementById('timestamps');
    
    timestampsContainer.innerHTML = '';

    if (isNaN(startYear) || isNaN(endYear) || isNaN(interval) || startYear >= endYear || interval <= 0) {
        alert("Пожалуйста, введите корректные значения.");
        return;
    }

    for (let year = startYear; year <= endYear; year += interval) {
        const timestampElement = document.createElement('div');
        timestampElement.className = 'timestamp';
        timestampElement.innerHTML = `
            <strong onclick="displayGeoJSON(${year})" style="cursor: pointer;">${year}</strong>
            <button class="geojson-upload-btn" data-year="${year}">Загрузить GeoJSON</button>
            <input type="text" id="annotation-${year}" placeholder="Добавить аннотацию" style="width: 100%; margin-top: 5px; padding-top: 8px; padding-bottom: 8px; font-size: 14px; border-radius: 5px; border: none;">
            <button class="add-annotation-btn" data-year="${year}">Добавить аннотацию на карту</button>
        `;
        timestampsContainer.appendChild(timestampElement);
    }
}

// Обработчик для добавления аннотаций
document.getElementById('timestamps').addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('add-annotation-btn')) {
        const year = event.target.getAttribute('data-year');
        addAnnotation(year);
    }
});
// Обработчик делегирования событий для кнопок и других динамических элементов
document.getElementById('timestamps').addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('geojson-upload-btn')) {
        // Получаем год из атрибута data-year кнопки
        const year = event.target.getAttribute('data-year');
        selectGeoJSONFile(year);
    }
});

// Функция для выбора GeoJSON файла для конкретного года
function selectGeoJSONFile(year) {
    // Если элемента с id="geojson-upload" нет, создаем его
    let fileInput = document.getElementById('geojson-upload');
    
    // Если элемент не существует, создаем его
    if (!fileInput) {
        fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.id = 'geojson-upload';
        fileInput.style.display = 'none';  
        fileInput.accept = '.geojson';// Прячем элемент, так как мы будем активировать его через JS
        document.body.appendChild(fileInput);
    }

    fileInput.onchange = function() {
        loadGeoJSONFile(year);
    };
    
    fileInput.click();
}

// Функция для загрузки GeoJSON файла
function loadGeoJSONFile(year) {
    const fileInput = document.getElementById('geojson-upload');
    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function(event) {
            const geojsonData = JSON.parse(event.target.result);

            // Сохраняем данные GeoJSON и аннотацию для выбранного года
            const annotation = document.getElementById(`annotation-${year}`).value;
            geojsonDataByYear[year] = {
                geojson: geojsonData,
                annotation: annotation
            };

            // Отображаем данные на карте
            displayGeoJSON(year);
        };
        reader.readAsText(file);
    }
}

// Функция отображения GeoJSON и аннотации для конкретного года
function displayGeoJSON(year) {
    if (geojsonDataByYear[year]) {
        // Очищаем карту от старых слоев GeoJSON, если они существуют
        if (map.geoJSONLayer) {
            map.removeLayer(map.geoJSONLayer);
        }

        // Добавляем новый слой GeoJSON на карту для текущего года
        map.geoJSONLayer = L.geoJSON(geojsonDataByYear[year].geojson).addTo(map);

        // Учитываем все маркеры и объекты для расчета границ
        const bounds = map.geoJSONLayer.getBounds();
        
        // Скрываем аннотации для всех других лет
        hideAnnotationsForOtherYears(year);

        // Отображаем аннотации для текущего года, если они есть
        if (geojsonDataByYear[year].annotations) {
            geojsonDataByYear[year].annotations.forEach(marker => {
                marker.addTo(map); // Добавляем маркер на карту
                bounds.extend(marker.getLatLng()); // Добавляем маркер в расчет границ
            });
        }

        // Устанавливаем границы карты, чтобы отображать все объекты и аннотации
        map.fitBounds(bounds);
    } else {
        alert(`Нет данных для ${year}`);
    }
}

// Функция скрытия аннотаций для всех лет, кроме указанного
function hideAnnotationsForOtherYears(currentYear) {
    Object.keys(geojsonDataByYear).forEach(year => {
        if (year !== currentYear && geojsonDataByYear[year].annotations) {
            geojsonDataByYear[year].annotations.forEach(marker => {
                map.removeLayer(marker); // Убираем маркер с карты для другого года
            });
        }
    });
}
// Функция для удаления аннотаций для другого года
function removeOldAnnotations(year) {
    if (geojsonDataByYear[year] && geojsonDataByYear[year].annotations) {
        geojsonDataByYear[year].annotations.forEach(marker => {
            map.removeLayer(marker); // Убираем маркеры для другого года
        });
        geojsonDataByYear[year].annotations = []; // Очищаем массив аннотаций
    }
}
// Показ сообщения об успешной загрузке GeoJSON
function uploadGeoJSON(year) {
    alert(`Загрузка GeoJSON для ${year}`);
}

