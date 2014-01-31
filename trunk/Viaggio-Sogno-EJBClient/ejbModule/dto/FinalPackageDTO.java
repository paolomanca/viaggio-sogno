package dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class FinalPackageDTO {

	private int id;
	
	private String sharedID;
	
	private boolean finalized = false;
	
	private boolean paid = false;
	
	private boolean shared = false;
	
	private boolean reserved = false;

	private int participants = 1;
	
	private int totalCost;

	private UserDTO user;
	
	@NotEmpty
	private PackageDTO originalPackage;

	private List<ProductDTO> products;
	private List<FinalProductDTO> finalProducts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PackageDTO getOriginalPackage() {
		return originalPackage;
	}

	public void setOriginalPackage(PackageDTO originalPackage) {
		this.originalPackage = originalPackage;
	}

	public String getName() {
		return originalPackage.getName();
	}

	public void removeProduct(ProductDTO product) {
		products.remove(product);
	}
	
	public void removeFinalProduct(FinalProductDTO finalProduct) {
		finalProducts.remove(finalProduct);
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public List<FinalProductDTO> getFinalProducts() {
		return finalProducts;
	}

	public void setFinalProducts(List<FinalProductDTO> finalProducts) {
		this.finalProducts = finalProducts;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalPrice) {
		this.totalCost = totalPrice;
	}

	public String getSharedID() {
		return sharedID;
	}

	public void setSharedID(String sharedID) {
		this.sharedID = sharedID;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

}
