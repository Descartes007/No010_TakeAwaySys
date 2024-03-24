<template>
	<view style="padding: 20rpx;">
		<view class="box" style="display: flex; align-items: center; margin-bottom: 20rpx;">
			<image :src="user.avatar" style="width: 150rpx; border-radius: 50%;" mode="widthFix"></image>
			<view style="flex: 1; margin-left: 30rpx;">
				<view style="margin-bottom: 20rpx; font-size: 32rpx;">{{ user.username }}</view>
				<view style="color: #888;">{{ user.name }}</view>
			</view>
		</view>
		
		<view class="box" style="margin-bottom: 20rpx;"> 
			<view style="font-size: 36rpx; font-weight: bold; margin-bottom: 20rpx;">我的功能</view>
			<view style="display: flex;">
				<navigator url="/pages/address/address" class="me-item">
					<image src="@/static/icons/地址.png" style="width: 50%;" mode="widthFix"></image>
					<view>收货地址</view>
				</navigator>
				<navigator url="/pages/collect/collect" class="me-item">
					<image src="@/static/icons/收藏.png" style="width: 50%;" mode="widthFix"></image>
					<view>我的收藏</view>
				</navigator>
				<navigator url="/pages/commentCenter/commentCenter" class="me-item">
					<image src="@/static/icons/评价.png" style="width: 50%;" mode="widthFix"></image>
					<view>评价中心</view>
				</navigator>
				<navigator url="" class="me-item">
					<image src="@/static/icons/查看.png" style="width: 50%;" mode="widthFix"></image>
					<view>浏览历史</view>
				</navigator>
			</view>
		</view>
		
		<view class="box">
			<navigator url="/pages/person/person" style="padding: 15rpx; border-bottom: 2rpx solid #eee;">
				<uni-icons type="person" size="18"></uni-icons>
				<text style="margin-left: 10rpx;">个人信息</text>
				<uni-icons type="right" color="#999" style="float: right;"></uni-icons>
			</navigator>
			<navigator url="/pages/aboutUs/aboutUs" style="padding: 15rpx; border-bottom: 2rpx solid #eee;">
				<uni-icons type="help" size="18"></uni-icons>
				<text style="margin-left: 10rpx;">关于我们</text>
				<uni-icons type="right" color="#999" style="float: right;"></uni-icons>
			</navigator>
			<navigator url="/pages/agreement/agreement" style="padding: 15rpx; border-bottom: 2rpx solid #eee;">
				<uni-icons type="info" size="18"></uni-icons>
				<text style="margin-left: 10rpx;">用户协议</text>
				<uni-icons type="right" color="#999" style="float: right;"></uni-icons>
			</navigator>
			<view style="padding: 15rpx; border-bottom: 2rpx solid #eee;" @click="logout()">
				<uni-icons type="undo" size="18"></uni-icons>
				<text style="margin-left: 10rpx;">退出登录</text>
				<uni-icons type="right" color="#999" style="float: right;"></uni-icons>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: uni.getStorageSync('xm-user')
			}
		},
		onShow() {
			this.user = uni.getStorageSync('xm-user')
		},
		methods: {
			logout() {
				uni.removeStorageSync('xm-user')
				uni.redirectTo({
					url: '/pages/login/login'
				})
			}
		}
	}
</script>

<style>
.me-item {
	flex: 1; 
	display: flex; 
	flex-direction: column; 
	align-items: center;
	grid-gap: 10rpx;
}
</style>
