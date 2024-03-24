<template>
	<view style="padding: 20rpx;">
		<view class="box" v-if="commentList.length">
			<view v-for="item in commentList" :key="item.id" style="border-bottom: 2rpx solid #eee; padding: 20rpx 0;">
				<navigator :url="'/pages/detail/detail?businessId=' + item.businessId" 
					style="font-size: 34rpx; display: inline-block; margin-bottom: 10rpx;">
					{{ item.businessName }} <uni-icons type="right" size="18" color="#888" style="position: relative; top: 2rpx;"></uni-icons>
				</navigator>
				
				<view style="display: flex; grid-gap: 20rpx;">
					<view style="display: flex; flex-direction: column; align-items: center; grid-gap: 10rpx;">
						<image :src="item.userAvatar" style="width: 120rpx; border-radius: 50%;" mode="widthFix"></image>
						<view>{{ item.userName }}</view>
					</view>
					<view style="flex: 1; display: flex; flex-direction: column; justify-content: space-between;">
						<view style="flex: 1; padding-top: 10rpx; font-size: 24rpx;">{{ item.content }}</view>
						<uni-rate :value="item.star" readonly></uni-rate>
					</view>
					<view style="font-size: 24rpx; color: #888; display: flex; align-items: flex-end;">{{ item.time }}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				commentList: [],
				user: uni.getStorageSync('xm-user')
			}
		},
		onLoad() {
			this.load()
		},
		methods: {
			load() {
				this.$request.get('/comment/selectAll', { userId: this.user.id }).then(res => {
					this.commentList = res.data || []
				})
			}
		}
	}
</script>

<style>

</style>
