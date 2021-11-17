package es.urjc.code.ejem1;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.ApplicationEventPublisher;

import es.urjc.code.ejem1.controller.ShoppingCartController;

public class ShoppingCartControllerParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	    throws ParameterResolutionException {
	// TODO Auto-generated method stub
	return (parameterContext.getParameter().getType() == ShoppingCartController.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	    throws ParameterResolutionException {
	// TODO Auto-generated method stub
	return new ShoppingCartController();
    }

}
