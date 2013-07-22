(ns app.core
  (:use app.draw2d app.mainloop))

(def glass)
(def sword)

(defrecord MainState [time]
  MainloopState
  
  (update [state dt]
    (new MainState (+ (:time state) dt)))
  
  (draw [state]
    (let [wobble (/ (+ 1 (Math/sin (:time state))) 2)
          texcoord (Math/floor (* 20 (:time state)))]
      (poly red [400 0] [0 400] [0 0])
      (rect cyan [200 200] [400 400])
      (texPoly glass [texcoord texcoord] 1 [400 400] [100 300] [50 150] [500 100])
      (circle (transparent wobble yellow) [100 100] 100)
      (sprite sword [100 100] (- 1 wobble))))
  )

(defn load []
  (def glass (getImg "glass.png"))
  (def sword (getImg "sworddiamond.png"))
  (new MainState 0))

(defn -main []
  (mainloop [[640 480] "EpischesFester"] load))
