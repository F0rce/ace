# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

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
