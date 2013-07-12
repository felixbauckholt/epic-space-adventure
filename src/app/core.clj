(ns app.core)

(defn -main []
  (.start (proxy [com.jme3.app.SimpleApplication] []
    ; Callbacks hier:
    (simpleInitApp [] (print "init!"))
  )))
