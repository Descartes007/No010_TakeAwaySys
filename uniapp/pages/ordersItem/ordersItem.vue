<template>
	<view style="padding: 20rpx;">
		<view class="box" style="margin-bottom: 20rpx;">
			<view style="margin-bottom: 10rpx;">订单编号：{{ orders.orderNo }}</view>
			<view style="font-size: 24rpx; color: #888;">下单时间：{{ orders.time }}</view>
		</view>
		<!-- 商品和金额信息 -->
		<view class="box" style="margin: 20rpx 0;">
			<navigator :url="'/pages/detail/detail?businessId=' + orders.businessId" style="margin-bottom: 20rpx; color: #666; 
				font-size: 36rpx;">{{ orders.businessName }}</navigator>
			
			<view>
				<view style="display: flex; grid-gap: 20rpx; margin-bottom: 20rpx;" v-for="item in ordersItemList" :key="item.id">
					<view style="width: 140rpx; height: 140rpx;">
						<image :src="item.goodsImg" style="width: 100%; height: 100%; display: block; border-radius: 10rpx;"></image>
					</view>
					<view style="flex: 1; display: flex; flex-direction: column; justify-content: space-between;">
						<view>{{ item.goodsName }}</view>
						<view style="color: #888;">x {{ item.num }}</view>
					</view>
					<view style="width: 150rpx; text-align: right; color: red; padding-top: 10rpx;">
						￥{{ item.price }}
					</view>
				</view>
			</view>
			<!-- 显示优惠和总金额信息 -->
			<view v-if="orders.discount">
				<view style="text-align: right;">
					<text>已优惠</text>
					<text style="color: red;">￥{{ orders.discount }}</text>
					<text style="margin-left: 20rpx;">小计</text>
					<text style="color: red; font-size: 36rpx; font-weight: bold;">￥{{ orders.actual }}</text>
				</view>
			</view>
		
			<view style="flex: 1; text-align: right; margin-top: 20rpx;">
				<uni-tag v-if="orders.status === '待支付'" text="支付" size="mini" type="primary"
					@click="changeStatus(item, '待发货')"></uni-tag>
				<uni-tag v-if="orders.status === '待发货'" text="申请退款" size="mini" type="error"
					@click="changeStatus(item, '已退款')"></uni-tag>
				<uni-tag v-if="orders.status === '待收货'" text="确认收货" size="mini" type="warning"
					@click="changeStatus(item, '待评价')"></uni-tag>
				<uni-tag v-if="orders.status === '待评价'" text="评价" size="mini" type="royal"></uni-tag>
			</view>
		</view>
		<!-- 商品和金额信息 -->		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				orders: {},
				ordersItemList: [],
				orderId: 0
			}
		},
		onLoad(option) {
			this.orderId = option.orderId
			this.load(this.orderId)
		},
		methods: {
			changeStatus(orders, status) {
				let form = JSON.parse(JSON.stringify(orders))
				form.status = status
				this.$request.put('/orders/update', form).then(res => {
					if (res.code === '200') {
						uni.showToast({
							icon: "success",
							title: '操作成功'
						})
						this.load(this.orderId)
					} else {
						uni.showToast({
							icon: "error",
							title: res.msg
						})
					}
				})
			},
			load(orderId) {
				this.$request.get('/orders/selectById/' + orderId).then(res => {
					this.orders = res.data || {}
				})
				
				this.$request.get('/ordersItem/selectAll', { orderId: orderId }).then(res => {
					this.ordersItemList = res.data || []
				})
			}
		}
	}
</script>

<style>

</style>
