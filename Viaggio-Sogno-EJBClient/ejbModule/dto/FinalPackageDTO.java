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
	
	public void removeFlight(ProductDTO flight) {
		this.flights.remove(flight);
	}

	public List<ProductDTO> getHotels() {
		return hotels;
	}

	public void setHotels(List<ProductDTO> hotels) {
		this.hotels = hotels;
	}
	
	public void removeHotel(ProductDTO hotel) {
		this.hotels.remove(hotel);
	}

	public List<ProductDTO> getExcursions() {
		return excursions;
	}

	public void setExcursions(List<ProductDTO> excursions) {
		this.excursions = excursions;
	}
	
	public void removeExcursion(ProductDTO excursion) {
		this.excursions.remove(excursion);
	}

	public List<FinalFlightDTO> getFinalFlights() {
		return finalFlights;
	}

	public void setFinalFlights(List<FinalFlightDTO> finalFlights) {
		this.finalFlights = finalFlights;
	}
	
	public void removeFinalProduct(FinalFlightDTO flight) {
		this.finalFlights.remove(flight);
	}

	public List<FinalHotelDTO> getFinalHotels() {
		return finalHotels;
	}

	public void setFinalHotels(List<FinalHotelDTO> finalHotels) {
		this.finalHotels = finalHotels;
	}
	
	public void removeFinalProduct(FinalHotelDTO hotel) {
		this.finalHotels.remove(hotel);
	}

	public List<FinalExcursionDTO> getFinalExcursions() {
		return finalExcursions;
	}

	public void setFinalExcursions(List<FinalExcursionDTO> finalExcursions) {
		this.finalExcursions = finalExcursions;
	}
	
	public void removeFinalProduct(FinalExcursionDTO excursion) {
		this.finalExcursions.remove(excursion);
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

	public String getName(){
		return originalPackage.getName();
	}

}
