(ns app.core
  (:import
    [org.lwjgl.opengl Display DisplayMode GL11]
    [org.lwjgl.util.glu GLU])
  (:use app.draw2d))

(defn tri []
  (GL11/glBegin GL11/GL_TRIANGLES)
    (GL11/glColor3f 1 0 0)
    (GL11/glVertex3f  0  1 0)
    (GL11/glColor3f 0 1 0)
    (GL11/glVertex3f -1 -1 0)
    (GL11/glColor3f 0 0 1)
    (GL11/glVertex3f 1  -1 0)
  (GL11/glEnd))

(defn draw []
  (GL11/glClear (or GL11/GL_COLOR_BUFFER_BIT GL11/GL_DEPTH_BUFFER_BIT))
  (GL11/glLoadIdentity)
  (poly red [400 0] [0 400] [0 0])
  (poly blue [300 400] [200 100] [100 500] [300 100])
  (Display/update))

(defn init []
  (Display/setDisplayMode (new DisplayMode 640 480))
  (Display/setTitle "LWJGL 2D")
  (Display/create)
    
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  (GL11/glOrtho 0 640 480 0 -1 1)
  (GL11/glMatrixMode GL11/GL_MODELVIEW))

(defn -main []
  (init)
  (loop [close? false]
    (if-not close? (do
      (draw)
      (Display/sync 30)
      (recur (Display/isCloseRequested))))))
