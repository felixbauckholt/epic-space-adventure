(ns app.core (:gen-class))

(def assetMgr (ref nil))

(.setLevel (java.util.logging.Logger/getLogger "")  (java.util.logging.Level/WARNING))

(let [logger (java.util.logging.Logger/getLogger "debug")
      level  (java.util.logging.Level/WARNING)]
  (defn log [msg] (.log logger level (str msg))))

(defn mkMat [col] (let [mat (new com.jme3.material.Material @assetMgr "Common/MatDefs/Light/Lighting.j3md")]
  (.setColor mat "Ambient" col)
  (.setColor mat "Diffuse" col)
  (.setColor mat "Specular" col)
  (.setFloat mat "Shininess" 5)
  mat))

(defn addShape [node shape col]
  (let [geo (new com.jme3.scene.Geometry "DeineMudda" shape)]
      (.setMaterial geo (mkMat col))
      (.attachChild node geo)
      geo))

(defn -main []
  (.start (proxy [com.jme3.app.SimpleApplication] []
    ; Callbacks hier:
    (simpleInitApp []
      (dosync (ref-set assetMgr (.getAssetManager this)))
      (let [node (.getRootNode this)
            sun (new com.jme3.light.DirectionalLight)
            blue com.jme3.math.ColorRGBA/Blue
            
            box (new com.jme3.scene.shape.Box (new com.jme3.math.Vector3f 2 2 2) 1 1 1)
            sphere (new com.jme3.scene.shape.Sphere 10 10 3)]
        
        (.setDirection sun (.normalizeLocal (new com.jme3.math.Vector3f 1 0 -2)))
        (.addLight node sun)
        
        (addShape node box blue)
        (addShape node sphere blue))))))
