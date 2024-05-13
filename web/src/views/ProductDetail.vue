<template>
	<div class="container shopping-cart">
		<div class="content">
			<h3>{{ productDetail.name }}</h3>
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="row">
						<div class="col-md-5">
							<image-carousel
								v-bind:images="productDetail.images"
							></image-carousel>
						</div>
						<div class="col-md-7">
							<features-view
								v-if="productDetail.features"
								v-bind:productFeatures="productDetail.features"
							></features-view>
						</div>
					</div>
					<hr />
				</div>
			</div>
			<div class="row">
				<description-view
					v-bind:description="productDetail.description"
				></description-view>
			</div>
			<div class="row">
				<comments-view v-bind:productId="productDetail.id"></comments-view>
			</div>
		</div>
	</div>
</template>

<script>
	import CommentsView from "../components/product-detail/comments-view.vue";
	import DescriptionView from "../components/product-detail/description-view.vue";
	import FeaturesView from "../components/product-detail/features-view.vue";
	import ImageCarousel from "../components/product-detail/image-carousel.vue";
	import { getProductDetailById } from "@/common/product.service";

	export default {
		components: { ImageCarousel, FeaturesView, DescriptionView, CommentsView },
		name: "ProductDetail",

		data() {
			return {
				productDetail: {},
			};
		},
		mounted() {
			getProductDetailById(this.$route.params.id).then((response) => {
				console.log(response.data); // Debugging line
				this.productDetail = response.data;
			});
		},
	};
</script>

<style scoped></style>
