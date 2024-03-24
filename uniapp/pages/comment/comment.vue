<template>
	<view style="padding: 20rpx;">
		<view class="box">
			<uni-forms :modelValue="form" :rules="rules" ref="formRef" label-width="140rpx" label-align="right">
			  <uni-forms-item label="内容" name="content" required>
			    <uni-easyinput type="textarea" v-model="form.content" placeholder="请输入内容" />
			  </uni-forms-item>
			  <uni-forms-item label="评分" name="star" required>
			    <uni-rate v-model="form.star" style="position: relative; top: 10rpx;" />
			  </uni-forms-item>
			
			  <uni-forms-item>
			    <button type="primary" size="mini" @click="save">保 存</button>
			  </uni-forms-item>
			</uni-forms>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {},
				rules: {
				  content: {
				    rules: [{
				      required: true,
				      errorMessage: '请填写内容',
				    }]
				  },
				  star: {
				    rules: [{
				      required: true,
				      errorMessage: '请评分',
				    }]
				  }
				}
			}
		},
		onLoad(option) {
			this.form.orderId = option.orderId
		},
		methods: {
			save() {
				// 验证
				this.$refs.formRef.validate().then(res => {
				  this.$request.post('/comment/add', this.form).then(res => {
				    if (res.code === '200') {
				      uni.showToast({
				        title: '操作成功'
				      })
					  uni.navigateBack()
				    } else {
				      uni.showToast({
				        icon: 'error',
				        title: res.msg
				      })
				    }
				  })
				}).catch(err => {
				  console.log('err', err);
				})   
			}
		}
	}
</script>

<style>

</style>
