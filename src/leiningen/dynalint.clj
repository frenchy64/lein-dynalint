(ns leiningen.dynalint
  (:require 
    [leiningen.test]
    [leiningen.core.eval :refer [eval-in-project]]))

(defn help []
  "Lint your Clojure programs as they run.")

(defn dynalint-help []
  (println "lein-dynalint: Lint your Clojure programs as they run")
  (println "Usage: ")
  (println 
"  lein dynalint test - Run all tests with linter enabled")
  (println 
"  lein dynalint help - Show help")
  (flush))

(defn dynalint-test [project args]
  (prn project)
  (do
    ;load linter
    (require 'dynalint.lint)
    (let [v (find-var 'dynalint.lint/lint)]
      (assert v "lein-dynalint cannot load Dynalint")
      (@v))
    (leiningen.test/test project)))

(defn dynalint 
  [project & [mode & args]]
  (case mode
    "test" (dynalint-test project args)
    (dynalint-help)))
