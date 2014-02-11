(ns leiningen.dynalint
  (:require 
    [leiningen.test]
    [clojure.repl :as repl]
    [leiningen.core.eval :refer [eval-in-project]]))

(defn help []
  "Lint your Clojure programs as they run.")

(defn dynalint-help []
  (println "lein-dynalint: Lint your Clojure programs as they run")
  (println "Usage: ")
  (println 
"  lein dynalint test - Run all tests with linter enabled
                        Options:
                          :output   File to output verbose warnings and errors under
                                    target/dynalint-output. Defaults to 'output'
                                    eg. lein dynalint test :output my-file")
  (println 
"  lein dynalint help - Show help")
  (flush))

(defn dynalint-test [project args]
  (let [_ (assert (even? (count args)) "Must provide even number of arguments to lein dynalint test")
        opt (apply hash-map args)]
    ;load linter
    (require 'clojure.java.io)
    (when-not (clojure.java.io/resource "dynalint/lint.clj")
      (throw (Exception. "Missing dependency: com.ambrosebs/dynalint. See lein-dynalint README")))
    (try
      (require 'dynalint.lint)
      (catch Exception e
        (throw (RuntimeException. "Missing dependency: com.ambrosebs/dynalint. See lein-dynalint README" e))))
    (let [v (find-var 'dynalint.lint/lint)]
      (assert v "lein-dynalint cannot load Dynalint")
      (@v))
    (try
      (leiningen.test/test project)
      (finally
        (let [fname (get opt ":output" "output")]
          (let [v (find-var 'dynalint.lint/warning-history)
                fname (str "target/dynalint-output/" fname)]
            (assert v "lein-dynalint cannot load Dynalint")
            (.mkdirs (clojure.java.io/file fname))
            (when (clojure.java.io/resource fname)
              (clojure.java.io/delete-file fname))
            (doseq [e (->> @@v
                           vals
                           (sort-by (comp :dynalint.lint/id ex-data)))]
              (spit fname
                    (with-out-str
                      (binding [*err* *out*]
                        (repl/pst e 200)
                        (prn)))
                    :append true))
            (println "Output Dynalint results to " fname)
            (flush)))))))

(defn dynalint 
  [project & [mode & args]]
  (case mode
    "test" (dynalint-test project args)
    (dynalint-help)))
