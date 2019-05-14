'use strict'
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const path = require('path')

function resolve(dir) {
    return path.join(__dirname, '..', dir)
}

module.exports = {
    context: path.resolve(__dirname, '../'),
    entry: "./src/main.js",
    output: {
        path: path.resolve(__dirname, './../dist'),
        filename: "[name].js"
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            "@": path.resolve(__dirname, '../')
        }
    },
    module: {
        rules: [
            {
                test: /\.(vue)$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                // include: [resolve('src'), resolve('test'), resolve('node_modules/webpack-dev-server/client')]
            },
            {
                test: /\.(css|postcss|scss|sass)$/,
                use: ['vue-style-loader', {
                    loader: 'css-loader', options: { importLoaders: 1 }
                }, 'sass-loader', 'postcss-loader', {
                    loader: 'sass-resources-loader',
                    options: {
                        resources: path.resolve(__dirname, './../src/assets/css/variables.scss')
                    }
                }]
            }
        ]

    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'index.html',
            inject: true
        })
    ]

};