package main;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//{
//	  "id":"256", 
//	  "name":"Tea Set", 
//	  "price":93.72, 
//	  "image":"tea_set_42.jpg",
//	  "productDetails":{
//	    "parts":12, 
//	    "manufacturer":"Royal Worcester", 
//	    "collectable":true
//	  }, 
//	  "category":"utensils"
//}

@Document(collection = "product")
public class ProductEntity {
	private String id;
	private String name;
	private float price;
	private String image;
	private Map<String, Object> productDetails;
	private String category;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Map<String, Object> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Map<String, Object> productDetails) {
		this.productDetails = productDetails;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
