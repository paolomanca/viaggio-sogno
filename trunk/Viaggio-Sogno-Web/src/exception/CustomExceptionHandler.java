package exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
	 
	  private ExceptionHandler wrapped;
	 
	  public CustomExceptionHandler(ExceptionHandler wrapped) {
	    this.wrapped = wrapped;
	  }
	 
	  @Override
	  public ExceptionHandler getWrapped() {
	    return wrapped;
	  }
	 
	  @Override
	  public void handle() throws FacesException {
	    //Iterate over all unhandled exceptions
	    Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
	    while (i.hasNext()) {
	      i.next();
	      final FacesContext fc = FacesContext.getCurrentInstance();
	      //here you do what ever you want with exception
	      try{
	    	  //redirect error page
	    	  fc.getExternalContext().invalidateSession();
	    	  String url =fc.getExternalContext().getRequestContextPath()+"/Error.xhtml";
	    	  System.out.println("Redirecting to: "+url);
	    	  fc.getExternalContext().redirect(url);
	      } catch (Exception e) {		
			e.printStackTrace();
		}finally{
	        //after exception is handled, remove it from queue
	        i.remove();
	      }
	    }
	    //let the parent handle the rest
	    getWrapped().handle();
	  }
}

