(ns app.core
  (:import
    [org.lwjgl.opengl Display DisplayMode GL11]
    [org.lwjgl.util.glu GLU])
  (:use app.draw2d))

(def testimg)

(defn draw []
  (GL11/glClear (or GL11/GL_COLOR_BUFFER_BIT GL11/GL_DEPTH_BUFFER_BIT))
  (GL11/glLoadIdentity)
  (poly red [400 0] [0 400] [0 0])
  (rect cyan [200 200] [400 400])
  (poly (transparent 0.5 blue) [400 400] [100 300] [50 150] [500 100])
  (sprite testimg [0 0] 0.5)
  (circle (transparent 0.7 yellow) [100 100] 100)
  (Display/update))

(defn init []
  (Display/setDisplayMode (new DisplayMode 640 480))
  (Display/setTitle "LWJGL 2D")
  (Display/create)
  
  (GL11/glEnable GL11/GL_BLEND)
  (GL11/glBlendFunc GL11/GL_SRC_ALPHA GL11/GL_ONE_MINUS_SRC_ALPHA)
  
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  (GL11/glOrtho 0 640 480 0 -1 1)
  (GL11/glMatrixMode GL11/GL_MODELVIEW))

(defn -main []
  (init)
  (def testimg (getImg "test.jpg"))
  (println (.getWidth testimg))
  (loop [close? false]
    (if-not close? (do
      (draw)
      (Display/sync 30)
      (recur (Display/isCloseRequested))))))
