import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import ProductView from "../views/ProductView.vue";
import ProductDetail from "../views/ProductDetail.vue";
const routes = [
	{
		path: "/",
		name: "home",
		component: HomeView,
	},
	{
		path: "/product",
		name: "product",
		component: ProductView,
	},
	{
		path: "/detail/:id",
		name: "productdetail",
		component: ProductDetail,
	},
	{
		path: "/about",
		name: "about",
		component: AboutView,
	},
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
});

export default router;
