package es.urjc.code.ejem1;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.ApplicationEventPublisher;

public class ApplicationEventPublisherParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	    throws ParameterResolutionException {
	// TODO Auto-generated method stub
	return (parameterContext.getParameter().getType() == ApplicationEventPublisher.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	    throws ParameterResolutionException {
	// TODO Auto-generated method stub
	return new ApplicationEventPublisher() {
	    
	    @Override
	    public void publishEvent(Object event) {
		// TODO Auto-generated method stub
		
	    }
	};
    }

    
}
