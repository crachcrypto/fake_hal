# FakeHAL Licensing Server v1.0.0

REST API сервер лицензирования и командного центра для FakeHAL.

## Запуск
```bash
bash /root/fakehal_server/start.sh
```

## API

### Публичный
- `GET /api/status` — статус сервера

### Авторизация
- `POST /api/auth` — проверка ключа, получение JWT токена
  ```json
  {"license_key": "TEST-KEY-001", "device_id": "android_id_here"}
  ```

### Команды (требует Bearer токен)
- `POST /api/command` — отправить команду устройству
  ```json
  {"device_id": "...", "command": "start_hal", "params": {}}
  ```
- `GET /api/commands` — получить и забрать очередь команд (pull-модель)

### Управление ключами (требует X-Admin-Token)
- `POST /api/key/create` — создать ключ
- `GET /api/keys` — список ключей
- `DELETE /api/key/{key}` — деактивировать ключ

## Конфиги
- Admin token: `fakehal_admin_2026_secret` (менять в config.py)
- Port: `7070`
- Keys store: `keys.json`
- Commands queue: `commands.json`

## Команды HAL
| command | params | описание |
|---|---|---|
| `start_hal` | `{}` | запустить fake_camera_provider |
| `stop_hal` | `{}` | остановить |
| `update_config` | `{"noise_level":"0.5","gyro_enabled":"1"}` | обновить fakehal.conf |
| `push_video` | `{"slot":"a","url":"http://..."}` | загрузить видео |
| `restart_hal` | `{}` | перезапустить |
