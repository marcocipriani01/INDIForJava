/*
 *  This file is part of INDI for Java Driver.
 * 
 *  INDI for Java Driver is free software: you can redistribute it
 *  and/or modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation, either version 3 of 
 *  the License, or (at your option) any later version.
 * 
 *  INDI for Java Driver is distributed in the hope that it will be
 *  useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 *  of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with INDI for Java Driver.  If not, see 
 *  <http://www.gnu.org/licenses/>.
 */
package org.indilib.i4j.driver;

import org.indilib.i4j.Constants.SwitchStatus;

/**
 * A class representing a pair of a
 * <code>INDISwitchElement</code> and a
 * <code>SwitchStatus</code>.
 *
 * @author S. Alonso (Zerjillo) [zerjioi at ugr.es]
 * @version 1.34, November 8, 2013
 */
public class INDISwitchElementAndValue extends INDIElementAndValue<INDISwitchElement,SwitchStatus> {

    public INDISwitchElementAndValue(INDISwitchElement element, SwitchStatus value) {
        super(element, value);
    }

}