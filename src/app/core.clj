(ns app.core
  (:use app.draw2d app.mainloop))

(def glass)
(def sword)

(defn load []
  (def glass (getImg "glass.png"))
  (def sword (getImg "sworddiamond.png"))
  0)

(defn update [state dt]
  (+ state dt))

(defn draw [state]
  (let [wobble (/ (+ 1 (Math/sin state)) 2)]
    (poly red [400 0] [0 400] [0 0])
    (rect cyan [200 200] [400 400])
    (texPoly glass [(Math/floor (* 20 state)) (Math/floor (* 30 state))] 1 [400 400] [100 300] [50 150] [500 100])
    (circle (transparent wobble yellow) [100 100] 100)
    (sprite sword [100 100] (- 1 wobble))))

(defn -main []
  (mainloop [[640 480] "EpischesFester"] load update draw))
