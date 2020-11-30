/*
 *  Copyright (C) 2011 Claudius Teodorescu
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  $Id$
 */
package ro.kuberam.libs.java.ftclient;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Implements a method for retrieving a remote resource.
 * 
 * Created by Claudius Teodorescu.
 */
public class RetrieveResource {
	private static final Logger log = LogManager.getLogger(RetrieveResource.class);
	private static final String moduleName = ExpathFTClientModule.MODULE_NAME;
    
    public static InputStream retrieveResource(Object remoteConnection, String remoteResourcePath) throws Exception {
    	InputStream result = null;
        String protocol = ExpathFTClientModule.PROTOCOL_CLASS_CODES.get(remoteConnection.getClass().getName());
       
        Class<?> clazz = Class.forName("ro.kuberam.libs.java.ftclient." + protocol + "." + protocol);
        Method method = clazz.getMethod("retrieveResource", new Class<?>[] {Object.class, String.class});
        try {
            result = (InputStream) method.invoke(clazz.newInstance(), new Object[] {remoteConnection, remoteResourcePath});
            log.info(moduleName + " retrieved the resource '" + remoteResourcePath + "'.");
        } catch(InvocationTargetException ex) {
            throw new Exception(ex.getCause().getMessage());
        }
        
        return result;
    }    
}