package laazotea.indi.driver.telescope;

import static laazotea.indi.Constants.PropertyStates.IDLE;

import java.util.Date;

import laazotea.indi.Constants.PropertyStates;
import laazotea.indi.INDIException;
import laazotea.indi.driver.INDIDriverExtention;
import laazotea.indi.driver.INDISwitchElement;
import laazotea.indi.driver.INDISwitchElementAndValue;
import laazotea.indi.driver.INDISwitchProperty;
import laazotea.indi.driver.annotation.InjectElement;
import laazotea.indi.driver.annotation.InjectProperty;
import laazotea.indi.driver.event.SwitchEvent;

public class INDITelescopeParkExtention extends INDIDriverExtention<INDITelescope> {

    public INDITelescopeParkExtention(INDITelescope telecopeDriver) {
        super(telecopeDriver);
        park.setEventHandler(new SwitchEvent() {

            @Override
            public void processNewValue(Date date, INDISwitchElementAndValue[] elementsAndValues) {
                driver.park();
            }
        });
    }

    @InjectProperty(name = "TELESCOPE_PARK", label = "Park", group = INDITelescope.MAIN_CONTROL_TAB)
    private INDISwitchProperty park;

    @InjectElement(name = "PARK", label = "Park")
    private INDISwitchElement parkElement;

    @Override
    public boolean isActive() {
        return driver.canPark();
    }

    @Override
    public void connect() {
        if (!isActive()) {
            return;
        }
        driver.addProperty(park);
    }

    @Override
    public void disconnect() {
        if (!isActive()) {
            return;
        }
        driver.removeProperty(park);
    }

    public void setIdle() {
        if (!isActive()) {
            return;
        }
        this.park.setState(IDLE);
        park.resetAllSwitches();
        try {
            driver.updateProperty(this.park);
        } catch (INDIException e) {
        }
    }

    public void setNotBussy() {
        if (!isActive()) {
            return;
        }
        if (this.park.getState() == PropertyStates.BUSY) {
            this.park.setState(IDLE);
        }

    }

    public boolean isBusy() {
        if (!isActive()) {
            return false;
        }
        return park.getState() == PropertyStates.BUSY;
    }

}