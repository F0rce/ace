# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [2.2.1](https://github.com/f0rce/ace/compare/v2.2.0...v2.2.1) (2022-06-13)


### Bug Fixes

* **autocompletion:** reverted hack as it was fixed in upstream ([5283671](https://github.com/f0rce/ace/commit/5283671c7ae4170cd05f50f835dbcb0f9788c4ec))

## [2.2.0](https://github.com/f0rce/ace/compare/v2.1.1...v2.2.0) (2022-06-07)


### ⚠ BREAKING CHANGES

* changed method parameter for `generateHTML()`

### Features

* added method to open a print dialog with ace's current value ([060e921](https://github.com/f0rce/ace/commit/060e9211485f93919df5217940db6c4d7da9eac5))
* **modes:** added `x12` highlighter (wip) ([149eea9](https://github.com/f0rce/ace/commit/149eea931affe36110852185f7597a9577a3277b))
* updated modes & themes ([6239c5c](https://github.com/f0rce/ace/commit/6239c5c0f72d5add5c02f716835dd61bedd5bd2a))


### Bug Fixes

* autocompletion under the editor ([c9113a4](https://github.com/f0rce/ace/commit/c9113a40edb63045ceb938823b8109d7f08fb15f))
* changed method parameter for `generateHTML()` ([7a54ae5](https://github.com/f0rce/ace/commit/7a54ae528e872b82635ecb0c53f8ccaa4d0fc49f))

## [2.1.1](https://github.com/F0rce/ace/compare/v2.1.0...v2.1.1) (2022-04-12)


### Bug Fixes

* **lobster_logs:** removed highlighting of every digit ([2050c2c](https://github.com/F0rce/ace/commit/2050c2cd9d592e007c17ae624e31f06b0dbe0806))
* **lobster_records:** removed highlighting of every digit ([f2076e5](https://github.com/F0rce/ace/commit/f2076e517b4f62ddda9029fe5bbeb4a03b3d14c8))

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
