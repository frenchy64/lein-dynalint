# lein-dynalint

A Leiningen plugin for [Dynalint](https://github.com/frenchy64/dynalint).

WARNING: Currently `lein-dynalint` only lints calls to Clojure
functions from within Leiningen, not from code running in your
project.  See the [Dynalint](https://github.com/frenchy64/dynalint)
page for how to lint your code.  See [this
issue](https://github.com/frenchy64/lein-dynalint/issues/2) if you are
interested in finding a way to improve `lein-dynalint` so it lints
your code.


## Quickstart

Run `lein dynalint`.

## Installation

Use this for user-level plugins:

Put `[lein-dynalint "0.1.4"]` into the `:plugins` vector of your
`:user` profile, or if you are on Leiningen 1.x do `lein plugin install
lein-dynalint 0.1.4`.

Use this for project-level plugins:

Put `[lein-dynalint "0.1.4"]` into the `:plugins` vector of your project.clj.

A specific version of [Dynalint](https://github.com/frenchy64/dynalint) 
must be declared as a dependency in the respective user- or project-level
`:plugins` vector.

## Usage

Currently the only command is

```
lein dynalint test
```

This runs your unit tests with the linter loaded.

If an `:output` keyword argument is given, the verbose warnings are dumped to
the give file name under `target/dynalint-outout`. `:output` defaults to `output`.

```
lein dynalint test :output my-output
```

## License

Copyright © 2014 Ambrose

Distributed under the Eclipse Public License, the same as Clojure.
