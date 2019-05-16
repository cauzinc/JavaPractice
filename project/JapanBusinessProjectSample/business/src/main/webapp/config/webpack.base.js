'use strict'
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const webpack = require('webpack');

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
                // 使用oneOf来同时应用module和scoped规则, 有些相同的Options也要在各自的Loader中进行配置
                // 使代码易读，可以参照vue-cli把loader options提取到别的文件中实现复用
                oneOf: [
                    {
                        resourceQuery: /module/,
                        use: ['vue-style-loader',
                            {
                                loader: 'css-loader',
                                options: {
                                    modules: true,
                                    localIdentName: '[local]_[hash:base64:5]'
                                }
                            },'sass-loader','postcss-loader', {
                                loader: 'sass-resources-loader',
                                options: {
                                    resources: path.resolve(__dirname, './../src/assets/css/variables.scss')
                                }
                            }]
                    },
                    {
                        use: ['vue-style-loader', 'css-loader', 'sass-loader', 'postcss-loader', {
                            loader: 'sass-resources-loader',
                            options: {
                                resources: path.resolve(__dirname, './../src/assets/css/variables.scss')
                            }
                        }]
                    }

                ],

            }
        ]

    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'index.html',
            inject: true
        }),
        new webpack.ProvidePlugin({
            Controller: [path.join(__dirname, '../src/store/Service'), 'default']
        })
    ]

};