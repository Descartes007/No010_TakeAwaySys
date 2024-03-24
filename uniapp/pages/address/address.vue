<template>
	<view style="padding: 20rpx;">
		<view style="margin-bottom: 110rpx;">
			<view v-for="item in addressList" :key="item.id" class="box" style="margin-bottom: 10rpx;" @click="selectAddress(item.id)">
				<view style="margin-bottom: 10rpx;">{{ item.address }}</view>
				<view style="color: #888; margin-bottom: 10rpx;">
					<text>{{ item.user }}</text>
					<text style="margin-left: 20rpx;">{{ item.phone }}</text>
				</view>
				<view style="text-align: right;">
					<uni-icons type="compose" size="16" @click="handleEdit(item.id)"></uni-icons>
					<uni-icons type="trash" size="16" style="margin-left: 20rpx;" @click="del(item.id)"></uni-icons>
				</view>
			</view>
		</view>
		
		<navigator url="/pages/addAddress/addAddress">
			<button type="primary" style="background-color: dodgerblue; border-color: dodgerblue;
				position: fixed; bottom: 10rpx; width: calc(100% - 40rpx)">新增收货地址</button>
		</navigator>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: uni.getStorageSync('xm-user'),
				addressList: []
			}
		},
		onShow(option) {
			this.load()
		},
		methods: {
			selectAddress(addressId) {
				let xmOrders = uni.getStorageSync('xm-orders') || {}
				xmOrders.addressId = addressId
				uni.setStorageSync('xm-orders', xmOrders)
				uni.navigateBack()
			},
			handleEdit(addressId) {
				uni.navigateTo({
					url: '/pages/addAddress/addAddress?addressId=' + addressId
				})
			},
			del(addressId) {
				this.$request.del('/address/delete/' + addressId).then(res => {
					if (res.code === '200') {
					  uni.showToast({
					    icon: "success",
					    title: '操作成功'
					  })
					  this.load()
					} else {
					  uni.showToast({
					    icon: "error",
					    title: res.msg
					  })
					}
				})
			},
			load() {
				this.$request.get('/address/selectAll', { userId: this.user.id }).then(res => {
					this.addressList = res.data || []
				})
			}
		}
	}
</script>

<style>

</style>
