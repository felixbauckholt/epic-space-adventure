(ns app.mainloop
  (:import
    [org.lwjgl.opengl Display DisplayMode GL11]
    [org.lwjgl Sys]))


(defn init [[x y] title]
  (Display/setDisplayMode (new DisplayMode x y))
  (Display/setTitle title)
  (Display/create)
  
  (GL11/glEnable GL11/GL_BLEND)
  (GL11/glBlendFunc GL11/GL_SRC_ALPHA GL11/GL_ONE_MINUS_SRC_ALPHA)
  
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  (GL11/glOrtho 0 x y 0 -1 1)
  (GL11/glMatrixMode GL11/GL_MODELVIEW))

(defprotocol MainloopState
  "Hier sollte man den Spielzustand speichern"
  (update [ms dt])
  (draw [ms]))

(defn mainloop [configuration load]
  (apply init configuration)
  (loop [oldstate (load)
         oldtime (Sys/getTime)
         close? false]
    (if-not close? (do
      (GL11/glClear (or GL11/GL_COLOR_BUFFER_BIT GL11/GL_DEPTH_BUFFER_BIT))
      (GL11/glLoadIdentity)
      (draw oldstate)
      (Display/update)
      (Display/sync 30)
      (let [newtime (Sys/getTime)
            dt (/ (- newtime oldtime) 1000)
            newstate (update oldstate dt)]
        (recur newstate newtime (Display/isCloseRequested)))))))
