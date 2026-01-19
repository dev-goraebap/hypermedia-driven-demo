import tailwindcss from '@tailwindcss/vite';
import { resolve } from 'path';
import { defineConfig } from 'vite';

export default defineConfig({
  plugins: [tailwindcss()],
  build: {
    outDir: '../resources/vite',
    emptyOutDir: true,
    rollupOptions: {
      input: {
        'app.main': resolve(__dirname, 'src/app.main.js'),
        style: resolve(__dirname, 'src/style.css'),
      },
      output: {
        entryFileNames: 'builds/[name].js',
        assetFileNames: 'builds/[name].[ext]',
      },
    },
  },
});
