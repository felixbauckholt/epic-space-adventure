(ns app.draw2d
  (:import
    [org.lwjgl.opengl GL11]
    [app.java TextureLoader Texture]))

(def black [0 0 0 1])
(def white [1 1 1 1])
(def red   [1 0 0 1])
(def green [0 1 0 1])
(def blue  [0 0 1 1])
(def cyan  [0 1 1 1])
(def magenta [1 0 1 1])
(def yellow  [1 1 0 1])

(defn transparent [t [r g b a]] [r g b (* t a)])


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


(def tLoader (new TextureLoader))

(defn getImg [name] (.getTexture tLoader (str "assets/images/" name)))

(defn sprite [t [x y] a]
  (setCol (transparent a white))
  (GL11/glEnable GL11/GL_TEXTURE_2D)
  (.bind t)
  (GL11/glBegin GL11/GL_QUADS)
  (let [w (.getImageWidth t)
        h (.getImageHeight t)
        w_t (.getWidth t)
        h_t (.getHeight t)
        mults [[0 0] [0 1] [1 1] [1 0]]]
    (doseq [[mx my] mults]
      (GL11/glTexCoord2f (* mx w_t) (* my h_t))
      (goto [(+ x (* mx w)) (+ y (* my h))])))
  (GL11/glEnd)
  (GL11/glDisable GL11/GL_TEXTURE_2D))

(defn texPoly [t [ox oy] a & points]
  (setCol (transparent a white))
  (GL11/glEnable GL11/GL_TEXTURE_2D)
  (.bind t)
  (GL11/glBegin GL11/GL_TRIANGLE_FAN)
  (let [wmul (/ (.getWidth t) (.getImageWidth t))
        hmul (/ (.getHeight t) (.getImageHeight t))]
    (doseq [[x y] points]
      (let [[dx dy] [(- x ox) (- y oy)]
            [tx ty] [(* wmul dx) (* hmul dy)]]
        (GL11/glTexCoord2f tx ty))
      (goto [x y])))
  (GL11/glEnd)
  (GL11/glDisable GL11/GL_TEXTURE_2D))
