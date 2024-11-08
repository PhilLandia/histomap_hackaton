const express = require('express');
const path = require('path');
const fs = require('fs');

const app = express();
const PORT = 3000;
app.use(express.json({ limit: '1000mb' }));
// Устанавливаем папку frontend как статическую, чтобы обслуживать HTML, CSS и JS файлы
app.use(express.static(path.join(__dirname)));

// имитация внутреннего id карты
let sequence = 0
// имитация доп таблички в бд
store = {
    100: {
        name: "first test",
        meta: {
            start: 10,
            end: 20,
            step: 1
        }
    },
    2000: {
        name: "second test",
        meta: {
            start: 5,
            end: 10,
            step: 2
        }
    }
} // {mapId: {name: "название карты", meta: {start: int end: int, step: int}}}

function generatorID() {
    sequence += 1
    return sequence
}

// Маршрут для получения списка карт из папки maps
app.get('/ViewAllMaps', (req, res) => {
    let result = [];
    Object.keys(store).forEach(mapID => {
        let obj = {}
        obj[mapID] = store[mapID]['name']
        result.push(obj);
    });

    res.status(200).json(result);

//    fs.readdir(path.join(__dirname, 'maps'), (err, files) => {
//        if (err) {
//            return res.status(500).json({ error: 'Ошибка чтения каталога карт' });
//        }
//        const mapFiles = files.filter(file => file.endsWith('.json')).map(file => ({
//            id: file,
//            name: path.parse(file).name
//        }));
//        res.json(mapFiles);
//    });
});

// Маршрут для получения содержимого карты по названию файла
app.get('/getMeta/:mapId', (req, res) => {
        console.log("идентификатор карты", req.params.mapId)
        const meta = store[req.params.mapId] ? store[req.params.mapId]['meta'] : {}
        res.status(200).json({meta: meta});

//    const mapPath = path.join(__dirname, 'maps', req.params.mapId);
//    fs.readFile(mapPath, 'utf8', (err, data) => {
//        if (err) {
//            return res.status(404).json({ error: 'Карта не найдена' });
//        }
//        res.json(JSON.parse(data));
//    });
});

// Обработчик сохранения данных по карте
app.post('/createMap', (req, res) => {
    const mapData = req.body;
    console.log('Полученные данные:', mapData);  // Получаем данные карты из запроса
    const mapName = mapData.name || 'default_map';  // Название карты
    const filePath = path.join(__dirname, 'maps', `${mapName}.json`);  // Путь для сохранения файла

    const mapID = generatorID();
    store[mapID] = {
        name: mapData.name,
        meta: {
            start: mapData.meta.start,
            end: mapData.meta.end,
            step:mapData.meta.step
        }
    };
    console.log('Информация о карте с метой:', store);

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
