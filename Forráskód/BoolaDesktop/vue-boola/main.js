import { app, BrowserWindow } from 'electron/main'
import path from 'node:path'

function createWindow () {
    const win = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join("C:\\Users\\dsfds\\boola-zakar-otott-palvolgyi\\Forráskód\\BoolaWeb\\vue-boola\\", 'preload.js')
        }
    })

    win.loadFile('./dist/index.html')
}

app.whenReady().then(() => {
    createWindow()

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow()
        }
    })
})

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})