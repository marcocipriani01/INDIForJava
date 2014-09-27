package laazotea.indi.driver.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import laazotea.indi.Constants.PropertyPermissions;
import laazotea.indi.Constants.PropertyStates;

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD})     
public @interface INDIp {

    PropertyPermissions permission() default PropertyPermissions.RW;

    int timeout() default 60;

    String name() default "";

    String label() default "";

    PropertyStates state() default PropertyStates.IDLE;

    String group() default "";

}