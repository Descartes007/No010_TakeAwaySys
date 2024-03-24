import apiConfig from '@/config.js'
const baseUrl = process.env.NODE_ENV === "development" ? apiConfig.dev.baseUrl : apiConfig.prod.baseUrl

const request = (options = {}) => {
    return new Promise((resolve, reject) => {
        uni.request({
            url: baseUrl + options.url || '',
            method: options.method || 'GET',
            data: options.data || {},
            header: options.header || {"Content-Type": "application/json", token: uni.getStorageSync('xm-user')?.token}      
        }).then(res => {
			let { data } = res
			if (data.code === '401') {
				uni.navigateTo({
					url: '/pages/login/login'
				})
			}
            resolve(data);
        }).catch(error => {
            reject(error)
        })
    });
}

const get = (url, data, options = {}) => {
    options.method = 'GET'
    options.data = data
    options.url = url
    return request(options)
}

const post = (url, data, options = {}) => {
    options.method = 'POST'
    options.data = data
    options.url = url
    return request(options)
}

const put = (url, data, options = {}) => {
    options.method = 'PUT'
    options.data = data
    options.url = url
    return request(options)
}

const del = (url, data, options = {}) => {
    options.method = 'DELETE'
    options.data = data
    options.url = url
    return request(options)
}

export default  {
    request,
    get,
    post,
	put,
	del
}