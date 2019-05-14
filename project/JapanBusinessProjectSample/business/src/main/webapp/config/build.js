const rm = require('rimraf');
const path = require('path');
const webpack = require('webpack');
const webpackConfig = require('./webpack.prod.js');

//  delete all files at first
//  we could do our work after packing
rm(path.resolve(__dirname, '../dist/static'), (err, stats) => {
    if(err) {
        throw err
    }
    webpack(webpackConfig, (err, stats) => {
        process.stdout.write(stats.toString({
            colors: true,
            modules: false,
            children: false, // If you are using ts-loader, setting this to true will make TypeScript errors show up during build.
            chunks: false,
            chunkModules: false
        }) + '\n\n')
    })

});