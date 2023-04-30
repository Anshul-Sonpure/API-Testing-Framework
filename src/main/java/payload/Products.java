package payload;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Products {

	public static String name;
	public static String cost;
	public static String quantity;
	public static String locationId;
	public static String DealerId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Products.name = name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		Products.cost = cost;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		Products.quantity = quantity;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		Products.locationId = locationId;
	}

	public String getDealerId() {
		return DealerId;
	}

	public void setDealerId(String DealerId) {
		Products.DealerId = DealerId;
	}

}
