package com.bamcoreport.web.api.identity.helpers;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.HashSet;
import java.util.Set;

public class HelpUpdate {
    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src)); // src: nv objet target : objet BD
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {


            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null ) emptyNames.add(pd.getName());

            //System.out.println(pd.getName()+" : "+srcValue+" -- "+pd.getReadMethod().getName());

        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}
