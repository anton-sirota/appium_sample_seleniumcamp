package main.screens;

import framework.Settings;
import framework.Locator;
import framework.AppObject;
import io.appium.java_client.AppiumDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Base class from which all page classes should be derived.
 * <p>
 * It contains the code to initialize {$project}.pages, initialize AppObject's, and interact in various ways with the
 * page(s).
 */
public abstract class BasicPage {
    public static AppiumDriver basedriver;

    /** Constructor. */
    public BasicPage() {
        super();
       //basedriver = DriverManager.getDriver(); //pass your driver here
        initializeWebObjects(this);
    }

    /**
     * Initialization of all fields of type {@link AppObject} which are complemented with the annotation {@link Locator}.
     *
     * @param whichClass
     *                  Page class to parse.
     */
    public void initializeWebObjects(Object whichClass) {
        Class<?> incomingClass = whichClass.getClass();
        ArrayList<Field> fields = new ArrayList<>();

        Class<?> tempIncomingClass = incomingClass;
        do {
            fields.addAll(Arrays.asList(tempIncomingClass.getDeclaredFields()));
            tempIncomingClass = tempIncomingClass.getSuperclass();
        } while (tempIncomingClass != null);

        String errorDesc = " while initializing locators for WebObjects. Root cause:";
        try {
            for (Field field : fields)
                if (field.isAnnotationPresent(Locator.class)) {
                    Annotation annotation = field.getAnnotation(Locator.class);
                    Locator locatorAnnotation = (Locator) annotation;

                    field.setAccessible(true);

                    Class<?> dataMemberClass = Class.forName(field.getType().getName());
                    Class<?> parameterTypes[] = new Class[3];

                    String locator = null;
                    switch (Settings.platform) {
                        case ANDROID:
                            locator = locatorAnnotation.android();
                            break;
                        case IOS:
                            locator = locatorAnnotation.ios();
                            break;
                        default:
                            break;
                    }

                    parameterTypes[0] = AppiumDriver.class;
                    parameterTypes[1] = String.class;
                    parameterTypes[2] = String.class;
                    Constructor<?> constructor = dataMemberClass.getDeclaredConstructor(parameterTypes);

                    Object[] constructorArgList = new Object[3];

                    constructorArgList[0] = basedriver;
                    constructorArgList[1] = locator;
                    constructorArgList[2] = field.getName();
                    Object retobj = constructor.newInstance(constructorArgList);
                    field.set(whichClass, retobj);
                }
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException("Class not found" + errorDesc + exception, exception);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("An illegal argument was encountered" + errorDesc + exception, exception);
        } catch (InstantiationException exception) {
            throw new RuntimeException("Could not instantantiate object" + errorDesc + exception, exception);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException("Could not access data member" + errorDesc + exception, exception);
        } catch (InvocationTargetException exception) {
            throw new RuntimeException("Invocation error occured" + errorDesc + exception, exception);
        } catch (SecurityException exception) {
            throw new RuntimeException("Security error occured" + errorDesc + exception, exception);
        } catch (NoSuchMethodException exception) {
            throw new RuntimeException("Method specified not found" + errorDesc + exception, exception);
        }
    }

}
