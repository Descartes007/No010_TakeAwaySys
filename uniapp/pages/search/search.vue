<template>
	<view style="padding: 20rpx;">
		<view>
			<view>
				<view class="box" v-for="item in businessList" :key="item.id" style="margin-bottom: 20rpx;">
					
					<view style="display: flex; grid-gap: 30rpx; margin-bottom: 10rpx;"  @click="goToDeatil(item.id)">
						<view style="width: 30%;">
							<image :src="item.avatar" style="width: 100%; height: 200rpx; border-radius: 10rpx; display: block;"></image>
						</view>
						<view style="flex: 1; display: flex; flex-direction: column; justify-content: space-between; grid-gap: 10rpx;">
							<view style="font-size: 36rpx; font-weight: bold;">{{ item.name }}</view>
									
							<view style="display: flex;  color: #666; ">
								<view style="flex: 1;">
									<text style="color: #ff9800; font-weight: bold;">{{ item.score }}分</text>
									<text style="margin-left: 10rpx;">已售{{ item.nums }}</text>
								</view>
								<view style="flex: 1; text-align: right;">30分钟内送达</text>
								</view>
							</view>
							
							<view style="color: #ff9800;">免配送费</view>
							
							<view style="background-color: #ffd281; color: brown; border-radius: 4rpx; width: fit-content;
								padding: 0 8rpx;">{{ item.info || '商家介绍' }}</view>
						</view>
					</view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: '',
				businessList: []
			}
		},
		onLoad(option) {
			this.name = option.name
			this.load()
		},
		methods: {
			goToDeatil(businessId) {
				uni.navigateTo({
					url: '/pages/detail/detail?businessId=' + businessId
				})
			},
			load() {
				// 获取商家的列表
				this.$request.get('/business/selectAll', { name: this.name, status: '通过'  }).then(res => {
					this.businessList = res.data || []
				})
			}
		}
	}
</script>

<style>

</style>
