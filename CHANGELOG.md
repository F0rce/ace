# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [3.4.1](https://github.com/f0rce/ace/compare/v3.4.0...v3.4.1) (2022-08-29)


### Bug Fixes

* add missing "meta.tag" ([6007aba](https://github.com/f0rce/ace/commit/6007abadb2b1b900b7e11014d12c479bf1ee4487))

## [3.4.0](https://github.com/f0rce/ace/compare/v3.3.3...v3.4.0) (2022-08-26)


### ⚠ BREAKING CHANGES

* autocompletion could not be called consecutively

### Features

* add gson as dependency to improve serialization ([8fa25a5](https://github.com/f0rce/ace/commit/8fa25a573573efe307e8a5ab072d3d7bec7dd48b))
* **modes:** create & set custom modes during runtime ([0d28156](https://github.com/f0rce/ace/commit/0d28156ece02047e5773c10c4ab9d2f815661a86))


### Bug Fixes

* autocompletion could not be called consecutively ([a4aed41](https://github.com/f0rce/ace/commit/a4aed4182488c99a48c9ab3a21643fccb7e3a54e)), closes [#36](https://github.com/F0rce/ace/issues/36)

## [3.3.3](https://github.com/f0rce/ace/compare/v3.3.2...v3.3.3) (2022-08-03)


### Features

* **themes:** add lobster_dark ([838ae0c](https://github.com/f0rce/ace/commit/838ae0cd7134f49c49ee62f396934d93c3741541))
* **themes:** add lobster_light ([a0be74c](https://github.com/f0rce/ace/commit/a0be74cbb54cbfbb145b4efd446ccbcca193321e))

## [3.3.2](https://github.com/f0rce/ace/compare/v3.3.1...v3.3.2) (2022-07-29)


### Bug Fixes

* **modes:** improve lobster_srv_logs ([5d640c4](https://github.com/f0rce/ace/commit/5d640c4bd67721b1d6d582da6dd44d9a56b6512f))

## [3.3.1](https://github.com/f0rce/ace/compare/v3.3.0...v3.3.1) (2022-07-28)


### Features

* **modes:** add lobster_srv_logs ([3934091](https://github.com/f0rce/ace/commit/39340917605763f120220a62d954273a5f2d1d41))

## [3.3.0](https://github.com/f0rce/ace/compare/v3.2.0...v3.3.0) (2022-07-27)


### Features

* **modes:** add lobster_dc_logs ([bb29dcb](https://github.com/f0rce/ace/commit/bb29dcb135d3ea8f5c56ff6722256ba6eb17d026))

## [3.2.0](https://github.com/f0rce/ace/compare/v3.1.2...v3.2.0) (2022-07-20)


### Features

* **statusbar:** possibility to set the starting index ([272b8ee](https://github.com/f0rce/ace/commit/272b8ee5e48b59538fa813d4aea33ce405b23e50))

## [3.1.2](https://github.com/f0rce/ace/compare/v3.1.1...v3.1.2) (2022-06-28)


### Features

* **modes:** added new mode ([5f3e1f8](https://github.com/f0rce/ace/commit/5f3e1f89f176f75eb16dff8d44d0910671189d82))
* **themes:** added new themes ([be089b5](https://github.com/f0rce/ace/commit/be089b51a20ae7a3e010511f18c907d7ef4e3b92))

## [3.1.1](https://github.com/f0rce/ace/compare/v3.1.0...v3.1.1) (2022-06-21)


### Bug Fixes

* **autocompletion:** reverted hack as it was fixed in upstream ([0f46838](https://github.com/f0rce/ace/commit/0f46838cd04e61c2875d0577a0e44194d4ce1d99))

## [3.1.0](https://github.com/f0rce/ace/compare/v3.0.1...v3.1.0) (2022-06-07)


### ⚠ BREAKING CHANGES

* changed method parameter for `generateHTML()`

### Features

* added method to open a print dialog with ace's current value ([d858b3e](https://github.com/f0rce/ace/commit/d858b3e9d12563b7ebedd8ffa4025e673c867983))
* **modes:** added `x12` highlighter (wip) ([e272efe](https://github.com/f0rce/ace/commit/e272efe383b0462045965bea8748f9d91fd014ea))
* updated modes & themes ([f3bc436](https://github.com/f0rce/ace/commit/f3bc436b97d2eea8276d02250716be62d963a035))


### Bug Fixes

* autocompletion under the editor ([74b9dc4](https://github.com/f0rce/ace/commit/74b9dc4b6622b0c2e5c69e433e92c2e18b51d24a))
* changed method parameter for `generateHTML()` ([9eec2b5](https://github.com/f0rce/ace/commit/9eec2b5603efea7a907c2bdf16335b39225bfb87))

## [3.0.1](https://github.com/F0rce/ace/compare/v3.0.0...v3.0.1) (2022-04-12)


### Bug Fixes

* **lobster_logs:** removed highlighting of every digit ([005bef6](https://github.com/F0rce/ace/commit/005bef6242c6c1f50164f212b4143f56474fdddc))
* **lobster_records:** removed highlighting of every digit ([3f636e3](https://github.com/F0rce/ace/commit/3f636e32ba63cc90fd9fb91bdde6fb805f4f5827))

## [3.0.0](https://github.com/F0rce/ace/compare/v2.1.0...v3.0.0) (2022-03-25)


### ⚠ BREAKING CHANGES

* **java:** ace (3.0) requires Java 11
* **vaadin:** ace (3.0) supports Vaadin 23.0

### Features

* **java:** ace (3.0) requires Java 11 ([29b2f2f](https://github.com/F0rce/ace/commit/29b2f2ffc6e14eb8414f197208f25ad35736a5c0))
* **vaadin:** ace (3.0) supports Vaadin 23.0 ([3b353c4](https://github.com/F0rce/ace/commit/3b353c4d12fa2d9ed017c6f3d32d1cc3507a4edd))

## [2.1.0](https://github.com/F0rce/ace/compare/v2.0.1...v2.1.0) (2022-03-24)


### ⚠ BREAKING CHANGES

* **customAutocompletion:** now uses List<String> instead of String Array

### Features

* **cursorPosition:** additional convenience methods ([21b316e](https://github.com/F0rce/ace/commit/21b316e6c9b727759e20a0fe1a1f8e2ce31a7902))
* **customAutocompletion:** now uses List<String> instead of String Array ([70cb03b](https://github.com/F0rce/ace/commit/70cb03b54b56d6eb34abc08897c7a7029da3e7bc))
* **selection:** additional convenience methods ([e2a3fac](https://github.com/F0rce/ace/commit/e2a3facf74f41b2aadc4795c7ccbdf51933e0837))


### Bug Fixes

* improved attach/detach logic ([0432964](https://github.com/F0rce/ace/commit/04329642facb7c7e0df361206a0dd23a71675aa3))

## [2.0.1](https://github.com/F0rce/ace/compare/v2.0.0...v2.0.1) (2022-03-16)


### Bug Fixes

* missing value after reattaching same instance ([c394f49](https://github.com/F0rce/ace/commit/c394f49e4404e17d8c1c4a6f09fb7de4fac8eb3e))

## 2.0.0 (2022-03-15)


### ⚠ BREAKING CHANGES

* AceEditor extends now from Component

### Features

* added AceReady as Event ([f549839](https://github.com/f0rce/ace/commits/f54983916f11acc4ff3b6e634fa2a3644465cc3e))
* added possibility to add a dynamic autocompletion ([774df2b](https://github.com/f0rce/ace/commits/774df2b498d9aef02a1499a0b04933654f2c4f73)), closes [#13](https://github.com/F0rce/ace/issues/13)
* added working valueChangeListener ([e2a2ef7](https://github.com/f0rce/ace/commits/e2a2ef7908ff11e802a06ca656ab305fea51eefe))
* change style of #editor ([72795b3](https://github.com/f0rce/ace/commits/72795b3071e99f98c72607974db18a893aafe7f2))
* convenience methods ([9b6a23d](https://github.com/f0rce/ace/commits/9b6a23d8656a271cd6725faeef14c9307fe5495f))
* **modes:** adding lobster_logs & lobster_records ([d651b3d](https://github.com/f0rce/ace/commits/d651b3df4422333558e5fac2a3b0a4862767b116))
* using new AceSelection and AceCursorPosition Classes ([40de358](https://github.com/f0rce/ace/commits/40de3581af32739d457e24df3b01b98e3115117e))


### Bug Fixes

* AceEditor extends now from Component ([a98c1d3](https://github.com/f0rce/ace/commits/a98c1d360d9065f6b56108fb6cf52cbaf9891916))
* removing close to all racing conditions ([774df2b](https://github.com/f0rce/ace/commits/774df2b498d9aef02a1499a0b04933654f2c4f73))
