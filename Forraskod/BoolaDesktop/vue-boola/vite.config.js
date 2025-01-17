import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


// https://vitejs.dev/config/
export default defineConfig({
  server:{
    proxy:{
      "/api":{
        target:"http://localhost8080",
        changeOrigin:true,
        secure:false,
        rewrite: (path)=> path.replace(/^\/api/,"",)
      },

    },
cors:false,

    },
  plugins:[vue()],

  }


  )

