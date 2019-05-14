const path = require('path')
const baseConfig = require('./webpack.base.js')
const merge = require('webpack-merge')
const basePath = 'static'

module.exports = merge(baseConfig, {
    //  production mode contains UglifyJsPlugin
    mode: "production",
    devtool: '#source-map',
    output: {
        path: path.resolve(__dirname, '../dist'),
        filename: path.join(basePath, 'js/[name].[chunkhash].js'),
        chunkFilename: path.join(basePath, 'js/[id].[chunkhash].js')
    }
})
