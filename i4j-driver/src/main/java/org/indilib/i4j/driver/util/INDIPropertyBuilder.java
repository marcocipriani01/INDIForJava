package org.indilib.i4j.driver.util;

/*
 * #%L
 * INDI for Java Driver Library
 * %%
 * Copyright (C) 2012 - 2014 indiforjava
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.indilib.i4j.Constants.PropertyPermissions;
import org.indilib.i4j.Constants.PropertyStates;
import org.indilib.i4j.Constants.SwitchRules;
import org.indilib.i4j.driver.INDIDriver;
import org.indilib.i4j.driver.INDIProperty;
import org.indilib.i4j.driver.annotation.InjectProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder utility to build a property.
 * 
 * @param <PropertyClass>
 *            property class to build.
 * @author Richard van Nieuwenhoven
 */
public class INDIPropertyBuilder<PropertyClass extends INDIProperty<?>> {

    /**
     * Logger to log to.
     */
    private static final Logger LOG = LoggerFactory.getLogger(INDIPropertyBuilder.class);

    /**
     * The Driver to which this property is associated.
     */
    private INDIDriver driver;

    /**
     * class to build.
     */
    private final Class<PropertyClass> propertyClazz;

    /**
     * the permissions for the property, defaults to RW.
     */
    private PropertyPermissions permission = PropertyPermissions.RW;

    /**
     * the timeout for the property defaults to 60.
     */
    private int timeout = InjectProperty.ONE_MINUTE_SECONDS;

    /**
     * name of the property (mandatory).
     */
    private String name = "";

    /**
     * label of the property (mandatory).
     */
    private String label = "";

    /**
     * the tab group to use for this property (mandatory if it is not in a
     * group).
     */
    private String group = "";

    /**
     * the initial state of the property.
     */
    private PropertyStates state = PropertyStates.IDLE;

    /**
     * should the value of this property be saved in a property file? defaults
     * to false.
     */
    private boolean saveable = false;

    /**
     * if this property is a switch property what rule should apply? defaults to
     * ONE_OF_MANY.
     */
    private SwitchRules switchRule = SwitchRules.ONE_OF_MANY;

    /**
     * default constructor of the property builder.
     * 
     * @param propertyClazz
     *            the property class to build.
     */
    public INDIPropertyBuilder(Class<PropertyClass> propertyClazz) {
        this.propertyClazz = propertyClazz;
    }

    /**
     * injection constructor of the property builder.
     * 
     * @param propertyClazz
     *            the property class to build.
     * @param injectProperty
     *            the injection property to copy the settings from.
     */
    public INDIPropertyBuilder(Class<PropertyClass> propertyClazz, InjectProperty injectProperty) {
        this(propertyClazz);
        this.group(injectProperty.group());
        this.label(injectProperty.label());
        this.name(injectProperty.name());
        this.permission(injectProperty.permission());
        this.saveable(injectProperty.saveable());
        this.state(injectProperty.state());
        this.switchRule(injectProperty.switchRule());
        this.timeout(injectProperty.timeout());
    }

    /**
     * @return The Driver to which this property is associated.
     */
    public INDIDriver driver() {
        return driver;
    }

    /**
     * @return the permissions for the property, defaults to RW.
     */
    public PropertyPermissions permission() {
        return permission;
    }

    /**
     * @return the timeout for the property defaults to 60.
     */
    public int timeout() {
        return timeout;
    }

    /**
     * @return name of the property (mandatory).
     */
    public String name() {
        return name;
    }

    /**
     * @return label of the property (mandatory).
     */
    public String label() {
        return label;
    }

    /**
     * @return the initial state of the property.
     */
    public PropertyStates state() {
        return state;
    }

    /**
     * @return the tab group to use for this property (mandatory if it is not in
     *         a group).
     */
    public String group() {
        return group;
    }

    /**
     * @return should the value of this property be saved in a property file?
     *         defaults to false.
     */
    public boolean saveable() {
        return saveable;
    }

    /**
     * @return if this property is a switch property what rule should apply?
     *         defaults to ONE_OF_MANY.
     */
    public SwitchRules switchRule() {
        return switchRule;
    }

    /**
     * set the permissions for the property, defaults to RW.
     * 
     * @param permissionValue
     *            the new permission value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> permission(PropertyPermissions permissionValue) {
        if (permissionValue != null) {
            permission = permissionValue;
        }
        return this;
    }

    /**
     * set the timeout for the property defaults to 60.
     * 
     * @param timeoutValue
     *            the new timeout value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> timeout(int timeoutValue) {
        timeout = Math.max(0, timeoutValue);
        return this;
    }

    /**
     * set name of the property (mandatory).
     * 
     * @param nameValue
     *            the new name value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> name(String nameValue) {
        if (nameValue != null) {
            name = nameValue.trim();
        }
        return this;
    }

    /**
     * set label of the property (mandatory).
     * 
     * @param labelValue
     *            the new label value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> label(String labelValue) {
        if (labelValue != null) {
            label = labelValue.trim();
        }
        return this;
    }

    /**
     * set the initial state of the property.
     * 
     * @param stateValue
     *            the new state value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> state(PropertyStates stateValue) {
        if (stateValue != null) {
            state = stateValue;
        }
        return this;
    }

    /**
     * set the tab group to use for this property (mandatory if it is not in a
     * group).
     * 
     * @param groupValue
     *            the new group value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> group(String groupValue) {
        if (groupValue != null) {
            group = groupValue;
        }
        return this;
    }

    /**
     * set should the value of this property be saved in a property file?
     * defaults to false.
     * 
     * @param saveableValue
     *            the new saveable value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> saveable(boolean saveableValue) {
        saveable = saveableValue;
        return this;
    }

    /**
     * set if this property is a switch property what rule should apply?
     * defaults to ONE_OF_MANY.
     * 
     * @param switchRuleValue
     *            the new switchRule value.
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> switchRule(SwitchRules switchRuleValue) {
        if (switchRuleValue != null) {
            switchRule = switchRuleValue;
        }
        return this;
    }

    /**
     * set the driver property.
     * 
     * @param driverValue
     *            the new driver value
     * @return the builder itself.
     */
    public INDIPropertyBuilder<PropertyClass> driver(INDIDriver driverValue) {
        this.driver = driverValue;
        return this;
    }

    /**
     * @return the instanciated property filled with all collected settings.
     */
    public PropertyClass create() {
        try {
            return propertyClazz.getConstructor(INDIPropertyBuilder.class).newInstance(this);
        } catch (Exception e) {
            LOG.error("could not instanciate property", e);
            throw new IllegalArgumentException(e);
        }
    }

}