(ns app.core)

(.setLevel (java.util.logging.Logger/getLogger "")  (java.util.logging.Level/WARNING))

(let [logger (java.util.logging.Logger/getLogger "debug")
      level  (java.util.logging.Level/WARNING)]
  (defn log [msg] (.log logger level (str msg))))

(defn -main []
  (.start (proxy [com.jme3.app.SimpleApplication] []
    ; Callbacks hier:
    (simpleInitApp [] (print "init!"))
  )))
