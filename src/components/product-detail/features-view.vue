<template>
	<div v-if="productFeatures">
		<div class="row">{{ productFeatures }}</div>
		<hr />
		<div class="row">info 2</div>
		<hr />
		<div class="row">info 3</div>
		<hr />
		<div class="row">info 4</div>
	</div>
</template>

<script>
	export default {
		name: "features-view",
		props: ["productFeatures"],
	};
</script>

<style scoped></style>
