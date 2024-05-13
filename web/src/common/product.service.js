import { get } from "./api.service";

export function getProductDetailById(id) {
	return get("products/" + id);
}
export function getAll() {
	return get("products");
}
