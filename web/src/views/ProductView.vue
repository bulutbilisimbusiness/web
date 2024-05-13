<template>
	<ProductListComponent />
</template>
<script>
	import ProductListComponent from "@/components/ProductListComponent.vue";

	export default {
		// eslint-disable-next-line vue/multi-word-component-names
		name: "ProductView",
		components: { ProductListComponent },
	};
</script>
