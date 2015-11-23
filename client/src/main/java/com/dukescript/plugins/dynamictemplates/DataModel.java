package com.dukescript.plugins.dynamictemplates;

import com.dukescript.plugins.dynamictemplates.js.TemplateRegistration;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.Models;
import net.java.html.json.Property;

@Model(className = "Data", targetId = "", properties = {
    @Property(name = "template", type = String.class),
    @Property(name = "content", type = String.class)
})
final class DataModel {

    private static Data ui;
    private static Closeable a;
    private static Closeable b;
    
    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new Data();
        Models.toRaw(ui);
        a = TemplateRegistration.registerTemplate("a", "a.html");
        ui.setTemplate("a");
        ui.setContent("This is Content!");
        ui.applyBindings();
    }

    @Function
    public static void registerB(Data model) {
       b = TemplateRegistration.registerTemplate("b", "b.html");
    }
    @Function
    public static void registerA(Data model) {
        a = TemplateRegistration.registerTemplate("a", "a.html");
    }
    
    @Function
    public static void registerA1(Data model) {
        a = TemplateRegistration.registerTemplate("a", "a1.html");
    }
    
    @Function
    public static void unRegisterB(Data model) {
        if (b != null ) try {
            b.close();
        } catch (IOException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Function
    public static void unRegisterA(Data model) {
        if (a != null ) try {
            a.close();
        } catch (IOException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Function
    public static void setA(Data model) {
        model.setTemplate("a");
    }

    @Function
    public static void setB(Data model) {
        model.setTemplate("b");
    }
}
