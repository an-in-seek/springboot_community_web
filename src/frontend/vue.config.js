const path = require('path');

module.exports = {
	outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
	devServer: {
		proxy: {
			'/api':{
				//target: 'http://34.64.173.60:8090',
				target: 'http://localhost:8090',
				ws: true,
				changeOrigin: true
			},
		}
	}
}