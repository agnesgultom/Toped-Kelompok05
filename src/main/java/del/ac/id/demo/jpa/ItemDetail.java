package del.ac.id.demo.jpa;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("item_detail")
public class ItemDetail {
	private double weight;
	private String condition;
	private String insurance;
	private String category;
	
	public ItemDetail() {}
	public ItemDetail(final double weight,
			final String condition,
			final String insurance,
			final String category) {
		this.weight = weight;
		this.condition = condition;
		this.insurance = insurance;
		this.category = category;
	}
	public void setWeight(double weight) { this.weight = weight;}
	public void setCondition(String condition) { this.condition=condition;}
	public void setInsurance(String insurance) { this.insurance = insurance;}
	public void setCategory(String category) { this.category = category;}
	
	public String getCategory() { return category; }
	public String getInsurance() { return insurance; }
	public double getWeight() { return weight; }
	public String getCondition() { return condition; }
	
}