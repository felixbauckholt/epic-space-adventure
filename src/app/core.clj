(ns app.core (:gen-class))

(.setLevel (java.util.logging.Logger/getLogger "")  (java.util.logging.Level/WARNING))

(let [logger (java.util.logging.Logger/getLogger "debug")
      level  (java.util.logging.Level/WARNING)]
  (defn log [msg] (.log logger level (str msg))))

(defn -main []
  (.start (proxy [com.jme3.app.SimpleApplication] []
    ; Callbacks hier:
    (simpleInitApp []
      (let [box (new com.jme3.scene.shape.Box com.jme3.math.Vector3f/ZERO 1 1 1)
            geo (new com.jme3.scene.Geometry "Box" box)
            mat (new com.jme3.material.Material (.getAssetManager this) "Common/MatDefs/Misc/Unshaded.j3md")]
        (do
          (.setColor mat "Color" com.jme3.math.ColorRGBA/Blue)
          (.setMaterial geo mat)
          (.attachChild (.getRootNode this) geo)
          (log "OMG eine blaue Kiste!!!")
        )))
  )))
