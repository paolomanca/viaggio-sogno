package control;

public interface DTOBuilder<T,S> {
	
	public S buildDTO(T in);

}
