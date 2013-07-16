(ns app.draw2d
  (:import
    [org.lwjgl.opengl Display DisplayMode GL11]))

(defn setCol [[r g b a]]
  (GL11/glColor4f r g b a))

(defn goto [[x y]]
  (GL11/glVertex3f x y 0))

(defn shape [shape points]
  (GL11/glBegin shape)
  (doseq [point points]
    (goto point))
  (GL11/glEnd))

(defn poly [col & points]
  (setCol col)
  (shape GL11/GL_TRIANGLE_FAN points))

(defn rect [col [x1 y1] [x2 y2]]
  (setCol col)
  (shape GL11/GL_QUADS [[x1 y1] [x1 y2] [x2 y2] [x2 y1]]))

(defn circle [col [x y] r]
  (let [points (map (fn [a] [(+ x (* r (Math/sin a))) (+ y (* r (Math/cos a)))])
                    (range 0 (* 2 Math/PI) (/ Math/PI r)))]
    (setCol col)
    (shape GL11/GL_TRIANGLE_FAN points)))

(def black [0 0 0 1])
(def white [1 1 1 1])
(def red   [1 0 0 1])
(def green [0 1 0 1])
(def blue  [0 0 1 1])
(def cyan  [0 1 1 1])
(def magenta [1 0 1 1])
(def yellow  [1 1 0 1])

(defn transparent [t [r g b a]] [r g b (* t a)])
