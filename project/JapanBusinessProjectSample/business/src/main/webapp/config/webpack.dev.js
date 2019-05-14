const merge = require('webpack-merge')
const path = require('path')
const baseWebpack = require('./webpack.base.js')
const CopyWebpackPlugin = require('copy-webpack-plugin')


let devConfig = merge(baseWebpack, {
    mode: "development",
    devtool: 'cheap-modules-eval-source-map',
    devServer: {
        clientLogLevel: 'warning',
        // The option is to deal with our static assets. Now we set it to false, since we use CopyWebpackPlugin.
        contentBase: false,
        hot: true,
        host: "localhost",
        port: 8080,
        open: false,    // not open browser automatically
        proxy: {}
    },
    plugins: [
        new CopyWebpackPlugin([
            {
                from: path.resolve(__dirname, '../static'),
                to: 'static'
            }
        ])
    ]
});
module.exports = devConfig;
