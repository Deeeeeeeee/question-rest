package com.seal_de.common;

import com.seal_de.domain.UserInfo;
import org.junit.Test;

import javax.persistence.Id;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sealde on 5/16/17.
 */
public class ReflectTest {
    @Test
    public void reflect() throws InvocationTargetException, IllegalAccessException {
        UserInfo userInfo = createUser();
        System.out.println(getId(userInfo));
    }

    @Test
    public void isNewTest() {
        UserInfo userInfo = createUser();
        if(isNew(userInfo)) {
            System.out.println("new");
        } else {
            System.out.println("not new");
        }
    }

    private <E> boolean isNew(E element) {
        boolean isNew = false;
        try {
            Object id = getId(element);
            isNew = id == null ? true : false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return isNew;
    }

    private <E> Object getId(E element) throws InvocationTargetException, IllegalAccessException {
        Object id = null;
        Class clazz = element.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Id.class)) {
                id = method.invoke(element);
            }
        }
        return id;
    }

    private UserInfo createUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("12");
        userInfo.setUsername("jm3");
        userInfo.setPassword("qqq");
        userInfo.setRole(2);
        return userInfo;
    }
}
