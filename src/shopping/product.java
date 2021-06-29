package shopping;

public class product implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public product() {
		name = "";
		price = 0;
		quantity = 0;
		setImg_name("");
		product_no = 0;
	}

	private String name;
	private Integer price;
	private Integer quantity;
	private String img_name;
	private Integer product_no;
	private Integer product_discount_no;
	private Integer product_discount_detail_buy_count;
	private Integer product_discount_detail_get_count;
	private Double product_discount_detail_buy_times_get;
	private String product_discount_detail_coupon;

	public String getProduct_discount_detail_coupon() {
		return product_discount_detail_coupon;
	}
	public void setProduct_discount_detail_coupon(String product_discount_detail_coupon) {
		this.product_discount_detail_coupon = product_discount_detail_coupon;
	}
	public Integer getProduct_discount_no() {
		return product_discount_no;
	}
	public void setProduct_discount_no(Integer product_discount_no) {
		this.product_discount_no = product_discount_no;
	}
	public Double getProduct_discount_detail_buy_times_get() {
		return product_discount_detail_buy_times_get;
	}
	public void setProduct_discount_detail_buy_times_get(Double product_discount_detail_buy_times_get) {
		this.product_discount_detail_buy_times_get = product_discount_detail_buy_times_get;
	}
	public Integer getProduct_discount_detail_buy_count() {
		return product_discount_detail_buy_count;
	}
	public void setProduct_discount_detail_buy_count(Integer product_discount_detail_buy_count) {
		this.product_discount_detail_buy_count = product_discount_detail_buy_count;
	}
	public Integer getProduct_discount_detail_get_count() {
		return product_discount_detail_get_count;
	}
	public void setProduct_discount_detail_get_count(Integer product_discount_detail_get_count) {
		this.product_discount_detail_get_count = product_discount_detail_get_count;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	@Override
	public String toString() {
		return "product [name=" + name + ", price=" + price + ", quantity=" + quantity + ","
				+ " img_name=" + img_name + ", product_no" + product_no + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		product other = (product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}