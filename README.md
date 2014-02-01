# lein-dynalint

A Leiningen plugin for Dynalint.

## Quickstart

Run `lein dynalint`.

## Installation

Use this for user-level plugins:

Put `[lein-dynalint "0.1.2"]` into the `:plugins` vector of your
`:user` profile, or if you are on Leiningen 1.x do `lein plugin install
lein-typed 0.3.1`.

Use this for project-level plugins:

Put `[lein-dynalint "0.1.2"]` into the `:plugins` vector of your project.clj.


## Usage

Currently the only command is

```
lein dynalint test
```

This runs your unit tests with the linter loaded.

If an `:output` keyword argument is given, the verbose warnings are dumped to
the give file name.

```
lein dynalint test :output my-output
```

## License

Copyright © 2014 Ambrose

Distributed under the Eclipse Public License, the same as Clojure.
