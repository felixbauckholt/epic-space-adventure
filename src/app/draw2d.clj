(ns app.draw2d
  (:import
    [org.lwjgl.opengl Display DisplayMode GL11]))

(defn setCol [[r g b a]]
  (GL11/glColor3f r g b))

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

(def black [0 0 0 1])
(def white [1 1 1 1])
(def red   [1 0 0 1])
(def green [0 1 0 1])
(def blue  [0 0 1 1])
