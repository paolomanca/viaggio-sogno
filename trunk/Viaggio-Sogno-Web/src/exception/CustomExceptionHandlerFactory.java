package exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	 
	  private ExceptionHandlerFactory parent;
	 
	  // this injection handles jsf
	  public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	    this.parent = parent;
	  }
	 
	  //create your own ExceptionHandler
	  @Override
	  public ExceptionHandler getExceptionHandler() {
	    ExceptionHandler result =
	        new CustomExceptionHandler(parent.getExceptionHandler());
	    return result;
	  }

}
