# dynamic-templates
Example for dynamic loading/unloading of templates.
Background: When using DukeScript in a modular system like OSGi, templates can be added as part of a module (OSGi bundle).
When the module is installed (bundle activator), the template can be registered. Templates are stored in html files.
To save resources, the html file is only loaded when the template is first used. 
It is also possible to unregister the template, e.g. when a module is uninstalled thus freeing up resources.

Use like this:

{% highlight java %}
TemplateRegistration.registerTemplate("a", "a.html");
TemplateRegistration.unRegisterTemplate("a");
{% endhighlight %}

Registering will add a new script tag with src pointng to an external html file. 
The template will then be lazy loaded when first used.
Subsequent attempts to register a template with the same id will be logged and ignored.

Unregistering will remove the script, which makes it eligible for gc.

