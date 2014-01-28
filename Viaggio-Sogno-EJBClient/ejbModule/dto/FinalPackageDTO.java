package dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class FinalPackageDTO {

	private int id;

	@NotEmpty
	private PackageDTO originalPackage;

	private List<ProductDTO> flights;
	private List<ProductDTO> hotels;
	private List<ProductDTO> excursions;

	private List<FinalFlightDTO> finalFlights;
	private List<FinalHotelDTO> finalHotels;
	private List<FinalExcursionDTO> finalExcursions;

	public List<ProductDTO> getFlights() {
		return flights;
	}

	public void setFlights(List<ProductDTO> flights) {
		this.flights = flights;
	}

	public List<ProductDTO> getHotels() {
		return hotels;
	}

	public void setHotels(List<ProductDTO> hotels) {
		this.hotels = hotels;
	}

	public List<ProductDTO> getExcursions() {
		return excursions;
	}

	public void setExcursions(List<ProductDTO> excursions) {
		this.excursions = excursions;
	}


	public List<FinalFlightDTO> getFinalFlights() {
		return finalFlights;
	}

	public void setFinalFlights(List<FinalFlightDTO> finalFlights) {
		this.finalFlights = finalFlights;
	}

	public List<FinalHotelDTO> getFinalHotels() {
		return finalHotels;
	}

	public void setFinalHotels(List<FinalHotelDTO> finalHotels) {
		this.finalHotels = finalHotels;
	}

	public List<FinalExcursionDTO> getFinalExcursions() {
		return finalExcursions;
	}

	public void setFinalExcursions(List<FinalExcursionDTO> finalExcursions) {
		this.finalExcursions = finalExcursions;
	}

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
		switch (product.getType()) {
		case "FLIGHT":
			flights.remove(product);
			break;
		case "HOTEL":
			hotels.remove(product);
			break;
		case "EXCURSION":
			excursions.remove(product);
			break;
		}

	}
	
	public void removeFinalProduct(FinalProductDTO finalProduct) {

		switch (finalProduct.getProduct().getType()) {
		case "FLIGHT":
			finalFlights.remove(finalProduct);
			break;
		case "HOTEL":
			finalHotels.remove(finalProduct);
			break;
		case "EXCURSION":
			finalExcursions.remove(finalProduct);
			break;
		}
	}

}
