const path = require('path');
const url = 'http://34.64.174.201:8090';

module.exports = {
	outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
	devServer: {
		proxy: {
			'/api':{
				target: url,
				ws: true,
				changeOrigin: true
			},
			'/upload':{
				target: url,
				ws: true,
				changeOrigin: true
			},
		}
	}
}