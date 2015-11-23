package com.dukescript.plugins.dynamictemplates.js;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

@JavaScriptResource("template-loading.js")
public class TemplateRegistration {

    @JavaScriptBody(args = {"id", "template"}, body =  "var my_template = document.getElementById(id);\n"
            + "if ( my_template ) {\n"
            + "   console.log('Already registered a template for id '+id+ ', ignoring this request.');\n"
            + "   return;\n"
            + "}\n"
            + "my_template = document.createElement('script');\n"
            + "my_template.setAttribute('id', id);\n"
            + "my_template.type ='text/html';\n"
            + "document.body.appendChild(my_template);\n"
            + "my_template.src = template;\n")
    public static native void registerTemplate(String id, String template);

    @JavaScriptBody(args = {"id"}, body = "var my_template = document.getElementById(id);\n"
            + "if ( my_template ) {\n"
            + "document.body.removeChild(my_template);\n"
            + "}\n"
    )
    public static native void unRegisterTemplate(String id);
    
}
