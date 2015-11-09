(ns badges.core
  (:require [badges.handler :refer [app init destroy]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :as reload]
            [taoensso.timbre :as timbre]
            [environ.core :refer [env]])
  (:gen-class))

;; Define a server placeholder as an atom, effectively making it a singleton
;; and treating the server as a component that can be swiched on and off.
(defonce server (atom nil))

;; The port number is set from an environment variable, ie. if running on Heroku,
;; or the port number is set to a default value, 3000, ie. when running locally.
(defn parse-port [[port]]
  (Integer/parseInt (or port (env :port) "3000")))


(defn start-server [port]
  (init)
  (reset! server
          (run-jetty
            (if (env :dev) (reload/wrap-reload #'app) app)
            {:port port
             :join? false})))


(defn stop-server []
  (when @server
    (destroy)
    (.stop @server)
    (reset! server nil)))


(defn start-app [args]
  (let [port (parse-port args)]
    (.addShutdownHook (Runtime/getRuntime) (Thread. stop-server))
    (timbre/info "server is starting on port " port)
    (start-server port)))



(defn -main [& args]
  (start-app args))
