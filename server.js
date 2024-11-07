const express = require('express');
const path = require('path');
const fs = require('fs');

const app = express();
const PORT = 3000;
app.use(express.json({ limit: '10mb' }));
// Устанавливаем папку frontend как статическую, чтобы обслуживать HTML, CSS и JS файлы
app.use(express.static(path.join(__dirname, 'frontend')));

// Маршрут для получения списка карт из папки maps
app.get('/api/maps', (req, res) => {
    fs.readdir(path.join(__dirname, 'maps'), (err, files) => {
        if (err) {
            return res.status(500).json({ error: 'Ошибка чтения каталога карт' });
        }
        const mapFiles = files.filter(file => file.endsWith('.json')).map(file => ({
            id: file,
            name: path.parse(file).name
        }));
        res.json(mapFiles);
    });
});

// Маршрут для получения содержимого карты по названию файла
app.get('/api/maps/:mapId', (req, res) => {
    const mapPath = path.join(__dirname, 'maps', req.params.mapId);
    fs.readFile(mapPath, 'utf8', (err, data) => {
        if (err) {
            return res.status(404).json({ error: 'Карта не найдена' });
        }
        res.json(JSON.parse(data));
    });
});
app.post('/save-map-data', (req, res) => {
    const mapData = req.body;
    console.log('Полученные данные:', mapData);  // Получаем данные карты из запроса
    const mapName = mapData.name || 'default_map';  // Название карты
    const filePath = path.join(__dirname, 'maps', `${mapName}.json`);  // Путь для сохранения файла

    // Записываем данные карты в файл
    fs.writeFile(filePath, JSON.stringify(mapData, null, 2), (err) => {
        if (err) {
            return res.status(500).json({ message: 'Ошибка при сохранении карты' });
        }

        res.status(200).json({ message: 'Данные карты сохранены' });
    });
});
app.listen(PORT, () => {
    console.log(`Сервер запущен на http://localhost:${PORT}`);
});
