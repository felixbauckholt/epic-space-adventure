(def osname
  (.toLowerCase
    (.replaceAll
      (System/getProperty "os.name")
      "\\s" "")))

(defproject app "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/felixbauckholt/epic-space-adventure"
  :dependencies [
    [org.clojure/clojure "1.3.0"]
    [org.lwjgl.lwjgl/lwjgl "2.9.0"]
    [org.lwjgl.lwjgl/lwjgl_util "2.9.0"]]
  :jvm-opts [~(str "-Djava.library.path=native/" osname ":" (System/getProperty "java.library.path"))]
  :main app.core)
