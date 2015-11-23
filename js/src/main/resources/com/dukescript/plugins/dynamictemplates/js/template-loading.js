ko.templateSources.domElement.prototype['text'] = function (/* valueToWrite */) {
    var tagNameLower = ko.utils.tagNameLower(this.domElement),
            elemContentsProperty = tagNameLower === 'script' ? 'text'
            : tagNameLower === 'textarea' ? 'value'
            : 'innerHTML';
    
    if (arguments.length == 0) {
        console.log("+++1");
        var el = this.domElement;
        console.log("el "+el);
        var val = el[elemContentsProperty];
        if (tagNameLower == 'script' && !val && el['src']) {
            console.log("+++2");
            val = ko.observable('Loading...<br/>');
            var xhr = new XMLHttpRequest();
            xhr.open('GET', this.domElement['src'], true);
            xhr.setRequestHeader('Content-Type', 'text/html; charset=utf-8');
            xhr.onreadystatechange = function () {
                if (xhr.readyState !== 4)
                    return;
                val(el[elemContentsProperty] = xhr.response || xhr.responseText);
                console.log("+++3");
            };
            xhr.onerror = function (e) {
                val(el[elemContentsProperty] = 'Cannot load: ' + e);
            }
            xhr.send();
        }
        return ko.utils.unwrapObservable(val);
    } else {
        console.log("---");
        var valueToWrite = arguments[0];
        if (elemContentsProperty === 'innerHTML')
            ko.utils.setHtml(this.domElement, valueToWrite);
        else
            this.domElement[elemContentsProperty] = valueToWrite;
    }
};
