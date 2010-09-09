/**
 * Copyright (C) 2010 Damien GOUYETTE <damien.gouyette@gmail.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cestpasdur.converter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter
public class WikiHtmlConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) throws ConverterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) throws ConverterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
