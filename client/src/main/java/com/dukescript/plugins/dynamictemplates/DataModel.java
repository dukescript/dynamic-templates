package com.dukescript.plugins.dynamictemplates;

import com.dukescript.plugins.dynamictemplates.js.TemplateRegistration;
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

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new Data();
        Models.toRaw(ui);
        TemplateRegistration.register("a", "a.html");
        ui.setTemplate("a");
        ui.setContent("This is Content!");
        ui.applyBindings();
    }

    @Function
    public static void registerB(Data model) {
        TemplateRegistration.registerTemplate("b", "b.html");
    }
    @Function
    public static void registerA(Data model) {
        TemplateRegistration.registerTemplate("a", "a.html");
    }
    @Function
    public static void registerA1(Data model) {
        TemplateRegistration.registerTemplate("a", "a1.html");
    }
    @Function
    public static void unRegisterB(Data model) {
        TemplateRegistration.unRegisterTemplate("b");
    }
    @Function
    public static void unRegisterA(Data model) {
        TemplateRegistration.unRegisterTemplate("a");
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
