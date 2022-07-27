[![Published on NPM](https://img.shields.io/npm/v/@f0rce/lit-ace)](https://www.npmjs.com/package/@f0rce/lit-ace)
[![Downloads](https://img.shields.io/npm/dt/@f0rce/lit-ace)](https://www.npmjs.com/package/@f0rce/lit-ace)
[![License](https://img.shields.io/github/license/f0rce/lit-ace)](https://github.com/F0rce/lit-ace/blob/master/LICENSE)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FF0rce%2Flit-ace.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FF0rce%2Flit-ace?ref=badge_shield)
[![CodeFactor](https://www.codefactor.io/repository/github/f0rce/lit-ace/badge)](https://www.codefactor.io/repository/github/f0rce/lit-ace)

# lit-ace #

Even <strong>more</strong> embeddable and lightweight code 
editor Custom Element - just one tag, and no JS 
needed to provide [Ace](http://ace.c9.io/) - The High Performance Code Editor

## Important Notice ##

This webcomponent has been built to fit and serve the needs of the Vaadin Add-on [Ace](https://vaadin.com/directory/component/ace) (which I created and actively maintain). 
When using this webcomponent in any other environment, please be carefull because it **only** has been tested in a Vaadin Add-on environment.

*Some methods just don't make sense when using in an non Vaadin environment as most functions depend on JSON input which I create in the Java/Vaadin backend.*


## Usage example

```html
  <lit-ace theme="terminal" mode="javascript" value="console.log('Have a great day :)')">
  </lit-ace>
```


## Install

Install the component using [npm](https://www.npmjs.com/package/@f0rce/lit-ace):

```sh
$ npm i @f0rce/lit-ace --save
```

Once installed, import it in your application:

```js
import "@f0rce/lit-ace/lit-ace.js";
```


## License

[MIT License](http://opensource.org/licenses/MIT)


[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FF0rce%2Flit-ace.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FF0rce%2Flit-ace?ref=badge_large)
